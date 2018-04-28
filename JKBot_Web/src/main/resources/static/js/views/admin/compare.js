


$(document).ready(function() {
	getExchangeRate();
	getPriceExchangeInfo();		// DB에 있는 코인 리스트 가져오기
	getCompareUSDT();			// USDT API 호출
	getCompareBTC();			// BTC API 호출 (5초 후에 호출 되도록 변경 필요함)
	setBtcKrwPrice();
	
	setInterval(function(){		// 1분마다 USDT 호출
		getCompareUSDT();
	}, 60000);
	
	setInterval(function(){		// 15초마다 BTC 호출
		getCompareBTC();
		setBtcKrwPrice();
	}, 15000);
	
	// 10분 마다 환율정보 가져오기
	setInterval(function(){		// 10분마다 환율정보 호출
		getExchangeRate();
	}, 600000);	
	
});


$(function(){
    $('#sathoshiBtn').click(function(){
    	var exchangePrice = $('#exchangePrice').val();		// BTC-KRW 가격
    	var sathoshi = $('#sathoshi').val();				// 입력한 사토시 가격
    	var result = exchangePrice*sathoshi;
    	result = result.toFixed(2);				// 소숫점 둘째자리에서 반올림
    	$("#sathoshiPrice").val(result);
    });
});
  
	
	
<!-- BTC-KRW 가격 저장 -->
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
			var price = Math.round(data);
			$("#exchange_rate").empty();
			$("#exchange_rate").text( comma(price) + ' 원' );
			$("#exchangePrice").val(price);
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
		url : "/admin/priceCompare/USDT",
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
		url : "/admin/priceCompare/BTC",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			// 선택한 코인 조회하기
			var choiceCoinList = $("ul.chosen-choices").find("li>span");
			var choiceCoinStr = '';
			$.each(choiceCoinList, function(){
				var value = $(this).text();
				choiceCoinStr += value + "/";
			})
			// 임시
			choiceCoinStr = "STORM/TRX/GRS/NEO/STEEM/XRP/POWR/SNT/EOS/OMG/";
			
			var result = data;
			
			var resultJsonArray = new Array();
			var resultHtml = "";
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
				resultHtml += "<td>-</td>";
				resultHtml += "<td>" + this.coinSymbol + "</td>";
				resultHtml += "<td>" + this.priceBtcB + "</td>";
				
				if(this.coinPriceWeightB > 5){
					resultHtml += '<td class="text-danger font-bold">' + comma(this.priceKrwB) + '&nbsp;&nbsp;<i class="fa fa-thumbs-o-up"></i>';
				}else if(this.coinPriceWeightB < -5){
					resultHtml += '<td class="text-success font-bold">'  + comma(this.priceKrwB) + '&nbsp;&nbsp;<i class="fa fa-thumbs-o-down"></i>';
				}else {
					resultHtml += '<td>'  + comma(this.priceKrwB);
				}
				resultHtml += "  (" + this.coinPriceWeightB +")" + "</td>";
				
				if(this.transferFeeB <= 4000){
					resultHtml += '<td class="text-danger">' + comma(this.transferFeeB) + "</td>";
				}else {
					resultHtml += "<td>" + comma(this.transferFeeB) + "</td>";
				}
				
				if(this.coinPriceWeightA > 5){
					resultHtml += '<td class="text-danger font-bold">' + comma(this.priceKrwA) + '&nbsp;&nbsp;<i class="fa fa-thumbs-o-up"></i>';
				}else if(this.coinPriceWeightA < -5){
					resultHtml += '<td class="text-success font-bold">'  + comma(this.priceKrwA) + '&nbsp;&nbsp;<i class="fa fa-thumbs-o-down"></i>';
				}else {
					resultHtml += '<td>'  + comma(this.priceKrwA);
				}
				resultHtml += "  (" + this.coinPriceWeightA +")" + "</td>";
				
				if(this.transferFeeA <= 4000){
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
				
				resultHtml += "</tr>";
				// 마이너스 빨간색 플러스 파란색 
				resultJsonArray.push(resultVO);
			});
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


