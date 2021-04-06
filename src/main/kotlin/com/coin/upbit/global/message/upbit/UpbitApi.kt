package com.coin.upbit.global.message.upbit

import com.coin.upbit.global.exception.BadRequestException
import com.coin.upbit.global.exception.ErrorReason
import com.coin.upbit.global.message.http.ApiGateway
import org.springframework.stereotype.Component


data class CoinInfo(
        val market: String,
        val korean_name: String
)

@Component
class UpbitApi(
        private val apiGateway: ApiGateway
) {
    private val baseUrl = "https://api.upbit.com/v1"

    fun getCoinInfo(): Array<CoinInfo> {
        return apiGateway.get("$baseUrl/market/all?isDetails=false", Array<CoinInfo>::class.java)
                ?: throw BadRequestException(ErrorReason.INVALID_DATA,"데이터 없음")
    }
}