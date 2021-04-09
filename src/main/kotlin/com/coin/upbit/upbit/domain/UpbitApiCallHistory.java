package com.coin.upbit.upbit.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "UPBIT_API_RESULT")
public class UpbitApiCallHistory extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payCostIdGenerator")
    @SequenceGenerator(name = "payCostIdGenerator", sequenceName = "PAY_COST_ID_SEQ", allocationSize = 1)
    @Column(name = "ID",columnDefinition = "NUMBER")
    private Long Id;
    @Column(name = "REQUEST_DATE",columnDefinition = "VARCHAR2(10)")
    private String requestDate;
    @Column(name = "METHOD",columnDefinition = "VARCHAR2(50)")
    private String method;
    @Enumerated(EnumType.STRING)
    @Column(name = "RESULT",columnDefinition = "VARCHAR2(10)")
    private ResultEnum result;

    public static UpbitApiCallHistory of(String method, ResultEnum result) {
        UpbitApiCallHistory upbitApiCallHistory = new UpbitApiCallHistory();
        upbitApiCallHistory.setMethod(method);
        upbitApiCallHistory.setResult(result);
        upbitApiCallHistory.setRequestDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        return upbitApiCallHistory;
    }

    public final UpbitApiCallHistory setStatusSuccess() {
        this.setResult(ResultEnum.SUCCESS);
        return this;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setResult(ResultEnum result) {
        this.result = result;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getMethod() {
        return method;
    }


    public ResultEnum getResult() {
        return result;
    }

}
