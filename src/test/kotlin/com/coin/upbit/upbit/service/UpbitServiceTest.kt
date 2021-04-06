package com.coin.upbit.upbit.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UpbitServiceTest(){
    @Autowired
    private lateinit var upbitService: UpbitService

    @Test
    fun getCoinInfos() {
        upbitService.getCoinInfos()
    }
}
