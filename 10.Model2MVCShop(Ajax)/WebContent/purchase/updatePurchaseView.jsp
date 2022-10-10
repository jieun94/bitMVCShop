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
	   
		<!--  ///////////////////////// CSS ////////////////////////// -->
		<style>
			body {
	            padding-top : 50px;
	        }
	    </style>
	    
	     <!--  ///////////////////////// JavaScript ////////////////////////// -->
		<script type="text/javascript">
		
		$(function() {
			$( "button.btn.btn-primary" ).on("click" , function() {
				fncUpdatePurchase();
			});
		});	
		
		//============= "이전"  Event 연결 =============
		$(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-default" ).on("click" , function() {
				self.location = "javascript:history.go(-1)"
			});
		});	

		
		function fncUpdatePurchase(){
			//Form 유효성 검증
			var name=$("input[name='receiverName']").val();
			var addr=$("input[name='divyAddr']").val();
			var tranNum=$("input[name='tranNum']").val();
		
			if(name == null || name.length<1){
				alert("구매자명은 반드시 입력하여야 합니다.");
				return;
			}
			if(addr == null || addr.length<1){
				alert("주소는 반드시 입력하여야 합니다.");
				return;
			}
			if(tranNum == null || tranNum.length<1){
				alert("수량은 반드시 입력하셔야 합니다.");
				return;
			}
				
			//document.detailForm.action='/product/updateProduct';
			//document.detailForm.submit();
			$("form").attr("method" , "POST").attr("action" , "/purchase/updatePurchase").submit();
		}
		
		</script>
	</head>
	
	<body>
	
		<!-- ToolBar Start /////////////////////////////////////-->
		<jsp:include page="/layout/toolbar.jsp" />
	   	<!-- ToolBar End /////////////////////////////////////-->
		
		<!--  화면구성 div Start /////////////////////////////////////-->
		<div class="container">
		
			<div class="page-header text-center">
		       <h3 class=" text-info">구매정보수정 수정</h3>
		    </div>
		
		    <!-- form Start /////////////////////////////////////-->
			<form class="form-horizontal">
			
				<input type="hidden" name="tranNo" value="${purchase.tranNo}"/>
				
				<div class="form-group">
				    <label for="buyerId" class="col-sm-offset-1 col-sm-3 control-label">구매자아이디</label>
				    <div class="col-sm-4">
				    	${purchase.buyer.userId}
				    	<input type="hidden" name="buyerId" value="${purchase.buyer.userId}">
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">구매방법</label>
				    <div class="col-sm-4">
				    	<select 	name="paymentOption"		class="form-control" >
							<option value="1" ${!empty purchase.paymentOption && purchase.paymentOption=='1' ? "selected" : ""}>현금구매</option>
							<option value="2" ${!empty purchase.paymentOption && purchase.paymentOption=='2' ? "selected" : ""}>신용구매</option>
						</select>
				    </div>
			  	</div>

				<div class="form-group">
				    <label for="tranNum" class="col-sm-offset-1 col-sm-3 control-label">구매수량</label>
				    <div class="col-sm-4">
				    	${purchase.tranNum}
				      	<input type="hidden" class="form-control" id="tranNum" name="tranNum" value="${purchase.tranNum}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="receiverName" class="col-sm-offset-1 col-sm-3 control-label">구매자이름</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="receiverName" name="receiverName" value="${purchase.receiverName}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="receiverPhone" class="col-sm-offset-1 col-sm-3 control-label">구매자 연락처</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="receiverPhone" name="receiverPhone" value="${purchase.receiverPhone}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyAddr" class="col-sm-offset-1 col-sm-3 control-label">구매자주소</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="divyAddr" name="divyAddr" value="${purchase.divyAddr}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyRequest" class="col-sm-offset-1 col-sm-3 control-label">구매요청사항</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="divyRequest" name="divyRequest" value="${purchase.divyRequest}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyDate" class="col-sm-offset-1 col-sm-3 control-label">배송희망일자</label>
				    <div class="col-sm-4">
				      <input type="date" class="form-control" id="divyDate" name="divyDate" value="${purchase.divyDate}">
				    </div>
				</div>

			  	<div class="form-group">
			    	<div class="text-right">
			      		<button type="button" class="btn btn-primary"  >수정</button>
				  		<button type="button" class="btn btn-default"  >취소</button>
			    	</div>
			  	</div>
			
			</form>
		</div>

	</body>
</html>