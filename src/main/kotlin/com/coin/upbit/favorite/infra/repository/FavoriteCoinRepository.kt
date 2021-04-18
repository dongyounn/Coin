package com.coin.upbit.favorite.infra.repository

import com.coin.upbit.favorite.domain.FavoriteCoin
import com.coin.upbit.favorite.domain.RegStatusEnum
import org.springframework.data.jpa.repository.JpaRepository


interface FavoriteCoinRepository : JpaRepository<FavoriteCoin, String> {
    fun findByStatus(statusEnum: RegStatusEnum = RegStatusEnum.REGISTRY): List<FavoriteCoin>?
}
