<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
	        
	        .padding-bottom {
	        	padding-bottom: 50px;
	        }
	        
	        .thumbnail>img {
	        	height: 200px;
	        }
	    </style>
	    
	    <!--  ///////////////////////// JavaScript ////////////////////////// -->
		<script type="text/javascript">
			// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScript 이용  
			function fncGetUserList(currentPage) {
				$("#currentPage").val(currentPage)
			   	$("form").attr("method" , "POST").attr("action" , "/product/listProduct?menu=${param.menu}").submit();
			}
			
			$(function() {
				$( "button.btn.btn-default:contains('검색')" ).on("click" , function() {
					fncGetUserList(1);
				});
				
				$( "td:nth-child(5) > i" ).on("click" , function() {
				//$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
				
					//Debug..
					//alert(  $( this ).text().trim() );
					
					//////////////////////////// 추가 , 변경된 부분 ///////////////////////////////////
					//self.location ="/user/getUser?userId="+$(this).text().trim();
					////////////////////////////////////////////////////////////////////////////////////////////
					//var prodNo = $(this).text().trim().substring(0,5);
					var prodNo = $(this).next().val();
					var menu = "${param.menu}";
					
					//alert(prodNum);
					
					$.ajax( 
							{
								url : "/product/json/getProduct/"+prodNo ,
								method : "GET" ,
								dataType : "json" ,
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
								success : function(JSONData , status) {
		
									//Debug...
									//alert(status);
									//Debug...
									//alert("JSONData : \n"+JSONData);
									
									var displayValue = "<h6>"
											+"상품명 : "+JSONData.prodName+"<br/>"
											+"상품 상세정보 : "+JSONData.prodDetail+"<br/>"
											+"제조일자 : "+JSONData.manuDate+"<br/>"
											+"가격 : "+JSONData.price+"<br/>"
											+"수량 : "+JSONData.prodNum+"<br/>"
											+"등록일자 : "+JSONData.regDate+"<br/>"
											+"</h6>";
									
									if (menu!="search") {
										
										displayValue+='<h6><a href="/product/getProduct?prodNo='
											+prodNo
											+'&menu=manage&currentPage=1'
											+'">수정</a></h6>';
									} else if(menu=="search" && JSONData.prodNum!=0) {
										displayValue+='<h6><a href="/product/getProduct?prodNo='
												+prodNo
												+'&menu=search&currentPage=1'
												+'">상세조회</a></h6>';
									}
									
									//Debug...									
									//alert(displayValue);
									$("h6").remove();
									$( "#"+prodNo+"" ).html(displayValue);
								}
						});
						////////////////////////////////////////////////////////////////////////////////////////////
					
				});
				
				$( "td:nth-child(6) > i" ).on("click" , function() {
					//$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
					
						//Debug..
						//alert(  $( this ).text().trim() );
						
						//////////////////////////// 추가 , 변경된 부분 ///////////////////////////////////
						//self.location ="/user/getUser?userId="+$(this).text().trim();
						////////////////////////////////////////////////////////////////////////////////////////////
						//var prodNo = $(this).text().trim().substring(0,5);
						var prodNo = $(this).next().val();
						var menu = "${param.menu}";
						
						//alert(prodNum);
						
						$.ajax( 
								{
									url : "/product/json/getProduct/"+prodNo ,
									method : "GET" ,
									dataType : "json" ,
									headers : {
										"Accept" : "application/json",
										"Content-Type" : "application/json"
									},
									success : function(JSONData , status) {
			
										//Debug...
										//alert(status);
										//Debug...
										//alert("JSONData : \n"+JSONData);
										
										var displayValue = "<h6>"
												+"상품명 : "+JSONData.prodName+"<br/>"
												+"상품 상세정보 : "+JSONData.prodDetail+"<br/>"
												+"제조일자 : "+JSONData.manuDate+"<br/>"
												+"가격 : "+JSONData.price+"<br/>"
												+"수량 : "+JSONData.prodNum+"<br/>"
												+"등록일자 : "+JSONData.regDate+"<br/>"
												+"</h6>";
										
										if (menu!="search") {
											
											displayValue+='<h6><a href="/product/getProduct?prodNo='
												+prodNo
												+'&menu=manage&currentPage=1'
												+'">수정</a></h6>';
										} else if(menu=="search" && JSONData.prodNum!=0) {
											displayValue+='<h6><a href="/product/getProduct?prodNo='
												+prodNo
												+'&menu=search&currentPage=1'
												+'">상세조회</a></h6>';
										}
										
										//Debug...									
										//alert(displayValue);
										$("h6").remove();
										$( "#"+prodNo+"" ).html(displayValue);
									}
						});
						////////////////////////////////////////////////////////////////////////////////////////////
						
				});
				
				//============= userId 에 회원정보보기  Event  처리(Click) =============	
				$(function() {
				
					//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
					$( "td:nth-child(2)" ).on("click" , function() {
						var prodNo = $(this).text().trim().substring(0,5);
						self.location ="/product/getProduct?prodNo="+prodNo+"&menu=${param.menu}&currentPage=1";
					});
								
					//==> userId LINK Event End User 에게 보일수 있도록 
					$( "td:nth-child(2)" ).css("color" , "red");
					
					});	
				
					//짝수열 회색
					$(".ct_list_pop:nth-child(4n+2)" ).css("background-color" , "whitesmoke");
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
		       	<h3>
		       		<c:if test="${param.menu=='manage'}">상품관리</c:if>
					<c:if test="${param.menu=='search'}">상품 목록조회</c:if>
				</h3>
		    </div>
		    
			<!-- tab형 네비게이션 start///////////////////////////////////////////////////// -->    
		    <ul class="nav nav-tabs">
			  <li role="presentation" class="active"><a href="#tab1" data-toggle="tab">게시판형</a></li>
			  <li role="presentation"><a href="#tab2" data-toggle="tab">썸네일형</a></li>
			</ul>
			
			<div class="padding-bottom"></div>	    
		    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
		    <div class="row">
		    
			    
	
			    <div class="col-md-12 text-right">
				    <form class="form-inline" name="detailForm">
				    
					  <div class="form-group">
					    <select class="form-control" name="searchCondition" >
							<c:if test="${param.menu=='manage'}">
								<option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
							</c:if>
							<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
							<option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
						</select>
					  </div>
					  
					  <div class="form-group">
					    <label class="sr-only" for="searchKeyword">검색어</label>
					    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
					    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
					  </div>
					  
					  <button type="button" class="btn btn-default">검색</button>
					  
					  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
					  <input type="hidden" id="currentPage" name="currentPage" value=""/>
					  
					</form>
		    	</div>
		    	
			</div>
			
			<div class="col-md-12 text-right">
				<p class="text-primary">
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
			    </p>
			</div>
			<!-- table 위쪽 검색 Start /////////////////////////////////////-->
			
			<!--  nav tab content start//////////////////////////////////// -->
		    <div class="tab-content">
		    	<!--  tab1 start////////////////////////////////////////// -->
			    <div class="tab-pane active" id="tab1">
			    <div class="col-md-6 text-left">
			    	<p class="text-primary">
			    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
			    	</p>
			    </div>
			    <!--  table Start /////////////////////////////////////-->
				    <table class="table table-hover table-striped" >
			      
					<thead>
						<tr>
				            <th class="text-center">No</th>
				            <th align="left" >상품명</th>
				            <th align="left">가격</th>
				            <c:if test="${param.menu=='manage'}">
				            	<th align="left">등록일</th>
				            </c:if>
				            <th align="left">현재상태</th>
				            <th align="left">간략정보</th>
						</tr>
			        </thead>
		
					<tbody>
					
						<c:set var="i" value="0" />
						<c:forEach var="prod" items="${list}">
						<c:set var="i" value="${ i+1 }" />
						<tr>
							<td align="center">${ i }</td>
							<td align="left"  title="Click : 상품정보 확인">
								<p class="hidden">${prod.prodNo}</p>
								${prod.prodName}
							</td>
							<td align="left">${prod.price}</td>
							<c:if test="${param.menu=='manage'}">
								<td align="left">${prod.regDate}</td>
							</c:if>	
							<td align="left">
								<c:if test="${param.menu=='manage'}">
										<a href="/purchase/listSale?prodNo=${prod.prodNo}">배송관리</a>
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
							</td>
							<td align="left">
						  	<i class="glyphicon glyphicon-ok" id= "${prod.prodNo}"></i>
						  	<input type="hidden" value="${prod.prodNo}">
						  	</td>
						</tr>
			          </c:forEach>
			        
			        </tbody>
			      
			    </table>
				<!--  table End /////////////////////////////////////-->
				<!-- PageNavigation Start... -->
				<jsp:include page="../common/pageNavigator_new.jsp"/>
				<!-- PageNavigation End... -->
				  
			    </div>
			    <!-- tab1 div end/////////////////////////////////// -->
			    
			    <div class="tab-pane" id="tab2">
			    
			    	<c:set var="i" value="0" />
					<c:forEach var="prod" items="${list}">
						<c:set var="i" value="${ i+1 }" />
						<div class="col-md-3">
						  <div class="card-md-3">
						    <div class="thumbnail">
							    <c:if test="${prod.fileName != null}">
									<img src="/images/uploadFiles/${prod.fileName}"/>
								</c:if>
								<c:if test="${prod.fileName == null}">
									<img src="/images/uploadFiles/null.jpg"/>
								</c:if>
						      <div class="caption">
						      	<p class="hidden">${prod.prodNo}</p>
						        <h3>${prod.prodName }</h3>
						        <p>${prod.prodDetail}</p>
						        <p><a href="/purchase/addPurchase?prod_no=${prod.prodNo}" class="btn btn-primary" role="button">구매</a></p>
						      </div>
						    </div>
						  </div>
						</div>
					</c:forEach>
			    	
			    </div>
		 	
		 	
		 	</div>
		 	<!-- tab content div end/////////////////////////////// -->
		 	
		</div>
		<!--  화면구성 div End /////////////////////////////////////-->
	 	
	</body>
</html>