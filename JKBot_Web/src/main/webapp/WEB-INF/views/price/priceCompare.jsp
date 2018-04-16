<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<script src="<c:url value='/js/views/price/compare.js'/>"></script>

<!--  DashBoard 현재 시세 -->
<!-- <div class="row col-lg-12"> -->
<div class="row">
    <div class="col-lg-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>업비트 - 코인네스트 재정거래 </h5>
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
