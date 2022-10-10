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
		
		//============= "����"  Event ���� =============
		$(function() {
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-default" ).on("click" , function() {
				self.location = "javascript:history.go(-1)"
			});
		});	

		
		function fncUpdatePurchase(){
			//Form ��ȿ�� ����
			var name=$("input[name='receiverName']").val();
			var addr=$("input[name='divyAddr']").val();
			var tranNum=$("input[name='tranNum']").val();
		
			if(name == null || name.length<1){
				alert("�����ڸ��� �ݵ�� �Է��Ͽ��� �մϴ�.");
				return;
			}
			if(addr == null || addr.length<1){
				alert("�ּҴ� �ݵ�� �Է��Ͽ��� �մϴ�.");
				return;
			}
			if(tranNum == null || tranNum.length<1){
				alert("������ �ݵ�� �Է��ϼž� �մϴ�.");
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
		
		<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
		<div class="container">
		
			<div class="page-header text-center">
		       <h3 class=" text-info">������������ ����</h3>
		    </div>
		
		    <!-- form Start /////////////////////////////////////-->
			<form class="form-horizontal">
			
				<input type="hidden" name="tranNo" value="${purchase.tranNo}"/>
				
				<div class="form-group">
				    <label for="buyerId" class="col-sm-offset-1 col-sm-3 control-label">�����ھ��̵�</label>
				    <div class="col-sm-4">
				    	${purchase.buyer.userId}
				    	<input type="hidden" name="buyerId" value="${purchase.buyer.userId}">
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">���Ź��</label>
				    <div class="col-sm-4">
				    	<select 	name="paymentOption"		class="form-control" >
							<option value="1" ${!empty purchase.paymentOption && purchase.paymentOption=='1' ? "selected" : ""}>���ݱ���</option>
							<option value="2" ${!empty purchase.paymentOption && purchase.paymentOption=='2' ? "selected" : ""}>�ſ뱸��</option>
						</select>
				    </div>
			  	</div>

				<div class="form-group">
				    <label for="tranNum" class="col-sm-offset-1 col-sm-3 control-label">���ż���</label>
				    <div class="col-sm-4">
				    	${purchase.tranNum}
				      	<input type="hidden" class="form-control" id="tranNum" name="tranNum" value="${purchase.tranNum}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="receiverName" class="col-sm-offset-1 col-sm-3 control-label">�������̸�</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="receiverName" name="receiverName" value="${purchase.receiverName}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="receiverPhone" class="col-sm-offset-1 col-sm-3 control-label">������ ����ó</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="receiverPhone" name="receiverPhone" value="${purchase.receiverPhone}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyAddr" class="col-sm-offset-1 col-sm-3 control-label">�������ּ�</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="divyAddr" name="divyAddr" value="${purchase.divyAddr}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyRequest" class="col-sm-offset-1 col-sm-3 control-label">���ſ�û����</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="divyRequest" name="divyRequest" value="${purchase.divyRequest}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyDate" class="col-sm-offset-1 col-sm-3 control-label">����������</label>
				    <div class="col-sm-4">
				      <input type="date" class="form-control" id="divyDate" name="divyDate" value="${purchase.divyDate}">
				    </div>
				</div>

			  	<div class="form-group">
			    	<div class="text-right">
			      		<button type="button" class="btn btn-primary"  >����</button>
				  		<button type="button" class="btn btn-default"  >���</button>
			    	</div>
			  	</div>
			
			</form>
		</div>

	</body>
</html>