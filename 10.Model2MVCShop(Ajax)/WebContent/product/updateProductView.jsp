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
		
			function fncAddProduct(){
				//Form 유효성 검증
				var name=$("input[name='prodName']").val();
				var detail=$("input[name='prodDetail']").val();
				var manuDate=$("input[name='manuDate']").val();
				var price=$("input[name='price']").val();
				var prodNum=$("input[name='prodNum']").val();
			
				if(name == null || name.length<1){
					alert("상품명은 반드시 입력하여야 합니다.");
					return;
				}
				if(detail == null || detail.length<1){
					alert("상품상세정보는 반드시 입력하여야 합니다.");
					return;
				}
				if(manuDate == null || manuDate.length<1){
					alert("제조일자는 반드시 입력하셔야 합니다.");
					return;
				}
				if(price == null || price.length<1){
					alert("가격은 반드시 입력하셔야 합니다.");
					return;
				}
				if(prodNum == null || prodNum.length<1){
					alert("수량은 반드시 입력하셔야 합니다.");
					return;
				}
					
				//document.detailForm.action='/product/updateProduct';
				//document.detailForm.submit();
				$("form").attr("method" , "POST").attr("action" , "/product/updateProduct").submit();
			}
			
			//============= "수정"  Event 연결 =============
			$(function() {
				$( "button.btn.btn-primary:contains('수정')" ).on("click" , function() {
					fncAddProduct();
				});
			});	
			
			//============= "이전"  Event 연결 =============
			$(function() {
				//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$( ".btn-default" ).on("click" , function() {
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
		
			<div class="page-header text-center">
		       <h3 class=" text-info">상품정보 수정</h3>
		    </div>
		
		    <!-- form Start /////////////////////////////////////-->
			<form class="form-horizontal">
			
				<input type="hidden" name="prodNo" value="${prod.prodNo}"/>
				
			  	<div class="form-group">
				    <label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">상 품 명</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="prodName" name="prodName" value="${prod.prodName}">
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">상품상세정보</label>
				    <div class="col-sm-4">
				    	<input type="text" class="form-control" id="prodDetail" name="prodDetail" value="${prod.prodDetail}">
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">제조일자</label>
				    <div class="col-sm-4">
				    	<input type="date" class="form-control" id="manuDate" name="manuDate" value="${prod.manuDate}">
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label for="price" class="col-sm-offset-1 col-sm-3 control-label">가격</label>
				    <div class="col-sm-4">
				    	<input type="text" class="form-control" id="price" name="price" value="${prod.price}">
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label for="prodNum" class="col-sm-offset-1 col-sm-3 control-label">수량</label>
				    <div class="col-sm-4">
				    	<input type="text" class="form-control" id="prodNum" name="prodNum" value="${prod.prodNum}">
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label for="fileName" class="col-sm-offset-1 col-sm-3 control-label">상품이미지</label>
				    <div class="col-sm-4">
				    	<input type="text" class="form-control" id="fileName" name="fileName" 
				    		<c:if test="${prod.fileName!=null }">
								value="${prod.fileName}"
							</c:if>>
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
			    	<div class="col-sm-offset-4  col-sm-4 text-right">
			      		<button type="button" class="btn btn-primary"  >수정</button>
				  		<button type="button" class="btn btn-default"  >이전</button>
			    	</div>
			  	</div>
			</form>
		</div>
	</body>
</html>