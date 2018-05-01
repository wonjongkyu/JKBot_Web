<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<script src="<c:url value='/js/views/admin/admin.js'/>"></script>
<script src="<c:url value='/js/views/common/compare.js'/>"></script>

<div class="row">
	<div class="col-lg-5">
	    <div class="ibox float-e-margins">
	    <div class="ibox-title">
	        <h5>코인 선택 </h5>
	        <div class="ibox-tools">
	            <a class="collapse-link">
	                <i class="fa fa-chevron-up"></i>
	            </a>
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	                <i class="fa fa-wrench"></i>
	            </a>
	            <ul class="dropdown-menu dropdown-user">
	                <li><a href="#">Config option 1</a>
	                </li>
	                <li><a href="#">Config option 2</a>
	                </li>
	            </ul>
	            <a class="close-link">
	                <i class="fa fa-times"></i>
	            </a>
	        </div>
	    </div>
	   		<div class="ibox-content" style="display: none;">
				<p>선택한 코인 하이라이팅</p>
			    <div class="form-group">
			    	<!-- <label class="font-normal">Multi select</label> -->
				    <div id="coinSymbolList"> </div>
			    </div>
	    	</div>
	    </div>
	 </div>
</div>
   
<div class="row">
	<div class="col-lg-3">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                
                <h5>BTC-KRW 가격</h5>
            </div>
            <div class="ibox-content">
				<input type="hidden" class="form-control" id="exchangePrice" /> 
                <h2 class="no-margins" id="exchange_rate"></h1>
                <!-- <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i></div>  -->
                <br/>
                <!-- <small id="exchangeDate"></small> -->
                <span class="label label-info pull-right" id="exchangePriceUpdateDt"></span>
            </div>
        </div>
    </div>
    
    <div class="col-lg-3">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
            	<!-- <span class="label label-success pull-right">Monthly</span>  -->
                <h5> 사토시 계산기 </h5>
            </div>
            <div class="ibox-content">
               <div class="input-group">
	               	<input type="text" class="form-control" id="sathoshi"> 
	               	<span class="input-group-btn"> 
	               		<button type="button" class="btn btn-primary" id="sathoshiBtn" > 사토시 계산</button> 
	               	</span>
            	</div>
            	<div class="form-group has-error">
            		<!-- <div class="form-group has-error"><label class="control-label"></label> -->
	            	<div class=""><input type="text" disabled="" id="sathoshiPrice" class="form-control" /></div>
	            </div>
   			</div>
    	</div>
    </div>
    <div class="col-lg-3">
        <div class="ibox float-e-margins ">
            <div class="ibox-title">
                <!-- <span class="label label-success pull-right">Monthly</span> -->
                <h5> 김프 알람 (텔레그램)</h5>
            </div>
            <div class="ibox-content">
               <div class="input-group">
	               	<input type="text" class="form-control" id="kimchPreminum" value="-1"> 
	               	<span class="input-group-btn"> 
	               		<button type="button" class="btn btn-primary" id="kimchPreminumBtn" > 김프 입력</button> 
	               	</span>
            	</div>
            	<div class="form-group has-error"><label class="control-label"></label>
	            	<div class=""><input type="text" disabled="" id="kimchPreminumVal" class="form-control" value="-1" /></div>
	            </div>
            </div>
        </div>
    </div>
    <div class="col-lg-3">
        <div class="ibox float-e-margins ">
            <div class="ibox-title">
                <!-- <span class="label label-success pull-right">Monthly</span> -->
                <h5> 김프 알람 (최대. 최소)</h5>
            </div>
            <div class="ibox-content">
            	<div class="form-group has-error"><label class="control-label"></label>
	            	<input type="text" disabled="" id="maxPremium" class="form-control" value="5" />
	            </div>
	            <div class="form-group has-error"><label class="control-label"></label>
	            	<input type="text" disabled="" id="minPremium" class="form-control" value="-5" />
	            </div>
               <!-- <div class="input-group">
	               	<input type="text" class="form-control" id="kimchPreminum" value="-1"> 
	               	<span class="input-group-btn"> 
	               		<button type="button" class="btn btn-primary" id="kimchPreminumBtn" > 김프 입력</button> 
	               	</span>
            	</div> -->
            </div>
        </div>
    </div>
</div>        

          
<!--  DashBoard 현재 시세 -->
<!-- <div class="row col-lg-12"> -->
<div class="row hide">
    <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>업비트 - 바이낸스 김프 계산 (USDT)</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#">Config option 1</a>
                            </li>
                            <li><a href="#">Config option 2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content" style="display: none;">
	                <table class="table table-hover">
	                    <thead>
	                  		<tr>
		                      <th>#</th>
		                      <th>코인</th>
		                      <th>binance(BTC)</th>
		                      <th>binance($)</th>
		                      <th>binance(￦)</th>
		                      <th>upbit(￦)</th>
		                      <th>BTC차액(￦)</th>
		                      <th>김치프리미엄(￦)</th>
	                     	</tr>
	                    </thead>
	                    <tbody id="priceTbody"></tbody>
	                </table>
     			</div>
            </div>
        </div>
</div>

<div class="row">
    <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>업비트 - 바이낸스 김프 계산 (BTC)</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#">Config option 1</a>
                            </li>
                            <li><a href="#">Config option 2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
	                <table class="table table-hover">
	                    <thead>
	                  		<tr>
		                      <th>#</th>
		                      <th>코인</th>
		                      <th>binance(BTC)</th>
		                      <th>binance(￦)</th>
		                      <th>binance(수수료)</th>
		                      <th>upbit(￦)</th>
		                      <th>upbit(수수료)</th>
		                      <th>김치프리미엄(￦)</th>
	                     	</tr>
	                    </thead>
	                    <tbody id="priceTbodyBTC"></tbody>
	                </table>
     			</div>
            </div>
        </div>
</div>
<script>
$('.dual_select').bootstrapDualListbox({
    selectorMinimalHeight: 160
});


</script>