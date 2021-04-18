package com.coin.upbit.info.controller

import com.coin.upbit.info.service.CoinInfoService
import com.coin.upbit.upbit.controller.dto.RecentTradeInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CoinInfoController(
        private val coinInfoService: CoinInfoService
) {

    @GetMapping("/coin/infos")
    fun coinInfos() = coinInfoService.getCoinInfos()

    @GetMapping("/coin/recent/trade/info")
    fun getRecentTradeInfo(
            @RequestParam market: String
    ): RecentTradeInfo {
        return coinInfoService.getRecentTradeInfo(market)
    }

    @GetMapping("/favorite/coin/recent/trade/info")
    fun getFavoriteCoinTradeInfos(
    ): List<RecentTradeInfo> {
        return coinInfoService.getFavoriteCoinRecentTradeInfos()
    }
}