package com.coin.upbit.upbit.controller.dto

import com.coin.upbit.upbit.domain.RegStatusEnum


data class FavoriteCoin(
        val coinSymbol: String,
        val regYn: RegStatusEnum
)

enum class YesOrNoEnum {
    Y, N
}