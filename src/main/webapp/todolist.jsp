<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.HttpSession" %>

<%
    // 로그인한 사용자의 ID를 세션에서 가져옴
    String loggedInUser = (String) session.getAttribute("loggedInUser");
%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todolist</title>
    <link rel="stylesheet" type="text/css" href="resources/todolist.css">
</head>
<body>

<!-- 메뉴바 -->
<div id="menu">
    <a href="http://localhost:8080/YuSejeong_free/MyPageServlet?cmd=mypage">마이페이지</a>
    <a href="todoadd.html">투두리스트</a><br>
</div>

<div id="todoContainer">
    <h1>To-Do List</h1>
<c:forEach var="todo" items="${todoList}" varStatus="loop">
    <div class="todoItem" id="todoItem${loop.index}">
        <div class="contentContainer">
            <span>${todo.getContent()}</span>
        </div>
        <div class="buttonContainer">
            <button class="actionButton deleteButton"
                    onclick="location.href='http://localhost:8080/YuSejeong_free/TodoListServlet?cmd=delete&id=${todo.getId()}&content=${todo.getContent()}'">
                삭제
            </button>
            <button class="actionButton completeButton"
                    onclick="completeTodo(${loop.index})">
                완료
            </button>
        </div>
    </div>
</c:forEach>

    <button class="actionButton goBackButton" onclick="location.href='http://localhost:8080/YuSejeong_free/todoadd.html'">이전 페이지로 이동</button>
</div>


<script>
// 페이지 로드 시 저장된 쿠키 읽어오기
window.onload = function () {
    var loggedInUser = '<%= session.getAttribute("loggedInUser") %>';
    var completedItems = getCookie('completedItems_' + loggedInUser);

    if (completedItems) {
        var items = completedItems.split(',');
        items.forEach(function (index) {
            toggleCompleted(parseInt(index));
        });
    } else {
        // 쿠키가 없을 경우 초기 쿠키 생성
        setCookie('completedItems_' + loggedInUser, '', 365);
    }
};

function completeTodo(index) {
    toggleCompleted(index);

    var loggedInUser = '<%= session.getAttribute("loggedInUser") %>';
    var completedItems = getCookie('completedItems_' + loggedInUser) || '';
    if (completedItems) {
        completedItems += ',';
    }
    completedItems += index;
    
    // 쿠키 유효 기간을 365일로 설정
    setCookie('completedItems_' + loggedInUser, completedItems, 365);
}

function toggleCompleted(index) {
    var contentContainer = document.getElementById('todoItem' + index).querySelector('.contentContainer span');
    contentContainer.classList.toggle('completed');
}

// 쿠키 설정 함수
function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + '=' + value + expires + ';path=/';
}

// 쿠키 가져오기 함수
function getCookie(name) {
    var match = document.cookie.match(new RegExp(name + '=([^;]+)'));
    return match ? match[1] : null;
}
</script>
</body>
</html>