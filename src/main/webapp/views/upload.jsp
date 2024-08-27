<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function img() {
		var preview=document.getElementById("preview");
		preview.innerHTML=""; // 이전 미리보기 제거
		
		var files=document.getElementById("fileinput").files;
		
		for(i=0;i<files.length;i++) {
			var file=files[i];
			
			if(file.type.startsWith("image/")){
				var reader =new FileReader();
				
				reader.onload=function(e) {
					var img=document.createElement("img");
					img.src=e.target.result;
					img.style.maxWidth="200px";
					img.style.maxHeight="200px";
					img.style.margin="10px";
					preview.appendChild(img);
				}
				reader.readAsDataURL(file);
			}
		}
	}
</script>
</head>
<body>
	<form method="post" action="uploadOk" enctype="multipart/form-data">
		<input type="file" name="file" multiple onchange="img()" id="fileinput">
		<input type="submit" value="업로드">
	</form>
	
	<div id="preview"></div>
</body>
</html>