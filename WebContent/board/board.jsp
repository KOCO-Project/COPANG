<%@page import="board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COPANG</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="customer">
		<div class="table">
			<h1>익명게시판</h1>
			<hr>
			<table border="1">
				<tr>
					<th>번호&nbsp;</th>
					<th>제목&nbsp;</th>
					<th>날짜&nbsp;</th>
					<th>조회수</th>
				</tr>
				<%
					ArrayList<BoardDTO> boardList = (ArrayList<BoardDTO>) request.getAttribute("boardList");

					for (int i = 0; i < boardList.size(); i++) {
						BoardDTO boardDTO = boardList.get(i);
				%>
				<tr>
					<td><%=boardDTO.getBoardNo()%>&nbsp;</td>
					<td><a href='boardView.bo?no=<%=boardDTO.getBoardNo()%>'
						style="color: black; text-decoration: none;"
						onmouseover="this.style.color='#0074E9'"
						onmouseout="this.style.color='black'"><%=boardDTO.getBoardTitle()%></a>&nbsp;</td>
					<!--a태그로 인해 링크로 걸려버리는 디자인 제거하여 마우스 오버 및 아웃시 색상변화와 기본 텍스트 컬러 블랙으로만 설정 -->
					<td><%=boardDTO.getBoardDate()%>&nbsp;</td>
					<td><%=boardDTO.getBoardReadcount()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<br>
			<div>
				<form action="boardSearch.bo" method="get">
					<ul>
						<li><input type="text" placeholder="게시물 제목"
							name="searchTitle" required="required"> <input
							type="submit" value="검색"></li>
					</ul>
				</form>
			</div>
			<br>
			<div class="link">
				<a href="board/boardWrite.jsp" class="btn">게시글쓰기</a>
			</div>
		</div>
	</div>
</body>
</html>