<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	BoardVO bv = (BoardVO) request.getAttribute("boardVO");

	String content = bv.getBoardContent().replaceAll(System.lineSeparator(), "<br>");	// 시스템 별 줄바꿈
	
	String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>글 정보</title>
</head>
<style>
	a {
		text-decoration: none;
	}
</style>
<body>
	<table border="1" style="border-collapse: collapse;">
		<tr>
			<td>글번호:</td>
			<td><%= bv.getBoardNo() %></td>
		</tr>
		<tr>
			<td>작성자:</td>
			<td><%= bv.getBoardWriter() %></td>
		</tr>
		<tr>
			<td>작성일자:</td>
			<td><%= bv.getBoardDate() %></td>
		</tr>
		<tr>
			<td>제목:</td>
			<td><%= bv.getBoardTitle() %></td>
		</tr>
		<tr>
			<td>내용:</td>
			<td><%= content %></td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="list.do">[목록]</a>
				<a href="update.do?boardNo=<%= bv.getBoardNo() %>&boardWriter=<%= bv.getBoardWriter() %>">[글정보 수정]</a>
				<a href="delete.do?boardNo=<%= bv.getBoardNo() %>">[글정보 삭제]</a>
			</td>
		</tr>
	</table>
	<%
		if(msg.equals("성공")){
			%>
			<script type="text/javascript">
				alert("글이 수정되었습니다.");
			</script>
			<%
		}
	%>
</body>
</html>