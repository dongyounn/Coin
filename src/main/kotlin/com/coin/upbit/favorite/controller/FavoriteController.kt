package com.coin.upbit.favorite.controller

import com.coin.upbit.favorite.controller.dto.RegistryFavoriteCoinReq
import com.coin.upbit.favorite.domain.FavoriteCoin
import com.coin.upbit.favorite.domain.RegStatusEnum
import com.coin.upbit.favorite.service.FavoriteCoinService
import com.coin.upbit.global.dto.ApiResult
import org.springframework.web.bind.annotation.*

@RestController
class FavoriteController(
        private val favoriteCoinService: FavoriteCoinService
) {
    @GetMapping("/favorite/infos")
    fun getFavoriteCoinInfo(
            @RequestParam status: RegStatusEnum
    ): List<FavoriteCoin> {
        return favoriteCoinService.getFavoriteCoin(status)
    }

    @PostMapping("/favorite/registry")
    fun registryFavoriteCoin(
            @RequestBody request: List<RegistryFavoriteCoinReq>
    ): ApiResult<Map<String, List<FavoriteCoin>>> {
        return favoriteCoinService.regFavoriteCoin(request)
    }


}