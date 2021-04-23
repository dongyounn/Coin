package com.coin.upbit.upbit.controller

import com.coin.upbit.global.message.upbit.MyAsset
import com.coin.upbit.upbit.service.UpbitService
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController("/upbit")
class UpbitController(
        private val upbitService: UpbitService
) {
    @GetMapping("/myasset")
    @ApiModelProperty(value = "내 자산 확인")
    fun myAsset(): List<MyAsset> = upbitService.getMyAsset()
}
