package com.coin.upbit.apikey.service

import com.coin.upbit.apikey.domain.Apikey
import com.coin.upbit.apikey.domain.ApikeyRegistryReq
import com.coin.upbit.apikey.infra.repository.ApikeyRepository
import com.coin.upbit.favorite.domain.RegStatusEnum
import com.coin.upbit.global.dto.ApiResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApikeyService(
        private val apikeyRepository: ApikeyRepository
) {
    @Transactional
    fun saveKey(apiKey: ApikeyRegistryReq): ApiResult<Apikey> {
        return ApiResult.succeed(apikeyRepository.save(Apikey.of(apiKey)))
    }

    @Transactional
    fun deleteKey(secretKey: String): ApiResult<*> {
        apikeyRepository.findBySecretKey(secretKey)?.let {
            return ApiResult.succeed(apikeyRepository.delete(it))
        } ?: return ApiResult.failed("데이터 없음")
    }

    fun getKeys(status: RegStatusEnum): List<Apikey> =
            apikeyRepository.findAll().let { apikeyList ->
                if (status == RegStatusEnum.ALL) apikeyList.toList()
                else apikeyList.filter { apikey -> apikey.status == status }
            }

}