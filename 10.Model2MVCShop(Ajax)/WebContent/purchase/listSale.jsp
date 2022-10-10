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
			//�˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScript �̿�  
			function fncGetUserList(currentPage) {
				$("#currentPage").val(currentPage)
				$("form").attr("method" , "POST").attr("action" , "/purchase/listSale").submit();
			}
			
			$(function() {
				$(".ct_list_pop:nth-child(2n+2)" ).css("background-color" , "whitesmoke");
			});	
			
			//============= userId �� ȸ����������  Event  ó��(Click) =============	
			 $(function() {
				$( "td:nth-child(5):contains('����ϱ�')" ).on("click" , function() {
					var tranNo = $(this).text().trim().substring(0,5);
					self.location ="/purchase/updateTranCode?tranNo="+tranNo+"&tranCode=2";
				});
				
				//==> userId LINK Event End User ���� ���ϼ� �ֵ��� 
				$( "td:nth-child(5):contains('����ϱ�')" ).css("color" , "red");
				
			});	
			
			//============= userId �� ȸ����������  Event  ó��(Click) =============	
			 $(function() {
				$( "td:nth-child(2)" ).on("click" , function() {
					var tranNo = $(this).text().trim().substring(0,5);
					self.location ="/purchase/getPurchase?tranNo="+tranNo;
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
		
			<div class="page-header text-info">
		       	<h3>�Ǹų��� ��ȸ</h3>
		    </div>
				    
	    <!-- table ���� �˻� Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
		    	</p>
		    </div>
		    
		</div>
		
	    <div class="col-md-6 text-right">
		    <form class="form-inline" name="detailForm">

<%-- 			<div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>ȸ����</option>
					</select>
			  	</div>
			  
				<div class="form-group">
				    <label class="sr-only" for="searchKeyword">�˻���</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="�˻���"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				</div>
			  
				<button type="button" class="btn btn-default">�˻�</button>
 --%>			  
				<!-- PageNavigation ���� ������ ���� ������ �κ� -->
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<input type="hidden" id="prodNo" name="prodNo" value="${param.prodNo}"/>
			  
			</form>
    	</div>
		<!-- table ���� �˻� end /////////////////////////////////////-->
	
		<!--  table Start /////////////////////////////////////-->
		<table class="table table-hover table-striped" >
      
	        <thead>
	          <tr>
	            <th class="text-center">No</th>
	            <th align="left" >��ǰ��</th>
	            <th align="left">������</th>
	            <th align="left">����ּ�</th>
	            <th align="left">�������</th>
	          </tr>
	        </thead>

		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="purchase" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
				<td align="center">${ i }</td>
				<td align="left"  title="Click : �ֹ����� Ȯ��">
					<p class="hidden">${purchase.tranNo}</p>
					${purchase.purchaseProd.prodName}
					<%-- <a href="/purchase/getProduct?prodNo=${purchase.purchaseProd.prodNo}&menu=manage"></a> --%>
				</td>
				<td align="left">${purchase.receiverName}</td>
				<td align="left">${purchase.divyAddr}</td>
				<td align="left">
					<p class="hidden">${purchase.tranNo}</p>
					<c:choose>
						<c:when test="${purchase.tranCode=='1  '}">
							����ϱ�
						</c:when>
						<c:when test="${purchase.tranCode=='2  '}">
							�����
						</c:when>
						<c:when test="${purchase.tranCode=='3  '}">
							��ۿϷ�
						</c:when>
						<c:when test="${purchase.tranCode=='4  '}">
							�����ۼ��Ϸ�
						</c:when>
						<c:otherwise>
							�Ǹ���
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
 	</div>
 	<!--  ȭ�鱸�� div End /////////////////////////////////////-->
 	
	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->

	</body>
</html>