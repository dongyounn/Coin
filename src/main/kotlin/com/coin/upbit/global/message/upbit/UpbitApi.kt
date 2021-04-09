package com.coin.upbit.global.message.upbit

import com.coin.upbit.global.exception.BadRequestException
import com.coin.upbit.global.exception.ErrorReason
import com.coin.upbit.global.message.http.ApiGateway
import com.coin.upbit.upbit.controller.dto.RecentTradeInfo
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder


data class CoinInfo(
        val market: String,
        val korean_name: String
)

@Component
class UpbitApi(
        private val apiGateway: ApiGateway
) {
    private val baseUrl = "https://api.upbit.com/v1"

    fun getCoinInfo(): CoinInfo {
        return apiGateway.get("$baseUrl/market/all?isDetails=false", Array<CoinInfo>::class.java)?.first()
                ?: throw BadRequestException(ErrorReason.INVALID_DATA, "데이터 없음")
    }

    fun getRecentTradeInfo(market: String): RecentTradeInfo {
        return apiGateway.get(
                UriComponentsBuilder
                        .fromHttpUrl("$baseUrl/trades/ticks")
                        .queryParam("market", market)
                        .build()
                        .toString(),
        Array<RecentTradeInfo>::class.java
        )?.first() ?: throw BadRequestException(ErrorReason.INVALID_DATA, "데이터 없음")
    }
}