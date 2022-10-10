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
	        
	        .form-horizontal .control-label.text-left{
    			text-align: left;
			}
	        
	    </style>
	    
	    <!--  ///////////////////////// JavaScript ////////////////////////// -->
	    <script type="text/javascript">
	    
			function fncAddReview() {
				//Form 유효성 검증
				$("form").attr("method" , "POST").attr("action" , "/review/addReview").submit();
				
				alert("작성 완료!");
			}
			
			$(function() {
				$( "button.btn.btn-primary" ).on("click" , function() {
					//Debug..
					//alert(  $( "td.ct_btn01:contains('등록')" ).html() );
					fncAddReview();
				});
			});	
			
			//============= "이전"  Event 연결 =============
			$(function() {
				//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$( "button.btn.btn-default" ).on("click" , function() {
					self.location = "javascript:history.go(-1)"
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
		
			<div class="page-header text-left">
				<h3 class=" text-info">상품평 등록</h3>
			</div>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>상품명</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.purchaseProd.prodName}</div>
			</div>
			
			<br/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>구매자</strong></div>
				<div class="col-xs-8 col-md-4">${user.userName}</div>
			</div>
			
			<br/>
			
			<!-- form Start /////////////////////////////////////-->
			<form class="form-horizontal">
				
				<input type="hidden" name="tranNo" value="${purchase.tranNo}" />
				<input type="hidden" name="prodNo" value="${purchase.purchaseProd.prodNo}" />
				<input type="hidden" name="userId" value="${purchase.buyer.userId}" />
				
				<div class="form-group">
				    <label for="reviewName" class="col-sm-3 col-md-2 control-label text-left">제목</label>
				    <div class="col-sm-4 col-md-10">
				      <input type="text" class="form-control" id="reviewName" name="reviewName">
				    </div>
				</div>
			
				<div class="form-group">
				    <label for="reviewDetail" class="col-sm-3 col-md-2 control-label text-left">상품평</label>
				    <div class="col-sm-4 col-md-10">
				      	<input type="text" class="form-control" id="reviewDetail" name="reviewDetail">
				    </div>
				</div>

				<div class="form-group">
				    <label for="reviewFileName" class="col-sm-3 col-md-2 control-label text-left">상품이미지</label>
				    <div class="col-sm-4 col-md-10">
				      	<input type="text" class="form-control" id="reviewFileName" name="reviewFileName">
				    </div>
				</div>
				
			  	<div class="form-group">
			    	<div class="text-right">
			      		<button type="button" class="btn btn-primary"  >등록</button>
				  		<button type="button" class="btn btn-default"  >취소</button>
			    	</div>
			  	</div>
			</form>
		</div>
	</body>
</html>