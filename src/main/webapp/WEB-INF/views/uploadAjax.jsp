<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script src="../resources/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".fDrop").on("dragenter dragover", function(evt) {
			evt.preventDefault(); // 이벤트 전파를 막음 (파일이 웹브라우저에서 열리는 것을 막음)
		});
		
		$(".fDrop").on("drop", function(evt) {
			evt.preventDefault();
			
			let files = evt.originalEvent.dataTransfer.files; // 드래그 이벤트의 파일 데이터를 전송하는 처리는 되도록
			for (let f in files) {
				console.log(f);
			}
			let file = files[0];
			console.log(file);
			
			let formData = new FormData();
			formData.append("file", file); // "file" : key, file : value
			
			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text', // 응답받을 타입
				type : 'post',
				processData : false, // 전송하는 데이터를 쿼리스트링 형태로 변환하는지를 결정
				contentType : false, // 기본 값 : apllication/x-www-form-urlencoded (form 태그의 인코딩 기본값)
				success : function(result) {
					
					let output = '';
					if(checkImgType(result)) {
						// 이미지 파일이면..
						output = "<div>" + 
								"<img src='displayFile?fileName=" + result + "'/>" + getImgLink(result);
						output += "<span id='" + result + "' onclick='deleteFile(this)'>X</span>";
						output += "</div>";
					} else {
						output = "<div><a href='displyFile?fileName=" + result + "'>" + getOriginalName(result) + "</a></div>";
					}
					
					$(".fDropList").append(output);
				},
				fail : function(result) {
					console.log(result);
				}
			})
			
		});
		
	});
	
	function deleteFile(obj) {
		let fileName = $(obj).attr("id");
		
		$.ajax({
			url : '/deleteFile',
			data : {"fileName" : fileName},
			dataType : 'text', // 응답받을 타입
			type : 'post',
			success : function(result) {
				alert(result);
			}, fail : function(result) {
				alert(result);
			}
		})
		
	}
	
	function getImgLink(fileName) {
		if (!checkImgType(fileName)){
			return;
		}
		
		let unscorePos = fileName.lastIndexOf("_") + 1;
		return fileName.substr(unscorePos);
	}
	
	function getOriginalName(fileName) {
		if (checkImgType(fileName)) {
			return;
		}
		let underScorePos = fileName.indexOf('_') + 1;
		return fileName.substr(underScorePos);
	}
	
	// 파일이름을 넘겨 받아 확장자가 패턴에 있는지 없는지 참/거짓 반환
	function checkImgType(fileName) {
		let imgPattern = /jpg$|gif$|png$|jpeg$/i;
		return fileName.match(imgPattern); // 넘겨져온 파일이 이미지파일인지 검사
	}
	
</script>
<style type="text/css">
	.fDrop {
		width: 100%;
		height: 200px;
		border: 1px dotted gray;
	}
	
	span {
		margin-left: 3px;
		font-weight: bord;
		color: gray;
	}
</style>
</head>
<body>
	<h3>Ajax Upload Test</h3>
	
	<div class="fDrop"></div>
	<div class="fDropList"></div>
</body>
</html>