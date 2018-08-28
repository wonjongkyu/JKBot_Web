<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="<c:url value='js/views/trade/autoBuy.js'/>"></script>

<!-- 
	- form은 밖으로 나갈 수 있음  
	- 필요한 정보 : 매수 가능 금액, 매수 금액을 통한 최대 매수 가능 개수
	- 매수 수량/ 매수 금액 옆에 + - 기능이 있도록 개선
	- 초기화 버튼 구현
	- 전일대비 퍼센트를 입력하게 되면 매수 가격이 자동으로 계산됨
	- 숫자만 입력 가능하도록 변경
-->
                        
<form method="get" class="form-horizontal">
	<div class="row">	<!--  자동 매도,매수 Start -->
		<div class="col-md-10">
			<div class="tabs-container">
	            <ul class="nav nav-tabs">
	                <li class="active"><a data-toggle="tab" href="#tab-1"> 자동 매수 <small>v 0.1</small></a></li>
	                <li class=""><a data-toggle="tab" href="#tab-2">자동 매도 <small>v 0.1</small></a></li>
	                <li class=""><a data-toggle="tab" href="#tab-3">자동 봇 설정<small>v 0.1</small></a></li>
	            </ul>
	            <div class="tab-content">
	                <div id="tab-1" class="tab-pane active">
	                	<div class="row">
		                    <div class="panel-body">
			                	<div class="col-md-6">
			                       	<div class="form-group">
			                       		<label class="col-md-4 control-label">Basic example </label>
			                        	<div class="col-md-6">
			                        		 <div>
			                        		 	<select data-placeholder="Choose a Country..." class="chosen-select"  tabindex="2">
									                <option value="">Select</option>
									                <option value="Zambia">Zambia</option>
									                <option value="Zimbabwe">Zimbabwe</option>
								                </select>
								            </div>
			                        	</div>
			                       	</div>
		                    	</div>
                
		                    	<div class="col-md-6">
			                       	<div class="form-group">
			                       		<label class="col-md-3 control-label">매수 가격</label>
			                        	<div class="col-md-6">
			                        		<input type="text" class="form-control" value="50,000" />
			                        		<font size="2">주문가능 :  <code>500,000 KRW</code> </font>
			                        	</div>
			                       	</div>
		                    	</div>
		                    </div>
	                    </div>
	                    <div class="row">
		                    <div class="panel-body">
		                    	<div class="col-md-6">
			                       	<div class="form-group">
			                       		<label class="col-md-4 control-label">전일대비 퍼센트(%) </label>
			                        	<div class="col-md-6">
			                        		<input type="text" class="form-control" value="2%" />
			                        	</div>
			                       	</div>
		                    	</div>
		                    	<div class="col-md-6">
			                       	<div class="form-group">
			                       		<label class="col-md-3 control-label">매수 수량</label>
			                        	<div class="col-md-6">
			                        		<input type="text" class="form-control" value="1">
			                        	</div>
			                       	</div>
		                    	</div>
		                    </div>
	                    </div>
	                    
	                    <div class="row">
		                    <div class="col-md-6">
		                       	<div class="form-group">
		                       		<div class="col-md-6 col-md-offset-6">
		                       			<label class="control-label">주문 총액 : 50,000 KRW </label>
		                       		</div>
		                       	</div>
	                    	</div>
		               	</div>     	
		               	
	                    <div class="row">
		                    <div class="panel-body">
		                    	<div class="col-md-2 col-md-offset-10">
			                    	<div>
			                     		<button class="btn btn-primary " type="button"><i class="fa fa-check"></i>&nbsp;Submit</button>
			                    	</div>
		                    	</div>
	                    	</div>
	                    </div>
	                </div>
	                
	                
	                
	                
	                <div id="tab-2" class="tab-pane">
	                    <div class="panel-body">
	                        <strong>Donec quam felis</strong>
	
	                        <p>Thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects
	                            and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath </p>
	
	                        <p>I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite
	                            sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet.</p>
	                    </div>
	                </div>
	                
	                 <div id="tab-3" class="tab-pane">
	                    <div class="panel-body">
	                        <strong>Donec quam felis</strong>
	
	                        <p>Thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects
	                            and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath </p>
	
	                        <p>I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite
	                            sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet.</p>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>	<!--  자동 매수 End -->
</form>
        

<!--  chosen-select 적용 -->
<script>
	$('.chosen-select').chosen({width: "100%"});
</script>
        
         