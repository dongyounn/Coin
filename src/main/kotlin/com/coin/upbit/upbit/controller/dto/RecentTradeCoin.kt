package com.coin.upbit.upbit.controller.dto

import com.fasterxml.jackson.annotation.JsonAlias


data class RecentTradeInfo(
        @JsonAlias("market")
        val market: String?,
        @JsonAlias("trade_time_utc")
        val tradeTimeUtc: String?,
        @JsonAlias("trade_price")
        val tradePrice: Double?,
        @JsonAlias("trade_volume")
        val tradeVolume: Double?,
        @JsonAlias("prev_closing_price")
        val prevClosingPrice: Double?,
        @JsonAlias("change_price")
        val changePrice: Double?
)