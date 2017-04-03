<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>

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
<!-- BODY -->
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/admin/topic">Топик</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/registration">Регистрация</a></li>
				</ul>
			</div>
		</div>

	</nav>
</div>
<div class="row">
	<div class="container">
		<div class="col-md-12 col-xs-12">
			<div class="row">
				<div class="col-md-12 col-xs-12">
					<form:form class="form-horizontal" action="/admin/topic"
						method="POST" modelAttribute="topic">
						<div class="form-group">
							<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors
									path="name" /></label>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<form:input class="form-control" path="name" id="name" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Create</button>
								<a class="btn btn-primary" href="/admin/topic/cancel">Отменить</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-xs-4">
					<h3>Topic name</h3>
				</div>

				<div class="col-md-4 col-xs-4">
					<h3>Update</h3>
				</div>
				<div class="col-md-4 col-xs-4">
					<h3>Delete</h3>
				</div>
			</div>

			<c:forEach items="${topics}" var="topic">
				<div class="row">
					<div class="col-md-4 col-xs-4">${topic.name}</div>
					<div class="col-md-4 col-xs-4">
						<a class="btn btn-warning"
							href="/admin/topic/update/${topic.id}">update</a>
					</div>
					<div class="col-md-4 col-xs-4">
						<a class="btn btn-danger"
							href="/admin/topic/delete/${topic.id}">delete</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
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