<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <script src="<c:url value='/js/views/user/mypage.js'/>"></script> --%>

<div class="row">
	<div class="col-lg-9">
	    <div class="ibox float-e-margins">
	        <div class="ibox-title">
	            <span class="label label-primary pull-right">2018.05.09</span>
	            <h5>총 자산</h5>
	        </div>
	        <div class="ibox-content">
	
	            <div class="row">
	                <div class="col-md-6">
	                    <h1 class="no-margins">5,500,000 원</h1>
	                    <div class="font-bold text-navy">44% <i class="fa fa-level-up"></i> <small>Rapid pace</small></div>
	                </div>
	                <!-- <div class="col-md-6">
	                    <h1 class="no-margins">206,12</h1>
	                    <div class="font-bold text-navy">22% <i class="fa fa-level-up"></i> <small>Slow pace</small></div>
	                </div> -->
	            </div>
	        </div>
	    </div>
	</div>
</div>
<div class="row">
	<div class="col-lg-3">
	    <div class="ibox float-e-margins">
	        <div class="ibox-title">
	            <span class="label label-success pull-right">Total</span>
	            <h5>총 수익</h5>
	        </div>
	        <div class="ibox-content">
	            <h1 class="no-margins">386,200</h1>
	            
	            <div class="stat-percent font-bold text-success">98% <i class="fa fa-level-down"></i></i></div>
	            <!-- <small>Total views</small> -->
	        </div>
	    </div>
	</div>
	<div class="col-lg-3">
	    <div class="ibox float-e-margins">
	        <div class="ibox-title">
	            <span class="label label-info pull-right">Monthly</span>
	            <h5>월간 수익</h5>
	        </div>
	        <div class="ibox-content">
	            <h1 class="no-margins">80,800</h1>
	            <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i></div>
	            <!-- <small>New orders</small> -->
	        </div>
	    </div>
	</div>
	<div class="col-lg-3">
	    <div class="ibox float-e-margins">
	        <div class="ibox-title">
	       		<span class="label label-danger pull-right">Daily</span>
	            <h5>오늘의 수익</h5>
	        </div>
	        <div class="ibox-content">
	            <h1 class="no-margins">386,200</h1>
	            <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i></div>
	            <!-- <small>Total views</small> -->
	        </div>
	    </div>
	</div>
</div>

