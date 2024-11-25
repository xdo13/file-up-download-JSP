<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script> //유효성검사
	function validateForm(form) {
		if(form.title.value == ""){
			alert("제목을 입력하세요.");
			form.title.focus(); //처음 커서 지정
			return false;
		}
		if(form.attachedFile.value == ""){
			alert("첨부 파일은 필수 입력입니다.")
			return false;
		}
	}

</script>
<title>FileUpload</title>
</head>
<body>
	<h3>파일 업로드</h3>
	<span style="color : red;">${errorMessage }</span>
	<form name="fileForm" method="post" enctype="multipart/form-data" action="UploadProcess.do" onSubmit="return validateForm(this);">
	제목 : <input type="text" name="title" /> <br />
	카테고리(선택사항) :
		<input type="checkbox" name="cate" value="사진" checked /> 사진
		<input type="checkbox" name="cate" value="과제" /> 과제
		<input type="checkbox" name="cate" value="워드" /> 워드
		<input type="checkbox" name="cate" value="음원" /> 음원 <br />
	첨부파일 : <input type="file" name="ofile" /> <br /> <!-- 첨부파일 넣는 코드 -->
		<input type="submit" value="전송하기" />
		</form>
</body>
</html>