package com.coin.upbit.upbit.service;

import com.coin.upbit.global.exception.BadRequestException;
import com.coin.upbit.global.exception.ErrorReason;
import com.coin.upbit.global.exception.ExternalServiceException;
import com.coin.upbit.global.message.upbit.UpbitApi;
import com.coin.upbit.upbit.controller.dto.RecentTradeInfo;
import com.coin.upbit.upbit.domain.ResultEnum;
import com.coin.upbit.upbit.domain.UpbitApiCallHistory;
import com.coin.upbit.upbit.infra.repository.UpbitApiCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UpbitTradeService {

    @Autowired
    private UpbitApi upbitApi;
    @Autowired
    private UpbitApiCallRepository upbitApiCallRepository;

    @Transactional
    public RecentTradeInfo getRecentTradeInfo(String market) {
        UpbitApiCallHistory upbitApiCallHistory = UpbitApiCallHistory.of("getRecentTradeInfo", ResultEnum.FAIL);
        RecentTradeInfo result;
        try {
            result = upbitApi.getRecentTradeInfo(new StringBuilder("KRW-").append(market).toString());
            upbitApiCallHistory.setStatusSuccess();
        } catch (Exception e) {
            throw new BadRequestException(ErrorReason.INVALID_DATA, "에러 발생");
        }
        upbitApiCallRepository.save(upbitApiCallHistory);
        return result;
    }

}
