package com.coin.upbit.upbit.service

import com.coin.upbit.apikey.infra.repository.ApikeyRepository
import com.coin.upbit.apikey.service.ApikeyService
import com.coin.upbit.global.message.upbit.MyAsset
import com.coin.upbit.global.message.upbit.UpbitApi
import org.springframework.stereotype.Service

@Service
class UpbitService(
        private val upbitApi: UpbitApi,
        private val apikeyService: ApikeyService,
        private val apikeyRepository: ApikeyRepository
) {
    fun getMyAsset(): List<MyAsset> {
        return upbitApi.getMyAsset(apikeyService.currentApikey())
                .filter {
                    it.balance.toBigDecimal() > 0.05.toBigDecimal()
                }
    }
}