<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="jquery.tablesorter.min.js"></script>

<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScript 이용  
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
	
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm"  action="/product/listProduct?menu=${param.menu}" method="post">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					<c:if test="${param.menu=='manage'}">상품관리</c:if>
					<c:if test="${param.menu=='search'}">상품 목록조회</c:if>
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<c:if test="${param.menu=='manage'}">
					<option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
				</c:if>
				<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
				<option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
			</select>
			<input 	type="text" name="searchKeyword"  value="${! empty search.searchKeyword ? search.searchKeyword : ""}" 
							class="ct_input_g" style="width:200px; height:19px" >
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetUserList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="5">
		전체  ${ resultPage.totalCount } 건수,	현재 ${ resultPage.currentPage} 페이지
		</td>
		<td colspan="5" align="right">
			<c:if test="${search.orderColumn==null}">
				<a href="/product/listProduct?menu=${param.menu}&orderColumn=prod_name&order=1">상품명 ▼</a>
				<a href="/product/listProduct?menu=${param.menu}&orderColumn=price&order=1">가격 ▼</a>
				<a href="/product/listProduct?menu=${param.menu}&orderColumn=purchaseCount&order=1">판매량 ▼</a>
			</c:if>
			<c:if test="${search.orderColumn!=null}">
				<a href="/product/listProduct?menu=${param.menu}&orderColumn=prod_name&order=${param.orderColumn=='prod_name' && param.order=='1' ? "2" : "1" }">
					상품명
					<c:choose>
						<c:when test="${ param.orderColumn=='prod_name' && param.order=='1'}">▲</c:when>
						<c:otherwise>▼</c:otherwise>
					</c:choose>
				</a>
				<a href="/product/listProduct?menu=${param.menu}&orderColumn=price&order=${param.orderColumn=='price' && param.order=='1' ? "2" : "1" }">
				가격
				<c:choose>
						<c:when test="${ param.orderColumn=='price' && param.order=='1'}">▲</c:when>
						<c:otherwise>▼</c:otherwise>
					</c:choose>
				</a>
				<a href="/product/listProduct?menu=${param.menu}&orderColumn=purchaseCount&order=${param.orderColumn=='purchaseCount' && param.order=='1' ? "2" : "1" }">
				판매량
				<c:choose>
						<c:when test="${ param.orderColumn=='purchaseCount' && param.order=='1'}">▲</c:when>
						<c:otherwise>▼</c:otherwise>
					</c:choose>
				</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<c:if test="${param.menu=='manage'}">
			<td class="ct_list_b" >등록일</td>
			<td class="ct_line02"></td>
		</c:if>
		<td class="ct_list_b">현재상태</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="i" value="0"/>
	<c:forEach var="prod" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center">${ i }</td>
			<td></td>
			<td align="left">
				<c:if test="${param.menu=='manage'}">
					<c:choose>
						<c:when test="${prod.prodNum!=0}">
							<a href="/product/getProduct?prodNo=${prod.prodNo}&menu=manage">${prod.prodName}</a>
						</c:when>
						<c:otherwise>
							${prod.prodName}
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${param.menu=='search'}">
					<c:choose>
						<c:when test="${prod.prodNum==0}">
							${prod.prodName}
						</c:when>
						<c:otherwise>
							
							<a href="/product/getProduct?prodNo=${prod.prodNo}&menu=search">${prod.prodName}</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</td>
			<td></td>
			<td align="left">${prod.price}</td>
			<td></td>
			<c:if test="${param.menu=='manage'}">
				<td align="left">${prod.regDate}</td>
				<td></td>
			</c:if>	
			<td align="left">
				<c:if test="${param.menu=='manage'}">
						<a href="/purchase/listSale?prodNo=${prod.prodNo}">배송관리</a>
					<%-- <c:choose>
						<c:when test="${prod.proTranCode==1}">
							구매완료 <a href="/updateTranCodeByProd.do?tranNo=${prod.tranNo}&tranCode=2">배송하기</a>
						</c:when>
						<c:when test="${prod.proTranCode==2}">
							배송중
						</c:when>
						<c:when test="${prod.proTranCode==3}">
							배송완료
						</c:when>
						<c:otherwise>
							판매중
						</c:otherwise>
					</c:choose> --%>
				</c:if>
				<c:if test="${param.menu=='search'}">
					<c:choose>
						<c:when test="${prod.prodNum==0}">
							재고없음
						</c:when>
						<c:otherwise>
							${prod.prodNum}개 남음
						</c:otherwise>
					</c:choose>
				</c:if>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
</table>

<%-- <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<c:if test="${resultPage.currentPage <= resultPage.pageUnit }">◀ 이전</c:if>
			<c:if test="${resultPage.currentPage > resultPage.pageUnit }">
				<a href="javascript:fncGetProdList('${resultPage.currentPage-1}')">◀ 이전</a>
			</c:if>
			<c:forEach var="i" begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
				<a href="javascript:fncGetProdList('${i}');">${i}</a>
			</c:forEach>
			<c:if test="${resultPage.endUnitPage>=resultPage.maxPage}">이후 ▶</c:if>
			<c:if test="${resultPage.endUnitPage<resultPage.maxPage}">
				<a href="javascript:fncGetProdList('${resultPage.endUnitPage+1}')">이후 ▶</a>
			</c:if>
    	</td>
	</tr>
</table> --%>
<!--  페이지 Navigator 끝 -->
<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
	
			<jsp:include page="../common/pageNavigator.jsp"/>	
			
    	</td>
	</tr>
</table> 
<!-- PageNavigation End... -->
</form>
</div>

</body>
</html>