package com.coin.upbit.upbit.controller.dto


data class RecentTradeInfo(
        val market: String?,
        val trade_time_utc: String?,
        val trade_price: Double?,
        val trade_volume: Double?,
        val prev_closing_price: Double?,
        val change_price: Double?
)