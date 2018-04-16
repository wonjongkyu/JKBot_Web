/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.upbit.entity; 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
[  
   {  
      "code":"CRIX.UPBIT.KRW-BTC",
      "candleDateTime":"2018-04-13T04:45:00+00:00",
      "candleDateTimeKst":"2018-04-13T13:45:00+09:00",
      "openingPrice":8057000.00000000,
      "highPrice":8057000.00000000,
      "lowPrice":8055000.0,
      "tradePrice":8055000.0,
      "candleAccTradeVolume":0.56475404,
      "candleAccTradePrice":4549556.757180000,
      "timestamp":1523594707406,
      "unit":1
   }
]
 */
@Data

@Setter
@EqualsAndHashCode(callSuper=false)
public class UpbitTickerResultEntity extends RestCommonVO{
	private String tradeType;				// 종목코드 정제
	@JsonProperty("code")
	private String code;					// 종목코드
	@JsonProperty("candleDateTime")
	private String candleDateTime;			// 해당 캔들(봉) 생성 시각
	@JsonProperty("candleDateTimeKst")
	private String candleDateTimeKst;		// 해당 캔들(봉) 생성 시각 (한국시각 기준, 한국은 UTF +09:00:00 임)
	@JsonProperty("openingPrice")
	private String openingPrice;			// 해당 캔들(봉) 시가
	@JsonProperty("highPrice")
	private String highPrice;				// 해당 캔들(봉) 고가
	@JsonProperty("lowPrice")
	private String lowPrice;				// 해당 캔들(봉) 저가
	@JsonProperty("tradePrice")
	private String tradePrice;				// 해당 캔들(봉) 종가
	@JsonProperty("candleAccTradeVolume")
	private String candleAccTradeVolume;	// 해당 캔들(봉) 거래량
	@JsonProperty("candleAccTradePrice")
	private String candleAccTradePrice;		// 해당 캔들(봉) 거래대금
	@JsonProperty("timestamp")
	private String timestamp;				// UNIX 기준의 타임 스탬프
	@JsonProperty("unit")
	private String unit;					// 조회 기준
	 
}
