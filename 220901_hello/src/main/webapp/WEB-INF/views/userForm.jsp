<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- 2. ModelAttribute읽어낼 수 있는 form태그라이브러리 소환 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 정보 입력</title>
</head>
<body>
	<%-- <form action="user" method="POST">
		<input type="text" name="name" />
		<input type="number" name="age" />
		<input type="submit">
	</form> --%>
	<!-- 3. 태그라이브러리 이용해서 modelAtrribute연동하기 -->
	<f:form modelAttribute="userinfo"> <!-- 4. 기본값이 method는 post방식, action은 현재요청url임!! -->
		<f:errors path="name" /> <!-- 9. Errors객체 or BindingResult객체로 에러 정보를 추가한 경우 path로 설정한 필드 관련 오류메시지들 전부 출력됨 -->
		<f:input type="text" path="name" /> <!-- 5. name 어트리뷰트를 path로 바꾸기! -> 같은 필드이름 찾아 바인딩 -->
		<f:errors path="age" />
		<f:input type="number" path="age" />
		<input type="submit"> 
	</f:form>
</body>
</html>