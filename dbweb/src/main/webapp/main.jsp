<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.TodoDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<%	
		List<TodoDto> list = (List<TodoDto>)request.getAttribute("list");
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todo</title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
	<div id="head">
		<h1>나의 해야할 일들</h1>
		<button type="button" onclick="location.href='todoform'">새로운 TODO 등록</button>
	</div>
	<ul class="nav">
		<li>
			TODO
		</li>
		<li>
			DOING
		</li>
		<li>
			DONE
		</li>
	</ul>
	<div class="list">
		<ul class="card">
			<c:forEach var="item" items="${list}">
				<c:if test="${item.type eq'TODO'}">
					<ul>
					<input name="id" type="hidden" value="${item.id}"/>
					<input name="type" type="hidden" value="${item.type}"/>
					<li><h2>${item.title}</h2></li>
					<li style="font-size: 15px; color: navy;">등록날짜: ${item.regdate}, ${item.name }, 
					우선순위: ${item.sequence} </li>
					<button class="todo" style="float: right; margin-top: -20px; margin-right: 20px">→</button>
					</ul>
				</c:if>
			</c:forEach>
		</ul>
		<ul class="card">
			<c:forEach var="item" items="${list}">
				<c:if test="${item.type eq'DOING'}">
					<ul>
					<input name="id" type="hidden" value="${item.id}"/>
					<input name="type" type="hidden" value="${item.type}"/>
					<li><h2>${item.title}</h2></li>
					<li style="font-size: 15px; color: navy;">등록날짜: ${item.regdate}, ${item.name }, 
					우선순위: ${item.sequence}</li>
					<button class="doing" style="float: right; margin-top: -20px; margin-right: 20px">→</button>
					</ul>
				</c:if>
			</c:forEach>
		</ul>
		<ul class="card">
			<c:forEach var="item" items="${list}">
				<c:if test="${item.type eq'DONE'}">
					<ul>
					<input name="id" type="hidden" value="${item.id}"/>
					<input name="type" type="hidden" value="${item.type}"/>
					<li><h2>${item.title}</h2></li>
					<li style="font-size: 15px; color: navy;">등록날짜: ${item.regdate}, ${item.name }, 
					우선순위: ${item.sequence}</li>
					</ul>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</body>
<script type="text/javascript" src="main.js"></script>
</html>