<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
 <link rel="stylesheet" type="text/css" href="resources/welcome.css">
</head>
<body>

<!-- ToDoList 상단 -->
<div id="header">
    ToDoList
</div>

<!-- 메뉴바 -->
<div id="menu">
    <a href="http://localhost:8080/YuSejeong_free/MyPageServlet?cmd=mypage" >마이페이지</a>
    <a href="todoadd.html">투두리스트</a>
</div>

<!-- 컨텐츠 영역 -->
<div id="todoContainer">
    <%-- JSP에서 세션을 이용하여 데이터 가져오기 --%>
   <br><br> <%
        String id = (String) session.getAttribute("loggedInUser");
    %>

    <%-- 가져온 데이터를 출력 --%>
    <h1>Welcome, <%= id %>!</h1>
   <p> 나만의 TODO-List를 만들어보세요!
   
   <br><br><br><br><br>
	TODO-List란 ? 해야 할 작업을 모두 정리한 목록입니다.<br>
	TODO-List를 사용하여 편리한 일정 관리를 해보세요!
   </p>
   
<br>

<button onclick="location.href='todoadd.html'">시작하기</button><br><br><br>
</div>

</body>
</html>