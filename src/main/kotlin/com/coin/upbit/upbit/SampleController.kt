package com.coin.upbit.upbit

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/test")
class SampleController(){

    @GetMapping("/get")
    fun test(): String {
        return "hello Kotlin"
    }
}