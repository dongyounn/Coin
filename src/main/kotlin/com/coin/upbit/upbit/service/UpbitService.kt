package com.coin.upbit.upbit.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.coin.upbit.global.message.upbit.MyAsset
import com.coin.upbit.global.message.upbit.UpbitApi
import org.springframework.stereotype.Service
import java.util.*


@Service
class UpbitService(
        private val upbitApi: UpbitApi
) {
    fun getMyAsset(): List<MyAsset> {
        val accessKey = "2oxejxICVBr0NmDtHxM1oxbxiWecYchKgi0WYxZd"
        val secretKey = "wBG71NL1eNVAK3wtgfhiKKPgmHzvRx3UQb0aPcUx"

        val jwtToken: String = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(Algorithm.HMAC256(secretKey))

        val authenticationToken = "Bearer $jwtToken"

        return upbitApi.getMyAsset(authenticationToken).filter { it.balance.toBigDecimal() > 0.05.toBigDecimal() }
    }
}