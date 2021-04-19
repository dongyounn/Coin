package com.coin.upbit.apikey.domain

import com.coin.upbit.favorite.domain.RegStatusEnum
import com.coin.upbit.global.dto.BaseDomain
import javax.persistence.*

@Entity
@Table(name = "API_KEY")
data class Apikey(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payCostIdGenerator") @SequenceGenerator(name = "payCostIdGenerator", sequenceName = "PAY_COST_ID_SEQ", allocationSize = 1)
        @Column(name = "ID", columnDefinition = "NUMBER")
        val apiId: Long? = null,
        val secretKey: String,
        val privateKey: String,
        @Enumerated(EnumType.STRING)
        val status: RegStatusEnum
) : BaseDomain() {
    companion object {
        fun of(request: ApikeyRegistryReq) = Apikey(
                secretKey = request.secretKey,
                privateKey = request.accessKey,
                status = request.status
        )
    }
}

data class ApikeyRegistryReq(
        val secretKey: String,
        val accessKey: String,
        val status: RegStatusEnum
)