<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard</title>

<!-- jQuery -->
<script src="../resources/plugins/jquery/jquery.min.js"></script>
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../resources/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="../resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="../resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="../resources/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../resources/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="../resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="../resources/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet"
	href="../resources/plugins/summernote/summernote-bs4.min.css">
<script type="text/javascript">
	function getParameter(param) {
		var returnVal; //리턴할 값을 저장하는 변수
		var url = location.href; //url주소
		console.log(url);
		var params = url.slice(url.indexOf("?") + 1, url.length).split("&");
		console.log(params);

		for ( var i in params) {
			var paraName = params[i].split("=")[0]; // 매개변수명 얻음
			if (param.toLowerCase() == paraName.toLowerCase()) {
				returnVal = params[i].split("=")[1];
				return decodeURIComponent(returnVal);
			}
		}

		return -1; // 모든 배열 요소를 다 검색해도 매개변수가 없을때
	}

	$(function() {
		
		let result = getParameter('result');
		console.log(result);
		if (result == 'success') {
			alert("게시글이 수정되었습니다!");
		} else if (result == 'fail') {
			alert("수정을 실패했습니다!");
		}
		
		if (${board.isdelete == 'Y'}) {
			alert("삭제된 게시물입니다!");
			history.back();
		}
		
		let pageNo = getParameter('pageNo');
		console.log(pageNo);
		if (pageNo == "") {
			alert("잘 못된 접근입니다!");
			location.href = "http://localhost:8081/board/listCri?page=1";
		}
		
	})
	
</script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<%@include file="../templateHeader.jsp"%>
	<%@include file="../templateAside.jsp"%>
	<div class="content-wrapper">
		<div class="container">
			<h1>게시판 글쓰기 페이지</h1>
			<hr />

			<c:choose>
				<c:when test='${board.isdelete == "N" }'>
					<div class="form-group">
						<label class="control-label col-sm-2" for="writer">글 번호 :</label>
						<div class="col-sm-10">${board.no }</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="title">작성자 :</label>
						<div class="col-sm-10">${board.writer }</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">작성일 :</label>
						<div class="col-sm-10">

							<fmt:formatDate value="${board.regdate }" type="both"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">조회수 :</label>
						<div class="col-sm-10">${board.viewcnt }</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">제 목 :</label>
						<div class="col-sm-10">${board.title }</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">내 용 :</label>
						<div class="col-sm-10">${board.content }</div>
					</div>
				</c:when>

				<c:otherwise>
					<div class="form-group">
						<label class="control-label col-sm-2" for="writer">글 번호 :</label>
						<div class="col-sm-10"></div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="title">작성자 :</label>
						<div class="col-sm-10"></div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">작성일 :</label>
						<div class="col-sm-10"></div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">조회수 :</label>
						<div class="col-sm-10"></div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">제 목 :</label>
						<div class="col-sm-10"></div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">내 용 :</label>
						<div class="col-sm-10"></div>
					</div>
				</c:otherwise>
			</c:choose>

			<div class="box-footer">
				<button type="button" class="btn btn-success" id="rewriteBoard"
					onclick="location.href='/board/modi?no=${board.no }'">수정하기</button>
				<button type="button" class="btn btn-info" id="deleteBoard"
					onclick="location.href='/board/remove?no=${board.no }'">삭제하기</button>
				<button type="button" class="btn btn-primary"
					onclick='location.href="/board/listCri?page=${param.pageNo }"'>리스트페이지로</button>
			</div>
		</div>
	</div>
	<%@include file="../templatefooter.jsp"%>
</body>