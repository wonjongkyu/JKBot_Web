

var context = 'price';

$(document).ready(function() {
	// getExchangeRate();
	getPriceExchangeInfo();		// DB에 있는 코인 리스트 가져오기
	getCompareUSDT();			// USDT API 호출
	getCompareBTC();			// BTC API 호출 (5초 후에 호출 되도록 변경 필요함)
	setBtcKrwPrice();
	
	
	setInterval(function(){		// 1분마다 USDT 호출
		getCompareUSDT();
	}, 60000);
	
	setInterval(function(){		// 10초마다 BTC 호출
		getCompareBTC();
		setBtcKrwPrice();
	}, 10000);				
	
	// 10분 마다 환율정보 가져오기
	setInterval(function(){		// 10분마다 환율정보 호출
		getExchangeRate();
	}, 600000);	
	
});

function sendTelegramMessage(param) {
	var data = param;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/telegram/sendMessage",
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