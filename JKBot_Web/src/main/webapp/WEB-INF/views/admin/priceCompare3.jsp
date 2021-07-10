<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<script src="<c:url value='/js/views/admin/admin3.js?v=20210103_420'/>"></script>
<script src="<c:url value='/js/views/common/compare.js?v=20210103_420'/>"></script>

<input type="hidden" class="form-control" id="viewType1" value="3"/> 
<input type="hidden" class="form-control" id="viewType2" value="3"/> 
<input type="hidden" class="form-control" id="viewType3" value="3"/> 

<div class="row">
	<div class="col-lg-3">
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
	 
	 <div class="col-lg-3">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>BTC 가격&nbsp;&nbsp;</h5>
					<input type="hidden" class="form-control" id="exchangePrice" /> 
                	<h5 class="no-margins" id="exchange_rate"></h5>
                	<span class="label label-info pull-right" id="exchangePriceUpdateDt"></span>
            </div>
<!--             <div class="ibox-content">
                <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i></div> 
                <br/>
                <small id="exchangeDate"></small>
            </div>
 -->       
 		</div>
    </div>
    
    <div class="col-lg-3">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
            	<!-- <span class="label label-success pull-right">Monthly</span>  -->
                <h5> 사토시 계산기 </h5>
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
</div>
   
<div class="row">
	
    <div class="col-lg-3" style="display:none;">
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
    <div class="col-lg-3" style="display:none;">
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
    <div class="col-lg-6">
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
                            <li><a href="viewtype_change('viewType1', '10');">전체 보기</a>
                            </li>
                            <li><a href="viewtype_change('viewType1', '200');">요약 보기</a>
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
  		<div class="col-lg-5">
           <div class="ibox float-e-margins">
               <div class="ibox-title">
                   <h5>업비트 - <font color="blue">빗썸</font> 김프 계산</h5>
                   <div class="ibox-tools">
                       <a class="collapse-link">
                           <i class="fa fa-chevron-up"></i>
                       </a>
                       <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                           <i class="fa fa-wrench"></i>
                       </a>
                       <ul class="dropdown-menu dropdown-user">
                            <li><a href="javascript:viewtype_change('viewType1', '200');">전체 보기</a>
                            </li>
                            <li><a href="javascript:viewtype_change('viewType1', '10');">요약 보기</a>
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
	                      <!-- <th>#</th> -->
	                      <th>코인</th>
	                      <th>빗썸 (sell)</th>
	                      <th>업비트 (buy)</th>
	                      <th>김프(%)</th>
	                      <th>코인2</th>
	                      <th>빗썸 (buy)</th>
	                      <th>업비트 (sell)</th>
	                      <th>김프(%)</th>
                     	</tr>
                    </thead>
                    <tbody id="priceTbodyBTC2"></tbody>
                </table>
    			</div>
           </div>
       </div>
        
    <div class="col-lg-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>업비트 - <font color="blue">바이낸스</font>  김프 계산 (BTC)</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="javascript:viewtype_change('viewType2', '200');">전체 보기</a>
                            </li>
                            <li><a href="javascript:viewtype_change('viewType2', '10');">요약 보기</a>
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
		                     <!--  <th>#</th> -->
		                      <th>코인</th>
		                      <th>바낸 (sell)</th>
		                      <th>업비트 &nbsp; (buy)</th>
		                      <th>김프(%)</th>
		                      <th>코인2</th>
		                      <th>바낸 (buy)</th>
		                      <th>업비트 &nbsp;(sell)</th>
		                      <th>김프(%)</th>
	                     	</tr>
	                    </thead>
	                    <tbody id="priceTbodyBTC"></tbody>
	                </table>
     			</div>
            </div>
        </div>
</div>

<div class="row">
		<div class="col-lg-5">
           
       </div>
         <div class="col-lg-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>빗썸 - <font color="blue">바이낸스</font>  김프 계산 (BTC)</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="javascript:viewtype_change('viewType3', '200');">전체 보기</a>
                            </li>
                            <li><a href="javascript:viewtype_change('viewType3', '10');">요약 보기</a>
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
		                     <!--  <th>#</th> -->
		                      <th>코인</th>
		                      <th>바낸 (sell)</th>
		                      <th>빗썸 &nbsp; (buy)</th>
		                      <th>김프(%)</th>
		                      <th>코인2</th>
		                      <th>바낸 (buy)</th>
		                      <th>빗썸 &nbsp;(sell)</th>
		                      <th>김프(%)</th>
	                     	</tr>
	                    </thead>
	                    <tbody id="priceTbodyBTC3"></tbody>
	                </table>
     			</div>
            </div>
        </div>
</div>

<script>
$('.dual_select').bootstrapDualListbox({
    selectorMinimalHeight: 160
});




// 상단 GNB 메뉴 최소화
console.log('test');
$("body").addClass("mini-navbar");

</script>