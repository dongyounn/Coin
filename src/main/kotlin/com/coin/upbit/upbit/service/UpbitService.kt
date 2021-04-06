package com.coin.upbit.upbit.service

import com.coin.upbit.global.message.upbit.CoinInfo
import com.coin.upbit.global.message.upbit.UpbitApi
import org.springframework.stereotype.Service

@Service
class UpbitService(
        private val upbitApi: UpbitApi
) {
    fun getCoinInfos(): Array<CoinInfo> {
        return upbitApi.getCoinInfo()
    }
}