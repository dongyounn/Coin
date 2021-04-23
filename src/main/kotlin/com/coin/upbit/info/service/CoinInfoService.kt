package com.coin.upbit.info.service

import com.coin.upbit.favorite.domain.RegStatusEnum
import com.coin.upbit.favorite.infra.repository.FavoriteCoinRepository
import com.coin.upbit.global.exception.BadRequestException
import com.coin.upbit.global.exception.ErrorReason
import com.coin.upbit.global.message.upbit.CoinInfo
import com.coin.upbit.global.message.upbit.UpbitApi
import com.coin.upbit.upbit.controller.dto.RecentTradeInfo
import org.springframework.stereotype.Service


@Service
class CoinInfoService(
        private val upbitApi: UpbitApi,
        private val favoriteCoinRepository: FavoriteCoinRepository
) {
    fun getCoinInfos(): Array<CoinInfo> = upbitApi.getCoinInfo()

    fun getRecentTradeInfo(market: String): RecentTradeInfo = upbitApi.getRecentTradeInfo(StringBuilder("KRW-").append(market).toString())

    fun getCurrentCoinInfos(markets: List<String>) = upbitApi.getCurrentPriceInfos(markets)
    fun getFavoriteCoinRecentTradeInfos(): List<RecentTradeInfo> =
            favoriteCoinRepository.findByStatus(RegStatusEnum.REGISTRY)?.let { coins ->
                coins.map {
                    upbitApi.getRecentTradeInfo(it.coinSymbol)
                }
            } ?: throw BadRequestException(ErrorReason.INVALID_INPUT_DATA, "즐겨찾기 코인이 없음")
}