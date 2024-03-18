<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이 페이지</title>
      <link rel="stylesheet" type="text/css" href="resources/mypage.css">
</head>
<body>
<!-- 메뉴바 -->
<div id="menu">
    <a href="http://localhost:8080/YuSejeong_free/MyPageServlet?cmd=mypage" >마이페이지</a>
    <a href="todoadd.html">투두리스트</a><br>
  </div>
<div id="todoContainer">
<c:if test="${not empty user}">
    <h2>My Page "${user.username}" </h2>
    <p>ID: ${user.id}</p>
    <p>비밀번호: ${user.passwd}</p>
    <p>전화번호: ${user.mobile}</p>
    <p>이메일: ${user.email}</p>
    
     <button onclick="location.href='http://localhost:8080/YuSejeong_free/MyPageServlet?cmd=pwdupdate'">비밀번호 수정하기</button>
</c:if>
</div>

</body>
</html>