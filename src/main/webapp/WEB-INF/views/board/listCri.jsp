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

	$(function() {
		
		var link = document.location.href;
		console.log(link);
		
		let result = '${result }';

		if (result == 'success') {
			alert("작성한 글이 등록되었습니다!");
		}
		
		let thispage = ${param.page };
		let endpage = ${param.page > pagingParam.endPage };
		
		console.log(thispage);
		console.log(endpage);
		
		
	})
</script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<%@include file="../templateHeader.jsp"%>
	<%@include file="../templateAside.jsp"%>
	<div class="content-wrapper">
		<c:choose>
			<c:when test="${boardList !=null }">
				<table class="table table-condensed">
					<thead>
						<tr>
							<th>글번호</th>
							<th>글제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<c:forEach var="board" items="${boardList }" varStatus="status">
						<c:choose>
							<c:when test='${board.isdelete == "Y"}'>
								<tr>
									<td><strike>${board.no }</strike></td>
									<td><strike>${board.title }</strike></td>
									<td><strike>${board.writer }</strike></td>
									<td><strike><span class="sendTime"
											id="${status.count }"><fmt:formatDate
													value="${board.regdate }" type="both"
													pattern="yyyy-MM-dd HH:mm:ss" /></span></strike></td>
									<td><strike>${board.viewcnt }</strike></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${board.no }</td>
									<td><a href="/board/read?no=${board.no }&pageNo=${param.page }">
											${board.title } </a>
									<td>${board.writer }</td>
									<td><span class="sendTime" id="${status.count }"><fmt:formatDate
												value="${board.regdate }" type="both"
												pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
									<td>${board.viewcnt }</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
												
				<div class="text-center">
					<ul class="pagination" style="position: absolute; margin-left: 500px;">
					<c:if test="${pagingParam.prev }">
							<li class="page-item">
								<a class="page-link" href="listCri?page=${param.page - 1}">Prev</a>
							</li>
					</c:if>
					
						<c:forEach begin="${pagingParam.startPage }" end="${pagingParam.endPage }" var="pageNo">
							<li class="page-item">
								<a class="page-link" href="listCri?page=${pageNo }">${pageNo }</a>
							</li>
						</c:forEach>
						
<%-- 					<c:if test="${param.page > pagingParam.endPage }"> --%>
					<c:if test="${pagingParam.next }">
							<li class="page-item">
								<a class="page-link" href="listCri?page=${param.page + 1}">Next</a>
							</li>
					</c:if>
<%-- 					</c:if> --%>
					
					</ul>
				</div>
				
				<div>
					<form action="list.bo" method="GET">
						<select name="searchQuery">
							<option value="title">제목</option>
							<option value="writer">작성자</option>
							<option value="content">본문</option>
						</select> <input type="text" name="searchWord" id="searchWord" />
						<button type="submit" class="btn btn-default">검색</button>
					</form>
				</div>
				
				<div>
					<button type="button" class="btn btn-info" style="float: right;"
						onclick="location.href='/board/register'">글쓰기</button>
				</div>

			</c:when>
			<c:otherwise>
				게시물이 존재하지 않거나, 데이터를 얻어오지 못했습니다.
			</c:otherwise>
		</c:choose>
	</div>
	<%@include file="../templatefooter.jsp"%>
</body>
</html>