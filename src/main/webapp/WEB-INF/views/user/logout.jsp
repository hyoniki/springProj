<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3; url='../'">
<title>Insert title here</title>
<script type="text/javascript">
	
	function displayWarn() {
		if (document.getElementById('chkCookie').checked) {
			alert("자동 로그인 기능은 공공장소에서는 사용하지 마세요!");
			return true;
		}
	}
	
</script>
</head>
<body>

	<h4> 로그아웃 되었습니다...</h4>
	
	<h6>잠시 후 첫 페이지 이동합니다...</h6>
	
</body>
</html>