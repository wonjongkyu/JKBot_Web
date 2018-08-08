

var highlightTransferFee = 4000;

// 수익률 계산기 관련 function

$(function(){
    $('#saveTradeBtn').click(function(){
    	// var resultJsonArray = new Array();
		var resultVO = new Object();
		// 업비트 출발 트레이드 정보
		resultVO.exchangeName_A = $('#trade_exchangeName_1').val();
		resultVO.coinSymbol_A = $('#trade_coinSymbol_1').val();
		resultVO.totalPrice_A = $('#trade_totalPrice_1').val();
		
		// 업비트 도착 트레이드 정보
		resultVO.exchangeName_B = $('#trade_exchangeName_4').val();
		resultVO.coinSymbol_B = $('#trade_coinSymbol_4').val();
		resultVO.totalPrice_B = $('#trade_totalPrice_4').val();
		
		// 총 수익 및 수익률 정보
		resultVO.profitPrice = $('#trade_totalPrice_5').val();
		resultVO.profitRate  = $('#trade_profitRate_5').val();
		
    	saveTradeHistory(resultVO);
    	
    });
    
    $('#setProfitBtn').click(function(){
   	 
    });
    
    $('#setTotalProfitBtn').click(function(){
    	var exchangePrice = $('#exchangePrice').val();		// BTC-KRW 가격
    	
    	// 업비트-바이낸스 코인이름, 개수 세팅
    	var trade_coinSymbol_1 = $('#trade_coinSymbol_1').val();
    	$("#trade_coinSymbol_1").val(trade_coinSymbol_1.toUpperCase());
    	$("#trade_coinSymbol_2").val(trade_coinSymbol_1.toUpperCase());
    	var trade_quantity_1 = $('#trade_quantity_1').val();
    	$("#trade_quantity_2").val(trade_quantity_1);
    	
    	
    	for(var i=1; i<5; i++){
    		var quantity = $('#trade_quantity_' + i).val();				// 입력한 사토시 가격
    		var coinPrice = 0;
    		if(i == 1){
    			coinPrice = $('#upbitPrice_' + trade_coinSymbol_1.toUpperCase()).text();				// 입력한 사토시 가격
    			$("#trade_coinPrice_1").val(coinPrice);
    		}else if(i == 2){
    			coinPrice = $('#binancePrice_' + trade_coinSymbol_1.toUpperCase()).text();				// 입력한 사토시 가격
    			$("#trade_coinPrice_2").val(coinPrice);
    		}else {
    			coinPrice = $('#trade_coinPrice_' + i).val();				// 입력한 사토시 가격
    		}
    		var totalPrice = quantity*coinPrice;
    		totalPrice = comma(totalPrice.toFixed(0));
    		$("#trade_totalPrice_" + i).val('￦ ' + totalPrice);
    	}
    	
    	
    	
    	var totalPrice_1 = uncomma($("#trade_totalPrice_1").val());
    	var totalPrice_2 = uncomma($("#trade_totalPrice_2").val());
    	var totalPrice_3 = uncomma($("#trade_totalPrice_3").val());
    	var totalPrice_4 = uncomma($("#trade_totalPrice_4").val());
    	
    	// 업비트-바낸 수익률
    	var profitRate_1 = (1-(totalPrice_1/totalPrice_2))*100;
    	profitRate_1 = profitRate_1.toFixed(2);
    	if(Number(profitRate_1) < 0){
    		$("#trade_profitRate_").removeClass("text-danger text-success");
    		$("#trade_profitRate_1").addClass("text-success");
    		$("#trade_profitRate_1").val(profitRate_1);
    	}else {
    		$("#trade_profitRate_1").removeClass("text-danger text-success");
    		$("#trade_profitRate_1").addClass("text-danger");
    		$("#trade_profitRate_1").val(profitRate_1);
    	}
    	
    	// 바낸-업비트 수익률
    	var profitRate_3 = (1-(totalPrice_2/totalPrice_4))*100;
    	profitRate_3 = profitRate_3.toFixed(2);
    	if(Number(profitRate_3) < 0){
    		$("#trade_profitRate_3").removeClass("text-danger text-success");
    		$("#trade_profitRate_3").addClass("text-success");
    		$("#trade_profitRate_3").val(profitRate_3);
    	}else {
    		$("#trade_profitRate_3").removeClass("text-danger text-success");
    		$("#trade_profitRate_3").addClass("text-danger");
    		$("#trade_profitRate_3").val(profitRate_3);
    	}
    	
    	// 최종 수익률
    	var profitRate_5 = (1-(totalPrice_1/totalPrice_4))*100;
    	profitRate_5 = profitRate_5.toFixed(2);
    	if(Number(profitRate_5) < 0){
    		$("#trade_profitRate_5").removeClass("text-danger text-success");
    		$("#trade_profitRate_5").addClass("text-success");
    		$("#trade_profitRate_5").val(profitRate_5);
    	}else {
    		$("#trade_profitRate_5").removeClass("text-danger text-success");
    		$("#trade_profitRate_5").addClass("text-danger");
    		$("#trade_profitRate_5").val(profitRate_5);
    	}
    	
    	// 최종 가격
    	var totalPrice_5 = totalPrice_4 - totalPrice_1;
    	totalPrice_5 = comma(totalPrice_5.toFixed(0));
    	$("#trade_totalPrice_5").val('￦ ' + totalPrice_5);
    });
});

