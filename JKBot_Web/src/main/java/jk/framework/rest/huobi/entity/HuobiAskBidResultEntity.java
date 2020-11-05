/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.huobi.entity; 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
 
/*
 * 
 * 
  {
	"ch": "market.dkausdt.depth.step1",
	"status": "ok",
	"ts": 1599741158957,
	"tick": {
		"bids": [
			[0.03626, 6850.65], 
			[0.034, 501000.0]
		],
		"asks": [
			[0.03681, 3565.41], 
			[0.0404, 499.0]
		],
		"version": 100012769608,
		"ts": 1599741158549
	}
}
 *
 */
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class HuobiAskBidResultEntity extends RestCommonVO{
	@JsonProperty("ch")
	private String ch;			 
	@JsonProperty("status")
	private String status;		
	@JsonProperty("ts")
	private String ts;
	@JsonProperty("tick")
	private Tick tick;
	
	@Getter
	@Setter
	public static class Tick {
		@JsonProperty("bids")
		private List<List<Object>>  bids;
		@JsonProperty("asks")
		private List<List<Object>> asks;
		@JsonProperty("version")
		private String version;
		@JsonProperty("ts")
		private String ts;
		 
	}
}
