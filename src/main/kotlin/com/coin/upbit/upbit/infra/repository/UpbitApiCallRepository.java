package com.coin.upbit.upbit.infra.repository;

import com.coin.upbit.upbit.domain.UpbitApiCallHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpbitApiCallRepository extends JpaRepository<UpbitApiCallHistory, Long> {

}
