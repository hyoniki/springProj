<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>
	
  <!-- jQuery -->
  <script src="../resources/plugins/jquery/jquery.min.js"></script>

  <script type="text/javascript">
	
	$(function() {
		let result = '${saveFileName}';
		console.log(result);
		parent.addFilePath(result);
		
	})
  
  </script>

</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<%@include file="templateHeader.jsp" %>	
	<%@include file="templateAside.jsp" %>	
	<div class="content-wrapper">
		<div class="container">
			<h3>uploadResult.jsp</h3>
			
			파일 이름: ${saveFileName }<br />
			파일: <img src="resources/uploads/${saveFileName }" />
			
		</div>
	</div>
	<%@include file="templatefooter.jsp" %>
</body>
</html>