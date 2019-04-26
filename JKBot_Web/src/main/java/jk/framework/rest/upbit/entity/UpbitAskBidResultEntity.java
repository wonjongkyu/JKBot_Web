package jk.framework.rest.upbit.entity; 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData;
import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*[{
	"market": "KRW-EOS",
	"timestamp": 1534852568326,
	"total_ask_size": 12130.20473323,
	"total_bid_size": 25651.39285036,
	"orderbook_units": [{
		"ask_price": 5560.0,
		"bid_price": 5555.0,
		"ask_size": 221.73044249,
		"bid_size": 67.8269686
	}, {
		"ask_price": 5565.0,
		"bid_price": 5550.0,
		"ask_size": 100.0,
		"bid_size": 697.0360199
	}]
}]*/
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class UpbitAskBidResultEntity extends RestCommonVO{
	@JsonProperty("market")
	private String market;			// KRW-EOS
	@JsonProperty("timestamp")
	private String timestamp;		// 1534852568326
	@JsonProperty("total_ask_size")
	private String total_ask_size;	// 12130.20473323
	@JsonProperty("total_bid_size")
	private String total_bid_size;	// 25651.39285036
	@JsonProperty("orderbook_units")
	private List<ORDERBOOK> orderbook_units;	// KRW-EOS
	
	/*
	@JsonProperty("code")
	private String market;			// KRW-EOS
	@JsonProperty("timestamp")
	private String timestamp;		// 1534852568326
	@JsonProperty("total_ask_size")
	private String total_ask_size;	// 12130.20473323
	@JsonProperty("total_bid_size")
	private String total_bid_size;	// 25651.39285036
	@JsonProperty("orderbook_units")
	private List<ORDERBOOK> orderbook_units;	// KRW-EOS
	*/
	@Getter
	@Setter
	public static class ORDERBOOK {
		@JsonProperty("ask_price")
		private String ask_price;	// 
		@JsonProperty("bid_price")
		private String bid_price;	// 
		@JsonProperty("ask_size")
		private String ask_size;	// 
		@JsonProperty("bid_size")
		private String bid_size;	// 
	}
}
