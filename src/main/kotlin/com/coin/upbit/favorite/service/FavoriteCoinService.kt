package com.coin.upbit.favorite.service

import com.coin.upbit.favorite.controller.dto.RegistryFavoriteCoinReq
import com.coin.upbit.favorite.domain.FavoriteCoin
import com.coin.upbit.favorite.domain.RegStatusEnum
import com.coin.upbit.favorite.infra.repository.FavoriteCoinRepository
import com.coin.upbit.global.dto.ApiResult
import com.coin.upbit.global.message.upbit.UpbitApi
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FavoriteCoinService(
        private val upbitApi: UpbitApi,
        private val favoriteCoinRepository: FavoriteCoinRepository
) {

    fun getFavoriteCoin(statusEnum: RegStatusEnum): List<FavoriteCoin> =
            if (statusEnum == RegStatusEnum.ALL) favoriteCoinRepository.findAll()
            else favoriteCoinRepository.findAll().filter { it.status == statusEnum }

    @Transactional
    fun regFavoriteCoin(request: List<RegistryFavoriteCoinReq>): ApiResult<Map<String, List<FavoriteCoin>>> =
            upbitApi.getCoinInfo().let { coinInfo ->
                request.map {
                    coinInfo.find { coinInfo -> coinInfo.market == "KRW-${it.coinSymbol}" }?.let { coinInfo ->
                        FavoriteCoin.of(coinInfo, it.regYn)
                    }!!
                }.let { results ->
                    favoriteCoinRepository.saveAll(results)
                    ApiResult.succeed(
                            mapOf(
                                    Pair("save", results.filter { it.status == RegStatusEnum.REGISTRY }),
                                    Pair("delete", results.filter { it.status == RegStatusEnum.DELETE })
                            )
                    )
                }
            }
}