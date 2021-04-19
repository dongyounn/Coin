package com.coin.upbit.apikey.controller

import com.coin.upbit.apikey.domain.Apikey
import com.coin.upbit.apikey.domain.ApikeyRegistryReq
import com.coin.upbit.apikey.service.ApikeyService
import com.coin.upbit.favorite.domain.RegStatusEnum
import com.coin.upbit.global.dto.ApiResult
import org.springframework.web.bind.annotation.*

@RestController
class ApikeyController(
        private val apikeyService: ApikeyService
) {

    @GetMapping("/apikey")
    fun getApikey(
            @RequestParam status: RegStatusEnum
    ) = apikeyService.getKeys(status)


    @PostMapping("/apikey")
    fun saveApikey(
            @RequestBody apikey: ApikeyRegistryReq
    ): ApiResult<Apikey> = apikeyService.saveKey(apikey)


    @DeleteMapping("/apikey")
    fun deleteApikey(
            @RequestParam secretKey: String
    ): ApiResult<*> = apikeyService.deleteKey(secretKey)

}