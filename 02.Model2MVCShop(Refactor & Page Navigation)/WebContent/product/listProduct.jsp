<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.*"  %>

<%@ page import="com.model2.mvc.service.domain.*" %>
<%@ page import="com.model2.mvc.common.*" %>
<%@ page import="com.model2.mvc.common.util.*" %>

<%
	List<Product> list= (List<Product>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");
	//==> null 을 ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
%>


<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScript 이용  
	function fncGetProdList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
	
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm"  action="<% if( request.getParameter("menu").equals("manage") ) { %> 
									/listProduct.do?menu=manage
									<% } else { %>
									/listProduct.do?menu=search
									<% } %> " method="post">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					<% if( request.getParameter("menu").equals("manage") ) { %>
						상품 관리
					<% } else { %>
						상품 목록조회
					<% } %>
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
				<option value="0" <% if(searchCondition.equals("0")) { %>selected<% } %>>상품번호</option>
				<option value="1" <% if(searchCondition.equals("1")) { %>selected<% } %>>상품명</option>
			</select>
			<input 	type="text" name="searchKeyword"  value="<%=searchKeyword %>" 
							class="ct_input_g" style="width:200px; height:19px" >
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProdList('1');">검색</a>
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
		<td colspan="11" >
		전체  ${ resultPage.totalCount } 건수,	현재 ${ resultPage.currentPage} 페이지
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" >등록일</td>		
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<% 	
		for(int i=0; i<list.size(); i++) {
			Product prodVO = list.get(i);
	%>
	<tr class="ct_list_pop">
		<td align="center"><%= i + 1 %></td>
		<td></td>
		<td align="left">
		<% if( request.getParameter("menu").equals("manage") ) { %>
			<a href="/getProduct.do?prodNo=<%=prodVO.getProdNo() %>&menu=manage"><%=prodVO.getProdName()%></a>
		<% } else { %>
			<% if(prodVO.getProTranCode().contains("0")) { %>
				<a href="/getProduct.do?prodNo=<%=prodVO.getProdNo() %>&menu=search"><%=prodVO.getProdName()%></a>
			<% } else { %>
				<%=prodVO.getProdName()%>
			<% } %>
		<% } %>
		</td>
		<td></td>
		<td align="left"><%=prodVO.getPrice()%></td>
		<td></td>
		<td align="left"><%=prodVO.getRegDate()%></td>	
		<td></td>
		<td align="left">
		<% if(request.getParameter("menu").equals("manage")) {%>
			<% if(prodVO.getProTranCode().contains("1")) {%>
				구매완료 <a href="/updateTranCodeByProd.do?tranNo=<%= prodVO.getTranNo() %>&tranCode=2">배송하기</a>
			<% } else if(prodVO.getProTranCode().contains("2")) { %>
				배송중
			<% } else if(prodVO.getProTranCode().contains("3")) {%>	
				배송완료
			<% } else {%>
				판매중
			<% } %>
		<% } else { %>
			<% if(prodVO.getProTranCode().contains("0")) { %>
				판매중
			<% } else { %>
				재고없음
			<% } %>
		<% } %>
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetProdList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProdList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetProdList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>