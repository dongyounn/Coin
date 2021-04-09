package com.coin.upbit.global.message.http

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI


@Component
class ApiGateway(
        private val restTemplate: RestTemplate
) {

    fun <T> post(url: String, request: Any, responseType: Class<T>, vararg uriVariables: Any): T? {
        return restTemplate.postForObject(url, request, responseType, *uriVariables)
    }

    fun <T> get(url: String, responseType: Class<T>?, vararg urlVariables: Any): T? {
        return restTemplate.getForObject(url, responseType!!, *urlVariables)
    }

    fun <T> get(url: URI, responseType: Class<T>): T? {
        return restTemplate.getForObject(url, responseType)
    }
}
