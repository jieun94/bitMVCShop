<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		
		<!-- 참조 : http://getbootstrap.com/css/   참조 -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
		<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
		
		
		<!-- Bootstrap Dropdown Hover CSS -->
	   	<link href="/css/animate.min.css" rel="stylesheet">
	   	<link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
	    <!-- Bootstrap Dropdown Hover JS -->
	   	<script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	   
	   
	   	<!-- jQuery UI toolTip 사용 CSS-->
	  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	 	<!-- jQuery UI toolTip 사용 JS-->
	  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		
		<!--  ///////////////////////// CSS ////////////////////////// -->
		<style>
		  body {
	            padding-top : 50px;
	        }
	    </style>
	    
	    <!--  ///////////////////////// JavaScript ////////////////////////// -->
		<script type="text/javascript">
			//============= "구매내역"  Event 연결 =============
			$(function() {
				$( "button.btn.btn-default:contains('구매내역')" ).on("click" , function() {
					self.location = "/purchase/listPurchase"
				});
			});	
			
			//============= "상품목록"  Event 연결 =============
			$(function() {
				$( "button.btn.btn-default:contains('상품목록')" ).on("click" , function() {
					self.location = "/product/listProduct"
				});
			});	
		</script>
	    
	</head>

	<body>

		<!-- ToolBar Start /////////////////////////////////////-->
		<jsp:include page="/layout/toolbar.jsp" />
	   	<!-- ToolBar End /////////////////////////////////////-->
	   	
		<!--  화면구성 div Start /////////////////////////////////////-->
		<div class="container">
		
			<div class="page-header">
		       <h3 class=" text-info">상품구매</h3>
		       <h5 class="text-muted">다음과 같이 구매가 되었습니다.</h5>
		    </div>
	
			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>상품명</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.purchaseProd.prodName}</div>
			</div>
			
			<hr/>
			
			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>구매자아이디</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.buyer.userId}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>구매방법</strong></div>
				<div class="col-xs-8 col-md-4">
					<c:if test="${purchase.paymentOption == 1}">
						현금구매
					</c:if>
					<c:if test="${purchase.paymentOption == 2}">
						신용구매
					</c:if>
				</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>구매수량</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.tranNum}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>구매자이름</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.receiverName}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>구매자연락처</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.receiverPhone}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>구매자주소</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.divyAddr}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>구매요청사항</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.divyRequest}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>배송희망일자</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.divyDate}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-md-12 text-right ">
		  			<button type="button" class="btn btn-default">구매내역</button>
		  			<button type="button" class="btn btn-default">상품목록</button>
		  		</div>
			</div>
		
			<br/>
			
		</div>
	</body>
</html>