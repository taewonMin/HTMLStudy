<%@page import="vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");

	String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
</head>
<body>
	<table border="1">
		 <tr>
		 	<td colspan="5"><a href="insert.do">[글 등록]</a></td>
		 </tr>
		 <tr>
		 	<td>글번호</td>
		 	<td>작성자</td>
		 	<td>날짜</td>
		 	<td>제목</td>
		 	<td>내용</td>
		 </tr>
	 <% 
		 if(list.size() > 0){
			 for(int i=0; i<list.size(); i++){
	  %>
			<tr>
				<td><%= i+1 %></td>
				<td><%= list.get(i).getBoardWriter() %></td>
				<td><%= list.get(i).getBoardDate().substring(0,10) %></td>
				<td><a href="select.do?boardNo=<%= list.get(i).getBoardNo() %>"><%= list.get(i).getBoardTitle() %></a></td>
				<td><%= list.get(i).getBoardContent() %></td>
			</tr>
	 <%
			 }
		 }else{	// 글정보가 존재하지 않으면
	  %>
			<tr>
				<td colspan="5">등록된 글이 없습니다.</td>
			</tr>
	 <%				 
		 }
	  %>
	</table>
	
	<% 
		if(msg.equals("성공")){
			%>
				<script type="text/javascript">
					alert("정상적으로 처리되었습니다.");
				</script>
			<%
		}
	%>
</body>
</html>