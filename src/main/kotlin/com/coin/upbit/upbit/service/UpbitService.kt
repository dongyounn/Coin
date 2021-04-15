package com.coin.upbit.upbit.service

import com.coin.upbit.global.exception.BadRequestException
import com.coin.upbit.global.exception.ErrorReason
import com.coin.upbit.global.message.upbit.CoinInfo
import com.coin.upbit.global.message.upbit.UpbitApi
import com.coin.upbit.upbit.domain.ResultEnum
import com.coin.upbit.upbit.domain.UpbitApiCallHistory
import com.coin.upbit.upbit.infra.repository.UpbitApiCallRepository
import org.springframework.stereotype.Service
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.coin.upbit.global.dto.ApiResult
import com.coin.upbit.global.message.upbit.MyAsset
import com.coin.upbit.upbit.controller.dto.FavoriteCoin
import com.coin.upbit.upbit.controller.dto.YesOrNoEnum
import com.coin.upbit.upbit.domain.MyFavoriteCoin
import com.coin.upbit.upbit.domain.RegStatusEnum
import com.coin.upbit.upbit.infra.repository.MyFavoriteCoinRepository
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList


@Service
class UpbitService(
        private val upbitApi: UpbitApi,
        private val upbitApiCallRepository: UpbitApiCallRepository,
        private val favoriteCoinRepository: MyFavoriteCoinRepository
) {
    fun getCoinInfos(): CoinInfo {
        val method = UpbitApiCallHistory.of("getCoinInfos", ResultEnum.FAIL)
        try {
            val result = upbitApi.getCoinInfo()
            upbitApiCallRepository.save(method.setStatusSuccess())
            return result[0]
        } catch (e: Exception) {
            throw BadRequestException(ErrorReason.INVALID_DATA, "에러 발생")
        }
    }

    fun getFavoriteCoin(statusEnum: RegStatusEnum): List<MyFavoriteCoin> =
            if (statusEnum == RegStatusEnum.ALL) favoriteCoinRepository.findAll()
            else favoriteCoinRepository.findAll().filter { it.status == statusEnum }

    @Transactional
    fun regFavoriteCoin(request: List<FavoriteCoin>): ApiResult<Map<String, ArrayList<MyFavoriteCoin>>>? {
        val coinInfo = upbitApi.getCoinInfo()
        val addResults = ArrayList<MyFavoriteCoin>()
        val deleteResults = ArrayList<MyFavoriteCoin>()

        request.forEach {
            coinInfo.find { coinInfo -> coinInfo.market == "KRW-${it.coinSymbol}" }?.let { coinInfo ->
                if (it.regYn == RegStatusEnum.REGISTRY) {
                    addResults.add(MyFavoriteCoin.of(coinInfo))
                } else
                    deleteResults.add(MyFavoriteCoin.of(coinInfo))
            }
        }
        favoriteCoinRepository.saveAll(addResults)
        favoriteCoinRepository.deleteAll(deleteResults)

        return ApiResult.succeed(
                mapOf(
                        Pair("save", addResults),
                        Pair("delete", deleteResults)
                )
        )
    }

    fun getMyAsset(): List<MyAsset> {
        val accessKey = "accessKey"
        val secretKey = "secretKey"

        val jwtToken: String = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(Algorithm.HMAC256(secretKey))

        val authenticationToken = "Bearer $jwtToken"

        return upbitApi.getMyAsset(authenticationToken).filter { it.balance.toBigDecimal() > 0.05.toBigDecimal() }
    }
}