package com.coin.upbit.apikey.controller

import com.coin.upbit.apikey.domain.Apikey
import com.coin.upbit.apikey.domain.ApikeyRegistryReq
import com.coin.upbit.apikey.service.ApikeyService
import com.coin.upbit.favorite.domain.RegStatusEnum
import com.coin.upbit.global.dto.ApiResult
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.bind.annotation.*

@RestController
class ApikeyController(
        private val apikeyService: ApikeyService
) {
    @GetMapping("/apikey")
    @ApiModelProperty(value = "업비트 API 키 확인")
    fun getApikey(
            @RequestParam status: RegStatusEnum
    ) = apikeyService.getKeys(status)


    @PostMapping("/apikey")
    @ApiModelProperty(value = "업비트 API 키 저장")
    fun saveApikey(
            @RequestBody apikey: ApikeyRegistryReq
    ): ApiResult<Apikey> = apikeyService.saveKey(apikey)


    @DeleteMapping("/apikey")
    @ApiModelProperty(value = "업비트 API 키 삭제")
    fun deleteApikey(
            @RequestParam secretKey: String
    ): ApiResult<*> = apikeyService.deleteKey(secretKey)
}