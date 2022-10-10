<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<c:set var="i" value="0" />
		<c:forEach var="prod" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<div class="row">
			  <div class="col-sm-6 col-md-4">
			    <div class="thumbnail">
			      <img src="..." alt="...">
			      <div class="caption">
			      	<p class="hidden">${prod.prodNo}</p>
			        <h3>${prod.prodName }</h3>
			        <p>...</p>
			        <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
			      </div>
			    </div>
			  </div>
			</div>
		</c:forEach>
	
</body>
</html>