function saveTradeHistory(param) {
	var data = param;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/history/saveTradeHistory",
		data : JSON.stringify(data),
		dataType : 'json',
		// tradtional : true,				// json List로 받기 위한 설정
		timeout : 5000,
		success : function(data) {
			
			
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}


$(function(){
    $('#sathoshiBtn').click(function(){
    	var exchangePrice = $('#exchangePrice').val();		// BTC-KRW 가격
    	var sathoshi = $('#sathoshi').val();				// 입력한 사토시 가격
    	var result = exchangePrice*sathoshi;
    	if(context == 'admin'){
    		result = result.toFixed(2)*1.03;				// 소숫점 둘째자리에서 반올림
    		result = result.toFixed(2);
    	}else {
    		result = result.toFixed(2);				// 소숫점 둘째자리에서 반올림
    	}
    	$("#sathoshiPrice").val(result);
    });
});
 

$(function(){
    $('#kimchPreminumBtn').click(function(){
    	var kimchPreminum = $('#kimchPreminum').val();		// BTC-KRW 가격
    	$("#kimchPreminumVal").val(kimchPreminum);
    });
});
	
	
// BTC-KRW 가격 저장
function setBtcKrwPrice(){
	var data = {}
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/admin/getBtcKrwPrice",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			var updateDt = data.btcUpdateDt;		// 업데이트 날짜
			var price = Math.round(data.binanceBtcKrwPrice);
			
			$("#exchange_rate").empty();
			$("#exchange_rate").text( comma(price) + ' 원' );
			$("#exchangePrice").val(price);
			$("#exchangePriceUpdateDt").text(updateDt);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}
 
/*
 * 환율 가져오는 function
 */
function getExchangeRate() {
	var data = {}
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/admin/getExchangeRate",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 50000,
		success : function(data) {
			/*
				var rate = Math.round(data[0].rate);
				$("#exchange_rate").text( comma(rate) + '원' );
				$("#exchangeDate").text('Last Update:   '+ data[0].date );
			*/
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

/*
 * 코인 리스트 가져오는 function
 */
function getPriceExchangeInfo() {
	var data = {}
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/admin/priceExchangeInfo",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 5000,
		success : function(data) {
			var result = data;
			var resultHtml = '<select id="coinSymbolList" data-placeholder="Choose a Country..." class="chosen-select" multiple style="width:350px;" tabindex="4">';
			$.each(result, function(){
				var symbol = this.coinSymbolName;
				resultHtml += '<option value="'+ symbol + '">'+ symbol +'</option>';
			});
			resultHtml += "</select>";
			$("#coinSymbolList").html(resultHtml);
			
			$('.chosen-select').chosen({width: "100%"});
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

/*
 * 업비트-바이낸스 가격 가져오는 function
 */
function getCompareUSDT() {
	var data = {}
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/" + context + "/priceCompare/USDT",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			var result = data;
			var resultJsonArray = new Array();
			var resultHtml = "";
			$("#priceTbody").html('');
			
			$.each(result, function(){
				var name = this.closing_price;
				var resultVO = new Object();
				resultVO.coinSymbol = this.coinSymbol;
				resultVO.priceKrwA = this.priceKrwA;
				resultVO.priceKrwB = this.priceKrwB;
				resultVO.priceBtcB = this.priceBtcB;
				resultVO.priceUsdtB = this.priceUsdtB;
				resultVO.priceGapKrw = this.priceGapKrw;
				resultVO.priceGapPercent = this.priceGapPercent;
				resultVO.status = this.status;
				resultHtml += "<tr>";
				resultHtml += "<td>-</td>";
				resultHtml += "<td>" + this.coinSymbol + "</td>";
				resultHtml += "<td>" + comma(this.priceBtcB) + "</td>";
				resultHtml += "<td>" + comma(this.priceUsdtB) + "</td>";
				resultHtml += "<td>" + comma(this.priceKrwB) + "</td>";
				resultHtml += "<td>" + comma(this.priceKrwA) + "</td>";
				resultHtml += "<td>" + "-" + "</td>";
				
				var priceGapPercent = this.priceGapPercent;
				if(priceGapPercent == null){
					priceGapPercent = '0';
				}else {
					priceGapPercent = priceGapPercent + '';
				}
				
				if(priceGapPercent.indexOf("-") > -1){
					resultHtml += '<td class="text-success">' + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ') ' + '<i class="fa fa-level-down"></i></td>';
				}else if(this.priceGapPercent == 0){
					resultHtml += "<td>" + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ') ' + "</td>";
				}else {
					resultHtml += '<td class="text-danger">' + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ')   ' + '<i class="fa fa-level-up"></i></td>';
				}
				resultHtml += "</tr>";
				// 마이너스 빨간색 플러스 파란색 
				resultJsonArray.push(resultVO);
			});
			$("#priceTbody").html(resultHtml);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

/*
 * 업비트-바이낸스 가격 가져오는 function
 */
function getCompareBTC() {
	var data = {}
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/" + context + "/priceCompare/BTC",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			var result = data;
			
			// 선택한 코인 조회하기
			var choiceCoinList = $("ul.chosen-choices").find("li>span");
			var choiceCoinStr = '';
			$.each(choiceCoinList, function(){
				var value = $(this).text();
				choiceCoinStr += value + "/";
			})
			
			
			// 임시
			if(context == 'admin'){
				choiceCoinStr = "ONT/XEM/STEEM/IOST/XLM/ICX/GTO/STORM/TRX/NEO/XRP/POWR/ZIL/EOS/SNT/LOOM/GNT/";
				// choiceCoinStr = "ADA/PIVX/KMD/GRS/WAVES/ICX/NEO/ONT/QTUM/DASH/GTO/ETH/ETC/EOS/TRX/LSK/LTC/BTG/BCH/XRP/POWR/SNT/STORJ/OMG/ZRX/MCO/GNT/STORM/REP/MTL/ZIL/LOOM/";
			}
			
			var resultJsonArray = new Array();
			var resultHtml = "";
			var sendMessage = "N";
			
			// 김치 프리미엄 
			var minPremium = Number($("#minPremium").val());
			var maxPremium = Number($("#maxPremium").val());
			
			// 텔레그램 프리미엄
			var telegram = $("#kimchPreminum").val();
			
			$("#priceTbodyBTC").html('');
			
			$.each(result, function(){
				var name = this.closing_price;
				var resultVO = new Object();
				resultVO.coinSymbol = this.coinSymbol;
				resultVO.priceKrwA = this.priceKrwA;
				resultVO.priceKrwB = this.priceKrwB;
				resultVO.priceBtcB = this.priceBtcB;
				resultVO.priceUsdtB = this.priceUsdtB;
				resultVO.priceGapKrw = this.priceGapKrw;
				resultVO.priceGapPercent = this.priceGapPercent;
				resultVO.coinPriceWeightA = this.coinPriceWeightA;
				resultVO.coinPriceWeightB = this.coinPriceWeightB;
				resultVO.status = this.status;
				

				if(choiceCoinStr.indexOf(this.coinSymbol + '/') > -1){ 
					resultHtml += "<tr class='alert-success'>";
				}else {
					resultHtml += "<tr>";
				}
				
				resultJsonArray.push(resultVO);
				// 텔레그램 메시지 전송
				if( this.priceKrwA != null && this.priceKrwB != null && (this.priceGapPercent < telegram )){
					sendMessage = "Y";
				}
				
				/*if(choiceCoinStr.indexOf(this.coinSymbol + '/') > -1){ 
					// 텔레그램 메시지 전송
					if( this.priceGapPercent < telegram){
						sendMessage = "Y";
					}
					resultJsonArray.push(resultVO);
					
					resultHtml += "<tr class='alert-success'>";
				}else {
					resultHtml += "<tr>";
				}*/
				
				resultHtml += "<td>-</td>";
				resultHtml += "<td>" + this.coinSymbol + "</td>";
				resultHtml += "<td>" + this.priceBtcB + "</td>";
				
				if(this.coinPriceWeightB > maxPremium){
					resultHtml += '<td class="text-danger font-bold">' + comma(this.priceKrwB) + '&nbsp;&nbsp;<i class="fa fa-thumbs-o-up"></i>';
				}else if(this.coinPriceWeightB < minPremium){
					resultHtml += '<td class="text-success font-bold">'  + comma(this.priceKrwB) + '&nbsp;&nbsp;<i class="fa fa-thumbs-o-down"></i>';
				}else {
					resultHtml += '<td>'  + comma(this.priceKrwB);
				}
				resultHtml += "  (" + this.coinPriceWeightB +")" + "</td>";
				
				if(this.transferFeeB <= highlightTransferFee){
					resultHtml += '<td class="text-danger">' + comma(this.transferFeeB) + "</td>";
				}else {
					resultHtml += "<td>" + comma(this.transferFeeB) + "</td>";
				}
				
				if(this.coinPriceWeightA > maxPremium){
					resultHtml += '<td class="text-danger font-bold">' + comma(this.priceKrwA) + '&nbsp;&nbsp;<i class="fa fa-thumbs-o-up"></i>';
				}else if(this.coinPriceWeightA < minPremium){
					resultHtml += '<td class="text-success font-bold">'  + comma(this.priceKrwA) + '&nbsp;&nbsp;<i class="fa fa-thumbs-o-down"></i>';
				}else {
					resultHtml += '<td>'  + comma(this.priceKrwA);
				}
				resultHtml += "  (" + this.coinPriceWeightA +")" + "</td>";
				
				if(this.transferFeeA <= highlightTransferFee){
					resultHtml += '<td class="text-danger">' + comma(this.transferFeeA) + "</td>";
				}else {
					resultHtml += "<td>" + comma(this.transferFeeA) + "</td>";
				}
                 
				var priceGapPercent = this.priceGapPercent;
				if(priceGapPercent == null){
					priceGapPercent = '0';
				}else {
					priceGapPercent = priceGapPercent + '';
				}
				
				if(priceGapPercent.indexOf("-") > -1){
					resultHtml += '<td class="text-success">' + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ') ' + '<i class="fa fa-level-down"></i></td>';
				}else if(this.priceGapPercent == 0){
					resultHtml += "<td>" + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ') ' + "</td>";
				}else {
					resultHtml += '<td class="text-danger">' + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ')   ' + '<i class="fa fa-level-up"></i></td>';
				}

				resultHtml += '<td class="hide" id="binancePrice_' + this.coinSymbol +'">'+ this.priceKrwB +'</td>';
				resultHtml += '<td class="hide" id="binanceTrans_' + this.coinSymbol +'">'+ this.transferFeeB+'</td>';
				resultHtml += '<td class="hide" id="upbitPrice_' + this.coinSymbol  +'">'+ this.priceKrwA+'</td>';
				resultHtml += '<td class="hide" id="upbitTrans_' + this.coinSymbol  +'">'+ this.transferFeeA+'</td>';
				
				resultHtml += "</tr>";
				// 마이너스 빨간색 플러스 파란색 
			});
			
			// 텔레그램 메시지 전송
			if(context == 'price' && sendMessage == 'Y'){
				sendTelegramMessage(resultJsonArray);
			}
			
			$("#priceTbodyBTC").html(resultHtml);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}


