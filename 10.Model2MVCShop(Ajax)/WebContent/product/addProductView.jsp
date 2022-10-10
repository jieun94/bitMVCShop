<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<html lang="ko">
	
	<head>
		<meta charset="EUC-KR">
		
		<!-- ���� : http://getbootstrap.com/css/   ���� -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
		<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
		
		
	   
	   
	   	<!-- jQuery UI toolTip ��� CSS-->
	  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	 	<!-- jQuery UI toolTip ��� JS-->
	  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  	
	  	<!-- include summernote css/js -->
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
		
		<!-- include summernote-ko-KR -->
		<!-- <script src="/javascript/lang/summernote-ko-KR.js"></script> -->
		
		<!--  ///////////////////////// CSS ////////////////////////// -->
		<style>
		  body {
	            padding-top : 50px;
	        }
	    </style>
	    
	    <!--  ///////////////////////// JavaScript ////////////////////////// -->
		<script type="text/javascript">
			function fncAddProduct(){
				
				var name=$("input[name='prodName']").val();
				var detail = $('textarea[name="prodDetail"]').val($('#summernote').summernote('code'));
				var manuDate=$("input[name='manuDate']").val();
				var price=$("input[name='price']").val();
				var prodNum=$("input[name='prodNum']").val();
				
				if(name == null || name.length<1){
					alert("��ǰ���� �ݵ�� �Է��Ͽ��� �մϴ�.");
					return;
				}
				if(detail == null || detail.length<1){
					alert("��ǰ�������� �ݵ�� �Է��Ͽ��� �մϴ�.");
					return;
				}
				if(manuDate == null || manuDate.length<1){
					alert("�������ڴ� �ݵ�� �Է��ϼž� �մϴ�.");
					return;
				}
				if(price == null || price.length<1){
					alert("������ �ݵ�� �Է��ϼž� �մϴ�.");
					return;
				}
				if(prodNum == null || prodNum.length<1){
					alert("������ �ݵ�� �Է��ϼž� �մϴ�.");
					return;
				}
				
    			$('textarea[name="prodDetail"]').val($('#summernote').summernote('code'));
				$("form").attr("method" , "POST").attr("action" , "/product/addProduct").attr("enctype","multipart/form-data").submit();
			}
			
			//============= "����"  Event ���� =============
			 $(function() {
				//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$( "button.btn.btn-primary" ).on("click" , function() {
					fncAddProduct();
				});
			});	
			
			//============= "���"  Event ó�� ��  ���� =============
			$(function() {
				//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$("a[href='#' ]").on("click" , function() {
					self.location = "javascript:history.go(-1)"
				});
			});	
			
			//============= radio ó�� =====================
            $(function() {
                $("#freeTemplate").show();
                $("#fileUpload").hide();
                  
                $("input[value='freeTemplate']").on("click", function(){
                    $("#fileUpload").hide();
                    $("#freeTemplate").show();
                });
            });
                
            $(function() {
                $("input[value='fileUpload']").on("click", function(){
                    $("#fileUpload").show();
                    $("#freeTemplate").hide();
                });
            });
            
            //summernote
            $(function() {
          	  	$('#summernote').summernote({
          		  placeholder: '������ �Է��ϼ���',
      	   	        tabsize: 1,
      	   	        height: 300,
      	   	     	lang: 'ko-KR', // default: 'en-US'
      	   	     	callbacks: {	//���� �κ��� �̹����� ÷���ϴ� �κ�
    					onImageUpload : function(files, editor, welEditable) {
							console.log(files);
	   			        	console.log(editor);
	   			        	var opt = {};
    						for (var i = files.length - 1; i >= 0; i--) {
    			            	sendFile(files[i], this);
    			            }
    					}
    				}
          	  	});
          		$("div.note-editable").on('drop',function(e){
                for(i=0; i< e.originalEvent.dataTransfer.files.length; i++){
                	sendFile(e.originalEvent.dataTransfer.files[i],$("#summernote")[0]);
                }
               e.preventDefault();
          })
          	});
            
            /**
        	* �̹��� ���� ���ε�
        	*/
        	function sendFile(file, editor) {
        		data = new FormData();
        		data.append("file", file);
        		
        		$.ajax({
        			data : data,
        			type : "POST",
        			url : "/product/json/addProduct",
        			cache: false,
        			contentType : false,
        			processData : false,
        			success : function(data) {
                    	//�׻� ���ε�� ������ url�� �־�� �Ѵ�.
                    	//alert(data.url);
                    	var data = data.url;
                    	
                    	setTimeout(function() {
							//alert('http://webisfree.com');
                    		$(editor).summernote('insertImage', data.url);
						}, 1000);
        				
        			}
        		});
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
				<h3 class=" text-info">��ǰ���</h3>
			</div>
			
			<!-- form Start /////////////////////////////////////-->
			<form class="form-horizontal">
			
				<div class="form-group">
				    <label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">�� ǰ ��</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="prodName" name="prodName">
				    </div>
				</div>
				
				<div class="form-group">
				    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">��ǰ������</label>
				    <div class="col-sm-4">
				      <!-- <input type="text" class="form-control" id="prodDetail" name="prodDetail"> -->
							<textarea id="prodDetail" name="prodDetail" class="summernote" style="display: none;"></textarea>
							<div id="summernote"></div>
				    </div>
				</div>
				
				<div class="form-group">
				    <label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">��������</label>
				    <div class="col-sm-4">
				      <input type="date" class="form-control" id="manuDate" name="manuDate">
				      <!-- <img src="../images/ct_icon_date.gif" width="15" height="15" 
											onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)"/> -->
				    </div>
				</div>
				
				<div class="form-group">
				    <label for="price" class="col-sm-offset-1 col-sm-3 control-label">����</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="price" name="price">
				    </div>
				    <div class="col-sm-3">
			      		<p>��</p>
			    	</div>
				</div>
				
				<div class="form-group">
				    <label for="prodNum" class="col-sm-offset-1 col-sm-3 control-label">����</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="prodNum" name="prodNum">
				    </div>
				    <div class="col-sm-3">
			      		<p>��</p>
			    	</div>
				</div>
				
				<div class="form-group">
				    <label for="cover" class="col-sm-offset-1 col-sm-3 control-label">ǥ������</label>
				    <div class="col-sm-4">
                        <label>
                            <input type="radio" name="cover" value="freeTemplate" checked> ���� ���ø�
                        </label>
                        <label>
                            <input type="radio" name="cover" value="fileUpload"> ���� �ø���
                        </label>
				    </div>
				</div>
                
                <div class="form-group" id="freeTemplate">
				    <label for="freeTemplate" class="col-sm-offset-1 col-sm-3 control-label">���� ���ø�</label>
				    <div class="col-sm-4">
				        ����
				    </div>
				</div>
                
                <div class="form-group" id="fileUpload">
				    <label for="fileUpload" class="col-sm-offset-1 col-sm-3 control-label">���� �ø���</label>
				    <div class="col-sm-4">
				        <input type="file" class="form-control" id="file" name="file">
				    </div>
				</div>
				
				<div class="form-group">
			    	<div class="col-sm-offset-4  col-sm-4 text-right">
			      		<button type="button" class="btn btn-primary"  >��&nbsp;��</button>
				  		<a class="btn btn-primary btn" href="#" role="button">��&nbsp;��</a>
			    	</div>
			  	</div>
			</form>
		</div>
	</body>
</html>