<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>FooTable with row toggler, sorting and pagination</h5>

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

                            <table class="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded">
                                <thead>
                                <tr>

                                    <th data-toggle="true" class="footable-visible footable-first-column footable-sortable">Project<span class="footable-sort-indicator"></span></th>
                                    <th class="footable-visible footable-sortable">Name<span class="footable-sort-indicator"></span></th>
                                    <th class="footable-visible footable-sortable">Phone<span class="footable-sort-indicator"></span></th>
                                    <th data-hide="all" class="footable-sortable" style="display: none;">Company<span class="footable-sort-indicator"></span></th>
                                    <th data-hide="all" class="footable-sortable" style="display: none;">Completed<span class="footable-sort-indicator"></span></th>
                                    <th data-hide="all" class="footable-sortable" style="display: none;">Task<span class="footable-sort-indicator"></span></th>
                                    <th data-hide="all" class="footable-sortable" style="display: none;">Date<span class="footable-sort-indicator"></span></th>
                                    <th class="footable-visible footable-last-column footable-sortable">Action<span class="footable-sort-indicator"></span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="footable-even" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Project - This is example of project</td>
                                    <td class="footable-visible">Patrick Smith</td>
                                    <td class="footable-visible">0800 051213</td>
                                    <td style="display: none;">Inceptos Hymenaeos Ltd</td>
                                    <td style="display: none;"><span class="pie">0.52/1.561</span></td>
                                    <td style="display: none;">20%</td>
                                    <td style="display: none;">Jul 14, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-odd" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Alpha project</td>
                                    <td class="footable-visible">Alice Jackson</td>
                                    <td class="footable-visible">0500 780909</td>
                                    <td style="display: none;">Nec Euismod In Company</td>
                                    <td style="display: none;"><span class="pie">6,9</span></td>
                                    <td style="display: none;">40%</td>
                                    <td style="display: none;">Jul 16, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-even" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Betha project</td>
                                    <td class="footable-visible">John Smith</td>
                                    <td class="footable-visible">0800 1111</td>
                                    <td style="display: none;">Erat Volutpat</td>
                                    <td style="display: none;"><span class="pie">3,1</span></td>
                                    <td style="display: none;">75%</td>
                                    <td style="display: none;">Jul 18, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-odd" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Gamma project</td>
                                    <td class="footable-visible">Anna Jordan</td>
                                    <td class="footable-visible">(016977) 0648</td>
                                    <td style="display: none;">Tellus Ltd</td>
                                    <td style="display: none;"><span class="pie">4,9</span></td>
                                    <td style="display: none;">18%</td>
                                    <td style="display: none;">Jul 22, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-even" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Alpha project</td>
                                    <td class="footable-visible">Alice Jackson</td>
                                    <td class="footable-visible">0500 780909</td>
                                    <td style="display: none;">Nec Euismod In Company</td>
                                    <td style="display: none;"><span class="pie">6,9</span></td>
                                    <td style="display: none;">40%</td>
                                    <td style="display: none;">Jul 16, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-odd" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Project
                                        <small>This is example of project</small>
                                    </td>
                                    <td class="footable-visible">Patrick Smith</td>
                                    <td class="footable-visible">0800 051213</td>
                                    <td style="display: none;">Inceptos Hymenaeos Ltd</td>
                                    <td style="display: none;"><span class="pie">0.52/1.561</span></td>
                                    <td style="display: none;">20%</td>
                                    <td style="display: none;">Jul 14, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-even" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Gamma project</td>
                                    <td class="footable-visible">Anna Jordan</td>
                                    <td class="footable-visible">(016977) 0648</td>
                                    <td style="display: none;">Tellus Ltd</td>
                                    <td style="display: none;"><span class="pie">4,9</span></td>
                                    <td style="display: none;">18%</td>
                                    <td style="display: none;">Jul 22, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-odd" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Project
                                        <small>This is example of project</small>
                                    </td>
                                    <td class="footable-visible">Patrick Smith</td>
                                    <td class="footable-visible">0800 051213</td>
                                    <td style="display: none;">Inceptos Hymenaeos Ltd</td>
                                    <td style="display: none;"><span class="pie">0.52/1.561</span></td>
                                    <td style="display: none;">20%</td>
                                    <td style="display: none;">Jul 14, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-even" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Alpha project</td>
                                    <td class="footable-visible">Alice Jackson</td>
                                    <td class="footable-visible">0500 780909</td>
                                    <td style="display: none;">Nec Euismod In Company</td>
                                    <td style="display: none;"><span class="pie">6,9</span></td>
                                    <td style="display: none;">40%</td>
                                    <td style="display: none;">Jul 16, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-odd" style="">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Betha project</td>
                                    <td class="footable-visible">John Smith</td>
                                    <td class="footable-visible">0800 1111</td>
                                    <td style="display: none;">Erat Volutpat</td>
                                    <td style="display: none;"><span class="pie">3,1</span></td>
                                    <td style="display: none;">75%</td>
                                    <td style="display: none;">Jul 18, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-even" style="display: none;">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Gamma project</td>
                                    <td class="footable-visible">Anna Jordan</td>
                                    <td class="footable-visible">(016977) 0648</td>
                                    <td style="display: none;">Tellus Ltd</td>
                                    <td style="display: none;"><span class="pie">4,9</span></td>
                                    <td style="display: none;">18%</td>
                                    <td style="display: none;">Jul 22, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-odd" style="display: none;">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Alpha project</td>
                                    <td class="footable-visible">Alice Jackson</td>
                                    <td class="footable-visible">0500 780909</td>
                                    <td style="display: none;">Nec Euismod In Company</td>
                                    <td style="display: none;"><span class="pie">6,9</span></td>
                                    <td style="display: none;">40%</td>
                                    <td style="display: none;">Jul 16, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-even" style="display: none;">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Project
                                        <small>This is example of project</small>
                                    </td>
                                    <td class="footable-visible">Patrick Smith</td>
                                    <td class="footable-visible">0800 051213</td>
                                    <td style="display: none;">Inceptos Hymenaeos Ltd</td>
                                    <td style="display: none;"><span class="pie">0.52/1.561</span></td>
                                    <td style="display: none;">20%</td>
                                    <td style="display: none;">Jul 14, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr class="footable-odd" style="display: none;">
                                    <td class="footable-visible footable-first-column"><span class="footable-toggle"></span>Gamma project</td>
                                    <td class="footable-visible">Anna Jordan</td>
                                    <td class="footable-visible">(016977) 0648</td>
                                    <td style="display: none;">Tellus Ltd</td>
                                    <td style="display: none;"><span class="pie">4,9</span></td>
                                    <td style="display: none;">18%</td>
                                    <td style="display: none;">Jul 22, 2013</td>
                                    <td class="footable-visible footable-last-column"><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="5" class="footable-visible">
                                        <ul class="pagination pull-right"><li class="footable-page-arrow disabled"><a data-page="first" href="#first">«</a></li><li class="footable-page-arrow disabled"><a data-page="prev" href="#prev">‹</a></li><li class="footable-page active"><a data-page="0" href="#">1</a></li><li class="footable-page"><a data-page="1" href="#">2</a></li><li class="footable-page-arrow"><a data-page="next" href="#next">›</a></li><li class="footable-page-arrow"><a data-page="last" href="#last">»</a></li></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>

                        </div>
                    </div>
                </div>
            </div> 
            
             <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function() {

            $('.footable').footable();
            $('.footable2').footable();
        });

    </script>
                