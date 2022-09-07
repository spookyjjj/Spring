<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- 2. 라이브러리소환 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 정보 입력</title>
</head>
<body>
	<f:form modelAttribute="user" method="POST"> <!-- 3. modelAtrribute주기 -->
		<f:errors path="name" /> <!-- 9.에러메시지 출력 -->
		<f:input type="text" path="name" /> <!-- 4. input tag도 불러온 라이브러리껄로 바꾸기! -->
		<f:errors path="age" />
		<f:input type="number" path="age" /> <!-- 5. name 어트리뷰트를 path로 바꾸기!->바인딩과정 -->
		<input type="submit"> <!-- 6. post방식으로 userController로 다시 ㄱㄱ -->
	</f:form>
</body>
</html>