package com.coin.upbit.apikey.infra.repository

import com.coin.upbit.apikey.domain.Apikey
import org.springframework.data.jpa.repository.JpaRepository

interface ApikeyRepository : JpaRepository<Apikey, Long> {
    fun findBySecretKey(secretKey: String): Apikey?
    fun findByApiId(id: Long): Apikey
}