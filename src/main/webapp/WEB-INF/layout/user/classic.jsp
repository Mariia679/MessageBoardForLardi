<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<security:csrfMetaTags />
<meta charset="UTF-8">
<script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js"></script>

<link rel="stylesheet" href="/resources/css/bootstrap.min.css">

<script src="/resources/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/resources/css/chosen.min.css">

<link href="/resources/css/bootstrap-social.css" rel="stylesheet"
	type="text/css">

<link href="/resources/css/templatemo_style.css" rel="stylesheet"
	type="text/css">

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700"
	rel="stylesheet" type="text/css">

<script src="/resources/js/chosen.jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="/resources/css/font-awesome.css">

<link rel="stylesheet" type="text/css" href="/resources/css/slider.css">

<link rel="stylesheet" type="text/css" href="/resources/css/mystyle.css">
<script>
	$(function() {
		$("select").chosen();
	});
</script>
<style type="text/css">
.bord {
	border: 3px solid #7c7c7f;
	border-radius: 10px;
	margin: 30px 30px 30px 80px;
	color: #7c7c7f;
	font-size: 30px;
	padding: 70px;
}

.bord:hover {
	color: #f2ba1f;
	border: 3px solid #f2ba1f;
	text-decoration: none;
}

.templatemo-bg-image-1 {
	background-color: rgb(60, 60, 60);
	background-image: url(resources/img/Black-Background.jpg);
}

.marg {
	margin-bottom: -10px;
}

.margin-padding {
	margin-left: 20px;
}

.btn-marg {
	margin-top: -14px;
}

.navbar-default .navbar-nav .open .dropdown-menu>li>a:hover {
	background-color: #cba328;
}

.navbar-default .navbar-nav .open .dropdown-menu {
	color: white;
	max-height: 800px;
}

.dropdowncostume {
	max-height: 800px;
}

.content {
	margin: 50px;
	margin-left: -50px;
}
</style>

<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<div class="allcontain">
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>
