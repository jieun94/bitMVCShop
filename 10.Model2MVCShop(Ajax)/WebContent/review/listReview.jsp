<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
		<script type="text/javascript">
			function fncGetUserList(currentPage) {
				
				$("#currentPage").val(currentPage)
				/* 
				$(function(){
				    //$("#btn_search").click(function(){
				        //alert("검색 버튼 클릭!");
				        var params = $("form").serialize();
				        
				         $.ajax({
				            url:'/review/json/listReview',
				            type:'POST',
				            dataType: "json",
				            data:params,
				            success:function(data){
				                // success
				            },
				            error:function(){
				                alert("ajax통신 실패!!!");
				            }
				        }); 
				    }); */
				$("form").attr("method" , "GET").attr("action" , "/product/getProduct").submit();
			}
			
			function fncReviewList(reviewNo) {
				//alert("ok");
				//var reviewNo = $(this).text().trim().substring(0,5);
				
				$.ajax(
						{
							url : "/review/json/getReview/"+reviewNo ,
							method : "GET" ,
							dataType : "json" ,
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
						success : function(JSONData , status) {
							
							var displayValue = "<h4>"
								+JSONData.reviewDetail+"<br/>"
								+"</h4>";
							
							$("h4").remove();
							$( "#"+reviewNo+"" ).html(displayValue);
						}
				});
				/////////////////////////////////////////////Ajax end//////////////////
			}
		</script>	
		<!--  화면구성 div Start /////////////////////////////////////-->
		<div class="container">
		
			<div class="page-header text-info">
		       	<h3>상품평</h3>
		    </div>
				    
		    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    	<input type="hidden" id="prodNo" name="prodNo" value="${param.prodNo}"/>
				  	<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  	<input type="hidden" id="currentPage" name="currentPage" value=""/>
				  	<input type="hidden" id="menu" name="menu" value="search"/>
				</form>
	    	</div>
			<!-- table 위쪽 검색 end /////////////////////////////////////-->
		
			<!--  table Start /////////////////////////////////////-->
			<table class="table table-hover table-striped" >
	      
		        <thead>
		          <tr>
		            <th class="text-center col-md-2">No</th>
		            <th align="left" class="text-center col-md-7">제목</th>
		            <th align="left" class="text-center col-md-3">작성자</th>
		          </tr>
		        </thead>
	
				<tbody>
				
				  <c:set var="i" value="0" />
				  <c:forEach var="review" items="${list}">
					<c:set var="i" value="${ i+1 }" />
					<tr>
						<td align="center">${ i }</td>
						<td align="left"  title="Click : 상품평 확인">
							<a href="javascript:fncReviewList('${review.reviewNo}');">${review.reviewName}</a>
							<p align="left" id="${review.reviewNo}"></p>
						</td>
						<td align="center">${review.reviewBuyer.userName}</td>
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