package com.coin.upbit.favorite.domain

import com.coin.upbit.global.message.upbit.CoinInfo
import com.coin.upbit.global.dto.BaseDomain
import javax.persistence.*

@Entity
@Table(name = "MY_FAVORITE_COIN")
data class FavoriteCoin(
        @Id
        @Column(name = "COIN_SYMBOL", columnDefinition = "VARCHAR2(20)")
        val coinSymbol: String,
        @Column(name = "COIN_NAME", columnDefinition = "VARCHAR2(50)")
        val coinName: String,
        @Enumerated(EnumType.STRING)
        @Column(name = "STATUS", columnDefinition = "VARCHAR2(20)")
        val status: RegStatusEnum,
) : BaseDomain() {
    companion object {
        fun of(
                request: CoinInfo,
                status: RegStatusEnum
        ) = FavoriteCoin(request.market, request.korean_name, status)
    }
}

enum class RegStatusEnum {
    REGISTRY, DELETE, ALL
}