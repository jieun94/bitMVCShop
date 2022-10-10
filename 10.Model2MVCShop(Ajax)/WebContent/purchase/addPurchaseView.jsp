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
	  	
	  	<!-- i'm port API CDN -->
		<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	  	
		
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
		
			function fncAddPurchase() {
				//Form ��ȿ�� ����
				var maxNum =$("input[name='maxNum']").val();
				var tranNum =$("input[name='tranNum']").val();
				var price = ${prod.price} * tranNum;
				var payment = $("select[name='paymentOption']").val();
				//alert(payment);
				if (payment == '1') {
					payment = 'trans';
				} else if (payment =='2') {
					payment = 'card';
				} else if (payment =='3') {
					payment = 'vbank';
				}
				//alert(tranNum+">"+maxNum);
				//alert(price);
				if(parseInt(tranNum) > parseInt(maxNum)){
					alert("�ִ� ������ �ʰ��Ͽ����ϴ�. ���� ����"+maxNum+"�� �Դϴ�.");
					return;
				} else {
					//alert(price);
					IMP.init('iamport');
	
					IMP.request_pay({
					    pg : 'inicis', // version 1.1.0���� ����.
					    pay_method : payment,
					    merchant_uid : 'merchant_' + new Date().getTime(),
					    name : '�ֹ���:�����׽�Ʈ',
					    amount : price, //�Ǹ� ����
					    buyer_email : 'iamport@siot.do',
					    buyer_name : '�Ⱥ���',
					    buyer_tel : '010-1234-5678',
					    buyer_addr : '����Ư���� ������ �Ｚ��',
					    buyer_postcode : '123-456'
					}, function(rsp) {
					    if ( rsp.success ) {
					        var msg = '������ �Ϸ�Ǿ����ϴ�.';
					        msg += '����ID : ' + rsp.imp_uid;
					        msg += '���� �ŷ�ID : ' + rsp.merchant_uid;
					        msg += '���� �ݾ� : ' + rsp.paid_amount;
					        msg += 'ī�� ���ι�ȣ : ' + rsp.apply_num;
					        
					        $("form").attr("method" , "POST").attr("action" , "/purchase/addPurchase").submit();
					    } else {
					        var msg = '������ �����Ͽ����ϴ�.';
					        msg += '�������� : ' + rsp.error_msg;
					    }
					    alert(msg);
					});
					
				}
				//document.addPurchase.submit();
				
			}
			
			$(function() {
				//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				//==> 1 �� 3 ��� ���� : $("tagName.className:filter�Լ�") �����.	
				$( "button.btn.btn-primary" ).on("click" , function() {
					//Debug..
					//alert(  $( "td.ct_btn01:contains('����')" ).html() );
					fncAddPurchase();
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
		
			<div class="page-header text-left">
				<h3 class=" text-info">��ǰ����</h3>
			</div>
			
			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>��ǰ��</strong></div>
				<div class="col-xs-8 col-md-4">${prod.prodName}</div>
			</div>
			
			<br/>
			
			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>��������</strong></div>
				<div class="col-xs-8 col-md-4">${prod.manuDate}</div>
			</div>
			
			<br/>
			
			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>����</strong></div>
				<div class="col-xs-8 col-md-4">${prod.price}</div>
			</div>
			
			<br/>
			
			<div class="row">
		  		<div class="col-xs-4 col-md-2"><strong>�����ھ��̵�</strong></div>
				<div class="col-xs-8 col-md-4">${user.userId}</div>
			</div>
			
			<br/>
			
			<!-- form Start /////////////////////////////////////-->
			<form class="form-horizontal">
				
				<input type="hidden" name="prodNo" value="${prod.prodNo}" />
				<input type="hidden" name="maxNum" value="${prod.prodNum}" />
				<input type="hidden" name="buyerId" value="${user.userId}" />
			
				<div class="form-group">
				    <label for="paymentOption" class="col-sm-3 col-md-2 control-label text-left">���Ź��</label>
				    <div class="col-sm-4 col-md-10">
				    	<select 	name="paymentOption"		class="form-control" >
							<option value="1" selected="selected">�ǽð�������ü</option>
							<option value="2">�ſ뱸��</option>
							<option value="3">�������</option>
						</select>
				    </div>
				</div>
				
				<div class="form-group">
				    <label for="tranNum" class="col-sm-3 col-md-2 control-label text-left">���ż���</label>
				    <div class="col-sm-4 col-md-10">
				      <input type="number" class="form-control" id="tranNum" name="tranNum" value="1">
				    </div>
				</div>
				
				<div class="form-group">
				    <label for="receiverName" class="col-sm-3 col-md-2 control-label text-left">�������̸�</label>
				    <div class="col-sm-4 col-md-10">
				      <input type="text" class="form-control" id="receiverName" name="receiverName" value="${user.userName}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="receiverPhone" class="col-sm-3 col-md-2 control-label text-left">�����ڿ���ó</label>
				    <div class="col-sm-4 col-md-10">
				      <input type="text" class="form-control" id="receiverPhone" name="receiverPhone" value="${user.phone}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyAddr" class="col-sm-3 col-md-2 control-label text-left">�������ּ�</label>
				    <div class="col-sm-4 col-md-10">
				      <input type="text" class="form-control" id="divyAddr" name="divyAddr" value="${user.addr}">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyRequest" class="col-sm-3 col-md-2 control-label text-left">���ſ�û����</label>
				    <div class="col-sm-4 col-md-10">
				      <input type="text" class="form-control" id="divyRequest" name="divyRequest">
				    </div>
				</div>

				<div class="form-group">
				    <label for="divyDate" class="col-sm-3 col-md-2 control-label text-left">����������</label>
				    <div class="col-sm-4 col-md-10">
				      <input type="date" class="form-control" id="divyDate" name="divyDate">
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