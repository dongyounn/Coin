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
import org.springframework.http.ResponseEntity
import java.util.*


@Service
class UpbitService(
        private val upbitApi: UpbitApi,
        private val upbitApiCallRepository: UpbitApiCallRepository
) {
    fun getCoinInfos(): CoinInfo {
        val method = UpbitApiCallHistory.of("getCoinInfos", ResultEnum.FAIL)
        try {
            val result = upbitApi.getCoinInfo()
            upbitApiCallRepository.save(method.setStatusSuccess())
            return result
        } catch (e: Exception) {
            throw BadRequestException(ErrorReason.INVALID_DATA, "에러 발생")
        }
    }

    fun getMyAsset(): ResponseEntity<String> {
        val accessKey = "accessKey"
        val secretKey = "secretKey"

        val algorithm: Algorithm = Algorithm.HMAC256(secretKey)
        val jwtToken: String = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm)

        val authenticationToken = "Bearer $jwtToken"

        return upbitApi.getMyAsset(authenticationToken)
    }
}