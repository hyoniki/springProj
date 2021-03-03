<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function addFilePath(msg) {
		alert(msg);
		
		$(".uploadForm")[0].reset();
	}
</script>
<style>
  	iframe {
  		width: 0px;
  		height: 0px;
  		border: 0px;
  	}
</style>
</head>
<body>
	<h1>파일 업로드 테스트</h1>
	
	<form action="uploadForm" method="post" enctype="multipart/form-data" target="myFrame" id="uploadForm">
		<input type="file" name="uploadfile" />
		<input type="submit" value="전송" />
	</form>
	
	<iframe name="myFrame">
		
	</iframe>
	
</body>
</html>