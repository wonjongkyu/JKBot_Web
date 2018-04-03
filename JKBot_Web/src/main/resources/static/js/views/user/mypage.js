	$(document).ready(function() {
		 
		getUserBalanceInfo();
		setInterval(function(){
			
			getUserBalanceInfo();
		}, 10000);
	});
	
	
	/*
	 * userId 변경해야 함
	 */
	function getUserBalanceInfo() {
		var userId = 'wonjongkyu';
		var data = {}
	
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/user/balance/" + userId,
			// data : JSON.stringify(data),
			dataType : 'json',
			timeout : 10000,
			success : function(data) {
				
				private String userId;				// 사용자 ID
				private String exchangeName;		// 거래소 명
				private String coinSymbolName;		// 코인 심볼명
				private String totalCurrency;		// 보유중인 통화 수
				private String inUserCurrency;		// 사용중 통화 수
				private String availableCurrency;	// 사용가능 통화 수
				private String xcoinLast;			// 마지막 거래체결 금액(bithumb)
				
				
				$("#bithumbTicker_table").jqGrid("GridUnload");
				// console.log("SUCCESS: ", data);
				var result = data;
				$("#bithumbTicker_table").html(result); // 초기화, empty 대체 가능?
				
				var resultJsonArray = new Array();
				$.each(result.data, function(){
					var name = this.closing_price;
					var resultVO = new Object();
					resultVO.coinName = this.currency_name;
					resultVO.aggregateValue = 0;
					resultVO.openingPrice = this.opening_price;
					resultVO.realtimePrice = this.closing_price;
					resultVO.changePrice = this.closing_price - this.opening_price;
					resultVO.changeRate = pricePercent(this.opening_price,this.closing_price);
					resultVO.transAmount = parseInt(this.volume_1day).toFixed(0);
					resultJsonArray.push(resultVO);
				});
		        
				
		        // Configuration for jqGrid Example 1
		        $("#bithumbTicker_table").jqGrid({
		            data: resultJsonArray,
		            datatype: "local",
		            height: 340,
		            autowidth: true,
		            shrinkToFit: true,
		            rowNum: 15,
		            rowList: [10, 20, 30],
		            colNames: ['코인', '시가총액(임시)', '전일종가', '현재가', '전일대비(금액)','전일대비(%)', 'temp'],
		            colModel: [
		                /*
		                {name:'money', formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces: 0}}
							formatter:'currency' <--금액? 통화? 화페로 설정
							thousandsSeparator:"," <--천 단위마다 ,를 찍음
							decimalPlaces: 0  <-- 소수점 자리 0이면 100, 1이면 100.0, 2면 100.00
		                */
		                {name:'coinName',		index:'coinName', 		editable: true, width:70, 	sortable:true, search:true},
	                    {name:'aggregateValue',	index:'aggregateValue', editable: true, width:80, 	align:"right",	sorttype: "int", 	formatter: "currency", formatoptions:{thousandsSeparator:",", decimalPlaces: 0}},
	                    {name:'openingPrice',	index:'openingPrice', 	editable: true, width:80, 	align:"right",	sorttype: "int", 	formatter: "currency", formatoptions:{thousandsSeparator:",", decimalPlaces: 0}},
	                    {name:'realtimePrice',	index:'realtimePrice', 	editable: true, width:80, 	align:"right",	sorttype: "int", 	formatter: "currency", formatoptions:{thousandsSeparator:",", decimalPlaces: 0}},
	                    {name:'changePrice',	index:'changePrice', 	editable: true, width:80, 	align:"right",	sorttype: "int", 	formatter: "currency", formatoptions:{thousandsSeparator:",", decimalPlaces: 0}},
	                    {name:'changeRate',		index:'changeRate', 	editable: true, width:80,	align:"right",	sorttype:"float", 	formatter: "currency", formatoptions:{thousandsSeparator:",", decimalPlaces: 2}},
	                    {name:'temp',			index:'temp', 			editable: true, width:80, 	sortable:false}
	                    
		            ],
		            // pager: "#bithumbTicker_page",
		            viewrecords: true,
		            caption: "",
		            hidegrid: false,
		            afterInsertRow: function(rowid, aData){
		            	// 상승,하락 확인하여 폰트 색깔 변경 및 + 붙여주기
		            	// changeRate가 0보다 크면 상승(red), changeRate가 0보다 작으면 하락(blue), changeRate가 0이면 black
		            	// 개발 예정 : 원, % 붙이기
		            	var aggregateValue 	= aData.aggregateValue + " 원";
		            	var realtimePrice 	= aData.realtimePrice + " 원";
		            	var changePrice 	= aData.changePrice + " 원";
		            	var changeRate 		= aData.changeRate + " %";
		            	if(aData.changeRate > 0){
		            		jQuery("#bithumbTicker_table").setCell(rowid,'aggregateValue','',{color:'red'});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'realtimePrice','',{color:'red'});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'changePrice','',{color:'red'});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'changeRate','',{color:'red'});
		            	}else if(aData.changeRate < 0){
		            		jQuery("#bithumbTicker_table").setCell(rowid,'aggregateValue','',{color:'blue'});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'realtimePrice','',{color:'blue'});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'changePrice','',{color:'blue'});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'changeRate','',{color:'blue'});
		            	}else {
		            		jQuery("#bithumbTicker_table").setCell(rowid,'aggregateValue','',{});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'realtimePrice','',{});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'changePrice','',{});
		            		jQuery("#bithumbTicker_table").setCell(rowid,'changeRate','',{});
		            	}
		            }
		            
		        }).trigger("reloadGrid");
		        
		        // Add responsive to jqGrid
		        $(window).bind('resize', function () {
		            var width = $('.jqGrid_wrapper').width();
		            $('#bithumbTicker_table').setGridWidth(width);
		        });


		        setTimeout(function(){
		            $('.wrapper-content').removeClass('animated fadeInRight');
		        },700);
		        
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
	
	  
	
	
 
