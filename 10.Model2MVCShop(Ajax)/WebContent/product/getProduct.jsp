<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		
		<!-- ���� : http://getbootstrap.com/css/   ���� -->
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
	   
	   
	   	<!-- jQuery UI toolTip ��� CSS-->
	  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	 	<!-- jQuery UI toolTip ��� JS-->
	  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		
		<!--  ///////////////////////// CSS ////////////////////////// -->
		<style>
			body {
				padding-top : 50px;
			}
	    </style>
	    
	    <!--  ///////////////////////// JavaScript ////////////////////////// -->
		<script type="text/javascript">
			//============= "����"  Event ���� =============
			$(function() {
				//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$( "button.btn.btn-primary" ).on("click" , function() {
					self.location = "/purchase/addPurchase?prod_no=${prod.prodNo}"
				});
			});	
			
			//============= "����"  Event ���� =============
			$(function() {
				//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
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
		   	
			<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
			<div class="container">
			
				<div class="page-header">
			       <h3 class=" text-info">��ǰ����ȸ</h3>
			    </div>
			    
				<div class="row">
			  		<div class="col-xs-4 col-md-2"><strong>�� ǰ ��</strong></div>
					<div class="col-xs-7 col-md-4">${prod.prodName}</div>
					<div class="col-xs-1 col-md-5 text-right">${prod.purchaseCount}�� ����</div>
				</div>
				
				<hr/>
				
				<div class="row">
			  		<div class="col-xs-4 col-md-2"><strong>��ǰ������</strong></div>
					<div class="col-xs-8 col-md-4">${prod.prodDetail}</div>
				</div>
				
				<hr/>
				
				<div class="row">
			  		<div class="col-xs-4 col-md-2"><strong>��������</strong></div>
					<div class="col-xs-8 col-md-4">${prod.manuDate}</div>
				</div>
				
				<hr/>
				
				<div class="row">
			  		<div class="col-xs-4 col-md-2"><strong>��  ��</strong></div>
					<div class="col-xs-8 col-md-4">${prod.price}</div>
				</div>
				
				<hr/>
				
				<div class="row">
			  		<div class="col-xs-4 col-md-2"><strong>��  ��</strong></div>
					<div class="col-xs-8 col-md-4">${prod.prodNum}</div>
				</div>
				
				<hr/>
				
				<div class="row">
			  		<div class="col-xs-4 col-md-2"><strong>��ǰ�̹���</strong></div>
			  		<c:if test="${prod.fileName != null}">
						<div class="col-xs-8 col-md-4"><img src="/images/uploadFiles/${prod.fileName}"/></div>
					</c:if>
					<c:if test="${prod.fileName == null}">
						<div class="col-xs-8 col-md-4">�̹��� ����</div>
					</c:if>
				</div>
				
				<hr/>
				
				<div class="row">
			  		<div class="col-md-12 text-right ">
			  			<c:if test="${user.userId !=null }">
			  				<c:if test="${prod.prodNum != 0}">
			  					<button type="button" class="btn btn-primary">����</button>
			  				</c:if>
			  			</c:if>
			  			<button type="button" class="btn btn-default">����</button>
			  		</div>
				</div>
			
				<br/>
				
				
				<jsp:include page="../review/listReview.jsp"></jsp:include>
			</div>
		</body>
</html>