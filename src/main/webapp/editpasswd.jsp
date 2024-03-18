<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Password</title>
  <link rel="stylesheet" type="text/css" href="resources/mypage.css">
</head>
<body>


<%@ page import="tukorea.web.club.domain.PersonVO" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%@ page import="java.util.Enumeration" %>

<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%@ page import="java.util.Enumeration" %>

<%@ page import="java.util.Enumeration" %>

<%@ page import="java.util.Enumeration" %>
<!-- 메뉴바 -->
<div id="menu">
    <a href="http://localhost:8080/YuSejeong_free/MyPageServlet?cmd=mypage" >마이페이지</a>
    <a href="todoadd.html">투두리스트</a><br>
    </div>
    <div id="todoContainer">
    <h2>비밀번호 수정</h2>
<form action="http://localhost:8080/YuSejeong_free/MyPageServlet?cmd=newpasswd" method="post" >
    <%-- 현재 비밀번호 표시 --%>
    <p>현재 비밀번호: ${user.passwd}</p>

    <%-- 비밀번호 수정 입력 폼 --%>
    <label for="newPassword">New Password:</label>
    <input type="password" id="newPassword" value="새로운 비밀번호" name="newPassword" required><br>
    <br>

    <br><input type="submit" value="비밀번호 수정">
</form>
</div>

</body>
</html>