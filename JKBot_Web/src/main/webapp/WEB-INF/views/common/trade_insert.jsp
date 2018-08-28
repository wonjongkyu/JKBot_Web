<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
    <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5> 재정거래 내역 등록</h5>
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
                <div class="ibox-content m-b-sm border-bottom">
	                <div class="row">
	                    <div class="col-sm-1">
	                        <div class="form-group has-success">
	                            <label class="control-label" for="product_name">거래소</label>
	                            <input type="text" id="trade_exchangeName_1" disabled="" value="업비트" class="form-control hnas-">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <label class="control-label" for="price">코인명</label>
	                            <input type="text" id="trade_coinSymbol_1" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <label class="control-label" for="quantity">코인 수</label>
	                            <input type="text" id="trade_quantity_1" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <label class="control-label" for="quantity">코인 가격</label>
	                            <input type="text" id="trade_coinPrice_1" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <label class="control-label" for="quantity">총 자산</label>
	                            <input type="text" id="trade_totalPrice_1" disabled="" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                        	<label class="control-label" for="status">수익률</label>
	                            <input type="text" id="trade_profitRate_1" disabled="" class="form-control ">
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="col-sm-1">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_exchangeName_2" disabled="" value="바낸" class="form-control hnas-">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_coinSymbol_2" disabled="" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_quantity_2" disabled="" class="form-control">
	                        </div>
	                    </div>
	                     <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_coinPrice_2" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_totalPrice_2" disabled="" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2 hide">
	                        <div class="form-group has-success">
	                       		<input type="text" id="trade_profitRate_2" disabled="" class="form-control">
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="col-sm-1">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_exchangeName_3" disabled="" value="바낸" class="form-control hnas-">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_coinSymbol_3" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_quantity_3" class="form-control">
	                        </div>
	                    </div>
	                     <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_coinPrice_3" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_totalPrice_3" disabled="" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                       		<input type="text" id="trade_profitRate_3" disabled="" class="form-control">
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="col-sm-1">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_exchangeName_4" disabled="" value="업비트" class="form-control hnas-">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_coinSymbol_4" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_quantity_4" class="form-control">
	                        </div>
	                    </div>
	                     <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_coinPrice_4" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <input type="text" id="trade_totalPrice_4" disabled="" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success hide">
	                        	<input type="text" id="trade_profitRate_4" disabled="" class="form-control">
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                            <label class="control-label" for="quantity">총 수익금</label>
	                            <input type="text" id="trade_totalPrice_5" disabled="" class="form-control">
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
	                        <div class="form-group has-success">
	                        	<label class="control-label" for="status">총 수익률</label>
	                            <input type="text" id="trade_profitRate_5" disabled="" class="form-control ">
	                        </div>
	                    </div>
	                    <div class="col-sm-2 pull-right">
			           		<button type="button" class="btn btn-primary" id="saveTradeBtn">저장</button> 
	                    </div>
	                    <div class="col-sm-2 pull-right">
	                    	<button type="button" class="btn btn-primary" id="setTotalProfitBtn">총 수익 계산</button> 
	                    </div>
	                </div>
           	 	</div>
            </div>
        </div>
</div>