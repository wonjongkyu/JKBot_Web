package jk.framework.rest.bithumb.entity; 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData;
import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
{
	"status": "0000",
	"data": {
		"timestamp": "1606375252582",
		"payment_currency": "KRW",
		"order_currency": "MLK",
		"bids": [{
			"price": "177.9",
			"quantity": "10012.9563"
		}, {
			"price": "177.8",
			"quantity": "3338.3514"
		}],
		"asks": [{
			"price": "179.1",
			"quantity": "93.5"
		}, {
			"price": "179.3",
			"quantity": "50"
		}]
	}
}

*/
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class BithumbAskBidResultEntity extends RestCommonVO{
	 
	@JsonProperty("data")
	public Data data;

	public Data getData() {
		return data;
	}
	
	@Getter
	@Setter
	public static class Data {
		@JsonProperty("order_currency")
		private String order_currency;			// KRW-EOS
		@JsonProperty("timestamp")
		private String timestamp;			// 1534852568326
		@JsonProperty("bids")
		private List<BIDS> bids;	
		@JsonProperty("asks")
		private List<ASKS> asks;	
	 
		@Getter
		@Setter
		public static class BIDS {
			@JsonProperty("price")
			private String price; 
			@JsonProperty("quantity")
			private String quantity; 
		}
		
		@Getter
		@Setter
		public static class ASKS {
			@JsonProperty("price")
			private String price;	 
			@JsonProperty("quantity")
			private String quantity;	 
		}
	}
}
