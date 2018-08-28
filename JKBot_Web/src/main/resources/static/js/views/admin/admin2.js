

// context 설정
var context = 'admin';

$(document).ready(function() {
	
	getExchangeRate();
	getPriceExchangeInfo();		// DB에 있는 코인 리스트 가져오기
	getCompareUSDT2();			// USDT API 호출
	getCompareBTC2();			// BTC API 호출 (5초 후에 호출 되도록 변경 필요함)
	setBtcKrwPrice();
	
	setInterval(function(){		// 1분마다 USDT 호출
		getCompareUSDT2();
	}, 60000);
	
	setInterval(function(){		// 15초마다 BTC 호출
		getCompareBTC2();
		setBtcKrwPrice();
	}, 15000);
	
	// 10분 마다 환율정보 가져오기
	setInterval(function(){		// 10분마다 환율정보 호출
		getExchangeRate();
	}, 600000);	
	
});
