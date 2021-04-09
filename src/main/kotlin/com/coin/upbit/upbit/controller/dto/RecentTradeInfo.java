//package com.coin.upbit.upbit.controller.dto;
//
//import java.util.Objects;
//
//public class RecentTradeInfo {
//    private String market;
//    private String trade_time_utc;
//    private Double trade_price;
//    private Double trade_volume;
//    private Double prev_closing_price;
//    private Double change_price;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RecentTradeInfo that = (RecentTradeInfo) o;
//        return Objects.equals(market, that.market) &&
//                Objects.equals(trade_time_utc, that.trade_time_utc) &&
//                Objects.equals(trade_price, that.trade_price) &&
//                Objects.equals(trade_volume, that.trade_volume) &&
//                Objects.equals(prev_closing_price, that.prev_closing_price) &&
//                Objects.equals(change_price, that.change_price);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(market, trade_time_utc, trade_price, trade_volume, prev_closing_price, change_price);
//    }
//
//    public RecentTradeInfo(String market, String trade_time_utc, Double trade_price, Double trade_volume, Double prev_closing_price, Double change_price) {
//        this.market = market;
//        this.trade_time_utc = trade_time_utc;
//        this.trade_price = trade_price;
//        this.trade_volume = trade_volume;
//        this.prev_closing_price = prev_closing_price;
//        this.change_price = change_price;
//    }
//
//    public String getMarket() {
//        return market;
//    }
//
//    public void setMarket(String market) {
//        this.market = market;
//    }
//
//    public String getTrade_time_utc() {
//        return trade_time_utc;
//    }
//
//    public void setTrade_time_utc(String trade_time_utc) {
//        this.trade_time_utc = trade_time_utc;
//    }
//
//    public Double getTrade_price() {
//        return trade_price;
//    }
//
//    public void setTrade_price(Double trade_price) {
//        this.trade_price = trade_price;
//    }
//
//    public Double getTrade_volume() {
//        return trade_volume;
//    }
//
//    public void setTrade_volume(Double trade_volume) {
//        this.trade_volume = trade_volume;
//    }
//
//    public Double getPrev_closing_price() {
//        return prev_closing_price;
//    }
//
//    public void setPrev_closing_price(Double prev_closing_price) {
//        this.prev_closing_price = prev_closing_price;
//    }
//
//    public Double getChange_price() {
//        return change_price;
//    }
//
//    public void setChange_price(Double change_price) {
//        this.change_price = change_price;
//    }
//}
//
//
