package com.coin.upbit.upbit.infra.repository

import com.coin.upbit.upbit.domain.MyFavoriteCoin
import com.coin.upbit.upbit.domain.RegStatusEnum
import org.springframework.data.jpa.repository.JpaRepository


interface MyFavoriteCoinRepository : JpaRepository<MyFavoriteCoin, String> {
    fun findByStatus(statusEnum: RegStatusEnum = RegStatusEnum.REGISTRY)
}
