/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.binance.entity; 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
  	{[
  	"symbol": "ETHBTC",
	"priceChange": "0.00193300",
	"priceChangePercent": "3.462",
	"weightedAvgPrice": "0.05679036",
	"prevClosePrice": "0.05583100",
	"lastPrice": "0.05776400",
	"lastQty": "0.10700000",
	"bidPrice": "0.05776400",
	"bidQty": "1.43600000",
	"askPrice": "0.05783100",
	"askQty": "0.00200000",
	"openPrice": "0.05583100",
	"highPrice": "0.05794300",
	"lowPrice": "0.05576300",
	"volume": "109938.66400000",
	"quoteVolume": "6243.45633847",
	"openTime": 1523159870734,
	"closeTime": 1523246270734,
	"firstId": 50966523,
	"lastId": 51195042,
	"count": 228520
	]}
 
 */
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class BinanceTickerResultEntity extends RestCommonVO{
	@JsonProperty("tradeType")
	private String tradeType;			// 거래 단위
	
	@JsonProperty("symbol")
	private String symbol;				// 코인종류

	@JsonProperty("price")
	private String price;				// 코인 BTC 가격 (v3 API)
	
	@JsonProperty("priceChange")
	private String priceChange;			// 가격 변동
	@JsonProperty("priceChangePercent")
	private String priceChangePercent;	// 가격 변동폭
	@JsonProperty("weightedAvgPrice")
	private String weightedAvgPrice;	// 평균 가격
	@JsonProperty("prevClosePrice")
	private String prevClosePrice;		// 전일 종가
	@JsonProperty("lastPrice")
	private String lastPrice;			// 직전 체결 가격
	@JsonProperty("lastQty")
	private String lastQty;				// 직전 체결 수량
	@JsonProperty("bidPrice")
	private String bidPrice;			// 매수 가격
	@JsonProperty("bidQty")
	private String bidQty;				// 매수 수량
	@JsonProperty("askPrice")
	private String askPrice;			// 매도 가격
	@JsonProperty("askQty")
	private String askQty;				// 매도 수량
	@JsonProperty("openPrice")
	private String openPrice;			// 시가
	@JsonProperty("highPrice")
	private String highPrice;			// 고가
	@JsonProperty("lowPrice")
	private String lowPrice;			// 저가
	@JsonProperty("volume")
	private String volume;				// 시총
	@JsonProperty("quoteVolume")
	private String quoteVolume;			// 
	@JsonProperty("openTime")
	private String openTime;			// 시가 시각
	@JsonProperty("closeTime")
	private String closeTime;			// 종가 시각
	@JsonProperty("firstId")
	private String firstId;				// 
	@JsonProperty("lastId")
	private String lastId;				// 
	@JsonProperty("count")
	private String count;				// 
}
