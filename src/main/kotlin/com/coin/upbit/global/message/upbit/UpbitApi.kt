package com.coin.upbit.global.message.upbit

import com.coin.upbit.global.exception.BadRequestException
import com.coin.upbit.global.exception.ErrorReason
import com.coin.upbit.global.message.http.ApiGateway
import com.coin.upbit.upbit.controller.dto.RecentTradeInfo
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


data class CoinInfo(
        @JsonAlias("market")
        val market: String,
        @JsonAlias("koreanName")
        val korean_name: String
)

data class MyAsset(
        @JsonAlias("currency")
        val currency: String,
        @JsonAlias("balance")
        val balance: String,
        @JsonAlias("avg_buy_price")
        val avgBuyPrice: String,
        @JsonAlias("unit_currency")
        val unitCurrency: String
)

@Component
class UpbitApi(
        private val apiGateway: ApiGateway,
        private val objectMapper: ObjectMapper
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

    fun getMyAsset(authenticationToken: String): Array<MyAsset> {
        val header = HttpHeaders()
        header.add("Content-Type", "application/json")
        header.add("Authorization", authenticationToken)

        val response = RestTemplate().exchange("$baseUrl/accounts", HttpMethod.GET, HttpEntity<Any>(header), String::class.java)

        val result = objectMapper.readValue(response.body, Array<MyAsset>::class.java)
        return result
    }


}