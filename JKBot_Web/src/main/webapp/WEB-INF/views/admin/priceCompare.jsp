<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<script src="<c:url value='/js/views/admin/compare.js'/>"></script>


<div class="row hide">
	<div class="col-lg-12">
	    <div class="ibox">
	        <div class="ibox-title">
	            <h5>코인 선택</h5>
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
	        <div class="ibox-content" >
	           <!--  <p>
	                Bootstrap Dual Listbox is a responsive dual listbox widget optimized for Twitter Bootstrap. It works on all modern browsers and on touch devices.
	            </p> -->
	
	            <form id="form" action="#" class="wizard-big">
	                <select class="form-control dual_select" multiple>
	                    <option value="United States">United States</option>
	                    <option value="United Kingdom">United Kingdom</option>
	                    <option value="Australia">Australia</option>
	                    <option selected value="Austria">Austria</option>
	                    <option selected value="Bahamas">Bahamas</option>
	                    <option value="Barbados">Barbados</option>
	                    <option value="Belgium">Belgium</option>
	                    <option value="Bermuda">Bermuda</option>
	                    <option value="Brazil">Brazil</option>
	                    <option value="Bulgaria">Bulgaria</option>
	                    <option value="Cameroon">Cameroon</option>
	                    <option value="Canada">Canada</option>
	                </select>
	            </form>
	        </div>
	    </div>
	</div>
</div>

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
                     
<!--  DashBoard 현재 시세 -->
<!-- <div class="row col-lg-12"> -->
<div class="row">
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
		                      <th>binance(￦) (가중치)</th>
		                      <th>binance(수수료)</th>
		                      <th>upbit(￦) (가중치)</th>
		                      <th>upbit(수수료)</th>
		                      <!-- <th>가격 변동(최근 5분)</th> -->
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