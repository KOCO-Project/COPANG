<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/top.js"></script>
<head>
<script type="text/javascript">
function logout(){
	confirm("로그아웃하시겠습니까?");
	
}
</script>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="top">
		<div class="logo">
			<a href="index.jsp"><img src="images/Copang_logo.png"></a>
		</div>
		<div class="top-bar">
			<a href="mypage.ep">마이페이지</a>
			<a href="logout.ep" onclick="return confirm('로그아웃 하시겠습니까?');">로그아웃</a>

		</div>
		<div class="menu">
			<ul>
				<li><a href="empList.ep">사원조회</a></li>
				<li><a href="#">재고관리</a></li>
				<li><a href="#">공지사항</a></li>
				<li><a href="boardList.bo">익명게시판</a></li>
				<li><a href="customerList.cu">거래처</a></li>

			</ul>
		</div>
	</div>
</body>
</html>