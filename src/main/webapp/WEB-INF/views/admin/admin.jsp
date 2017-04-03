<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!-- HEADER -->
<div class="header ">
	<ul class="socialicon">
	</ul>
	<ul class="givusacall">
		<li>Give us a call : +66666666</li>
	</ul>
	<ul class="logreg ">
		<li class="navbar-form navbar-right marg"><security:authorize
				access="isAuthenticated()">
				<form:form action="/logout" method="POST">
					<button class="btn btn-default btn-xs btn-marg">Sign out</button>
				</form:form>
			</security:authorize></li>
		<li class="register "><security:authorize
				access="hasRole('ROLE_ADMIN')">

				<li><a href="/admin">Admin</a></li>
				<li class="dropdown"><a href="" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> Entities <span class="caret"></span>
				</a>

					<ul class="dropdown-menu">
						<li><a href="/admin/topic">Рубрики</a></li>
						<li><a href="/admin/registration">Регистрация</a></li>
					</ul></li>

			</security:authorize></li>
	</ul>
</div>
<nav class="topnavbar navbar-default topnav">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed toggle-costume"
				data-toggle="collapse" data-target="#upmenu" aria-expanded="false">
				<span class="sr-only"> Toggle navigaion</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
	</div>
	<div class="collapse navbar-collapse " id="upmenu">
		<ul class="nav navbar-nav" id="navbarontop">
			<li class="active"><a href="/">HOME</a></li>
			<li><a href="/allTopics">TOPIC </a></li>

		</ul>
	</div>

</nav>
<!-- HEADER -->
<!-- BODY -->
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<ul class="nav navbar-nav ">
				<li><a href="/admin/topic">Рубрика</a></li>
				<li><a href="/admin/registration">Регистрация</a></li>
			</ul>
		</div>
	</nav>
</div>
<!-- BODY -->
<!--FOOTER  -->
<div class="bottommenu">
	<div class="bottomlogo">
		<span class="dotlogo">&bullet;</span>Доска объявлений<span
			class="dotlogo">&bullet;;</span>
	</div>
	<ul class="nav nav-tabs bottomlinks">
		<li role="presentation"><a href="#/" class="text-uppercase">Lorem
				ipsum</a></li>
		<li role="presentation"><a href="#/" class="text-uppercase">Lorem</a></li>
		<li role="presentation"><a href="#/" class="text-uppercase">Lorem
				ipsum</a></li>
		<li role="presentation"><a href="#/" class="text-uppercase">Lorem</a></li>
		<li role="presentation"><a href="#/" class="text-uppercase">Lorem</a></li>
		<li role="presentation"><a href="#/" class="text-uppercase">Lorem
				ipsum</a></li>
		<li role="presentation"><a href="#/" class="text-uppercase">Lorem</a></li>
	</ul>
	<p>
		"Lorem ipsum dolor sit amet, consectetur, sed do eiusmod tempor
		incididunt <br> eiusmod tempor incididunt"
	</p>
	<br>
	<div class="bottomsocial">
		<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
			class="fa fa-twitter"></i></a> <a href="#"><i
			class="fa fa-google-plus"></i></a> <a href="#"><i
			class="fa fa-pinterest"></i></a>
	</div>
	<div class="footer"></div>
</div>
<!-- FOOTER -->
