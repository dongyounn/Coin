package com.coin.upbit.upbit.domain

import com.coin.upbit.global.message.upbit.CoinInfo
import javax.persistence.*

@Entity
@Table(name = "MY_FAVORITE_COIN")
data class MyFavoriteCoin(
        @Id
        @Column(name = "COIN_SYMBOL", columnDefinition = "VARCHAR2(20)")
        val coinSymbol: String,
        @Column(name = "COIN_NAME", columnDefinition = "VARCHAR2(50)")
        val coinName: String,
        @Enumerated(EnumType.STRING)
        @Column(name = "STATUS", columnDefinition = "VARCHAR2(20)")
        val status: RegStatusEnum,
) : BaseDomain(){
    companion object{
        fun of(
                request: CoinInfo
        ) = MyFavoriteCoin(request.market, request.korean_name, RegStatusEnum.REGISTRY)

    }
}


enum class RegStatusEnum {
    REGISTRY, DELETE, ALL
}