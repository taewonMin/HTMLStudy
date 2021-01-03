<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String boardNo = request.getParameter("boardNo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
</head>
<body>
	<form action="update.do" method="post">
		<table>
			<tr>
				<td>작성자: </td>
				<td><input type="text" name="boardWriter" readonly="readonly" value="<%= request.getParameter("boardWriter") %>"></td>
			</tr>
			<tr>
				<td>제목: </td>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<td>내용: </td>
				<td><textarea name="boardContent"></textarea></td>
			</tr>
		</table>
		<input type="hidden" name="boardNo" value="<%= boardNo %>">
		<input type="submit" value="글 수정">
	</form>
</body>
</html>