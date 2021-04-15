package com.coin.upbit.upbit.controller;

import com.coin.upbit.global.dto.ApiResult;
import com.coin.upbit.global.message.upbit.CoinInfo;
import com.coin.upbit.global.message.upbit.MyAsset;
import com.coin.upbit.upbit.controller.dto.FavoriteCoin;
import com.coin.upbit.upbit.controller.dto.RecentTradeInfo;
import com.coin.upbit.upbit.domain.MyFavoriteCoin;
import com.coin.upbit.upbit.domain.RegStatusEnum;
import com.coin.upbit.upbit.service.UpbitService;
import com.coin.upbit.upbit.service.UpbitTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 앞으로 자바 혼용해서 개발 해봐야지
// API Call 호출 마다 DB에 저장 하기

@RestController("/upbit")
public class UpbitController {

    @Autowired
    private UpbitService upbitService;

    @Autowired
    private UpbitTradeService upbitTradeService;

    @GetMapping("/infos")
    public CoinInfo getCoinInfos() {
        return upbitService.getCoinInfos();
    }

    @GetMapping("/recent/trade/info")
    public RecentTradeInfo getRecentTradeInfos(
            @RequestParam String market
    ) {
        return upbitTradeService.getRecentTradeInfo(market);
    }

    @PostMapping("/favorite/reg")
    public ApiResult<Map<String, ArrayList<MyFavoriteCoin>>> regFavoriteCoin(
            @RequestBody List<FavoriteCoin> favoriteCoins
    ) {
        return upbitService.regFavoriteCoin(favoriteCoins);
    }

    @GetMapping("/myasset")
    public List<MyAsset> getMyAsset() {
        return upbitService.getMyAsset();
    }

    @GetMapping("/favorite")
    public List<MyFavoriteCoin> getFavoriteCoin(
            @RequestParam RegStatusEnum statusEnum
    ) {
        return upbitService.getFavoriteCoin(statusEnum);
    }
}
