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
			//검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScript 이용  
			function fncGetUserList(currentPage) {
				$("#currentPage").val(currentPage)
				$("form").attr("method" , "POST").attr("action" , "/purchase/listSale").submit();
			}
			
			$(function() {
				$(".ct_list_pop:nth-child(2n+2)" ).css("background-color" , "whitesmoke");
			});	
			
			//============= userId 에 회원정보보기  Event  처리(Click) =============	
			 $(function() {
				$( "td:nth-child(5):contains('배송하기')" ).on("click" , function() {
					var tranNo = $(this).text().trim().substring(0,5);
					self.location ="/purchase/updateTranCode?tranNo="+tranNo+"&tranCode=2";
				});
				
				//==> userId LINK Event End User 에게 보일수 있도록 
				$( "td:nth-child(5):contains('배송하기')" ).css("color" , "red");
				
			});	
			
			//============= userId 에 회원정보보기  Event  처리(Click) =============	
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
	   	
		<!--  화면구성 div Start /////////////////////////////////////-->
		<div class="container">
		
			<div class="page-header text-info">
		       	<h3>판매내역 조회</h3>
		    </div>
				    
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		</div>
		
	    <div class="col-md-6 text-right">
		    <form class="form-inline" name="detailForm">

<%-- 			<div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품명</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>회원명</option>
					</select>
			  	</div>
			  
				<div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				</div>
			  
				<button type="button" class="btn btn-default">검색</button>
 --%>			  
				<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<input type="hidden" id="prodNo" name="prodNo" value="${param.prodNo}"/>
			  
			</form>
    	</div>
		<!-- table 위쪽 검색 end /////////////////////////////////////-->
	
		<!--  table Start /////////////////////////////////////-->
		<table class="table table-hover table-striped" >
      
	        <thead>
	          <tr>
	            <th class="text-center">No</th>
	            <th align="left" >상품명</th>
	            <th align="left">구매자</th>
	            <th align="left">배송주소</th>
	            <th align="left">현재상태</th>
	          </tr>
	        </thead>

		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="purchase" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
				<td align="center">${ i }</td>
				<td align="left"  title="Click : 주문정보 확인">
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
							배송하기
						</c:when>
						<c:when test="${purchase.tranCode=='2  '}">
							배송중
						</c:when>
						<c:when test="${purchase.tranCode=='3  '}">
							배송완료
						</c:when>
						<c:when test="${purchase.tranCode=='4  '}">
							리뷰작성완료
						</c:when>
						<c:otherwise>
							판매중
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
 	</div>
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->

	</body>
</html>