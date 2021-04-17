package com.coin.upbit.favorite.controller.dto

import com.coin.upbit.favorite.domain.RegStatusEnum


data class RegistryFavoriteCoinReq(
        val coinSymbol: String,
        val regYn: RegStatusEnum
)

enum class YesOrNoEnum {
    Y, N
}