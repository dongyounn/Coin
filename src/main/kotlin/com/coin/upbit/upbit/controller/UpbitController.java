package com.coin.upbit.upbit.controller;

import com.coin.upbit.global.message.upbit.CoinInfo;
import com.coin.upbit.upbit.service.UpbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

// 앞으로 자바 혼용해서 개발 해봐야지
// API Call 호출 마다 DB에 저장 하기

@RestController("/upbit")
public class UpbitController {

    @Autowired
    private UpbitService upbitService;

    @GetMapping("/infos")
    public List<CoinInfo> getCoinInfos() {
        return Arrays.asList(upbitService.getCoinInfos());
    }
}
