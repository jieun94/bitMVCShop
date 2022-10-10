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
			//============= "���ų���"  Event ���� =============
			$(function() {
				$( "button.btn.btn-default:contains('���ų���')" ).on("click" , function() {
					self.location = "/purchase/listPurchase"
				});
			});	
			
			//============= "��ǰ���"  Event ���� =============
			$(function() {
				$( "button.btn.btn-default:contains('��ǰ���')" ).on("click" , function() {
					self.location = "/product/listProduct"
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
		       <h3 class=" text-info">��ǰ����</h3>
		       <h5 class="text-muted">������ ���� ���Ű� �Ǿ����ϴ�.</h5>
		    </div>
	
			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>��ǰ��</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.purchaseProd.prodName}</div>
			</div>
			
			<hr/>
			
			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>�����ھ��̵�</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.buyer.userId}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>���Ź��</strong></div>
				<div class="col-xs-8 col-md-4">
					<c:if test="${purchase.paymentOption == 1}">
						���ݱ���
					</c:if>
					<c:if test="${purchase.paymentOption == 2}">
						�ſ뱸��
					</c:if>
				</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>���ż���</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.tranNum}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>�������̸�</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.receiverName}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>�����ڿ���ó</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.receiverPhone}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>�������ּ�</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.divyAddr}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>���ſ�û����</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.divyRequest}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>����������</strong></div>
				<div class="col-xs-8 col-md-4">${purchase.divyDate}</div>
			</div>
			
			<hr/>

			<div class="row">
		  		<div class="col-md-12 text-right ">
		  			<button type="button" class="btn btn-default">���ų���</button>
		  			<button type="button" class="btn btn-default">��ǰ���</button>
		  		</div>
			</div>
		
			<br/>
			
		</div>
	</body>
</html>