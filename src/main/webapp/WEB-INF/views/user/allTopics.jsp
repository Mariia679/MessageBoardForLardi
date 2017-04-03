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
			<li><a href="/">HOME</a></li>
			<li class="active"><a href="/allTopics">TOPIC </a></li>

		</ul>
	</div>

</nav>
<!-- HEADER -->

<!--BODY  -->
<nav class="navbar navbar-default midle-nav">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed textcostume"
			data-toggle="collapse" data-target="#navbarmidle"
			aria-expanded="false">
			<h1>SEARCH TEXT</h1>
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
	</div>
	<div class="collapse navbar-collapse" id="navbarmidle">
		<div class="searchtxt">
			<h1>SEARCH TEXT</h1>
		</div>
		<form:form
			class="form-inline navbar-form navbar-left searchformmargin"
			action="/allTopics" method="GET" modelAttribute="filter">

			<div class="form-group">
				<form:input path="nameUser" class="form-control "
					placeholder="Username" />

			</div>
			<div class="form-group">
				<label class="control-label col-sm-12 weight-font-size">Your
					ads</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" items="${ads}" itemValue="id"
						itemLabel="username" path="userId" id="start" />
				</div>
			</div>
			<ul class="nav navbar-nav navbarborder navbar-right margin-padding">
				<li class="li-category"><strong>Topic</strong> <form:select
						class="form-control" path="topicId" id="topic"
						items="${headTopics}" itemValue="id" itemLabel="name" /></li>
				<li class="li-search">
					<button class="searchbutton">
						<span class="glyphicon glyphicon-search "></span>
					</button>
				</li>
			</ul>
		</form:form>
		<script>
			document
					.getElementById('start1')
					.addEventListener(
							'click',
							function() {
								var input = document.getElementById("nameUser"), self = document
										.getElementById('start1');
								self.checked ? input.disabled = 'disabled'
										: input.disabled = '';
							});
		</script>
	</div>
</nav>
<div class="row" style="margin-top: 15px;">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/allTopics" method="POST"
			modelAttribute="advert" enctype="multipart/form-data">
			<div class="form-group">
				<label for="topic" class="col-sm-2 control-label">Topic</label>
				<div class="col-sm-7">
					<form:select class="form-control" path="topic" id="topic"
						items="${headTopics}" itemValue="id" itemLabel="name" />
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Name</label>
				<div class="col-sm-7">
					<form:input class="form-control" path="name" id="name"
						maxlength="30" />
				</div>
				<label style="color: red; text-align: left;" for="name"
					class="col-sm-3 control-label"><form:errors path="name" /></label>
			</div>
			<div class="form-group">
				<label for="review" class="col-sm-2 control-label">Announcement</label>
				<div class="col-sm-7">
					<form:textarea class="form-control" path="review" id="review"
						cols="73" rows="6" maxlength="400" />

				</div>
				<label style="color: red; text-align: left;" for="review"
					class="col-sm-3 control-label"><form:errors path="review" /></label>
			</div>
			<div class="form-group">
				<label for="file" class="col-sm-2 control-label">Image</label>
				<div class="col-sm-8">
					<input name="file" type="file" id="file">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Create</button>
					<a class="btn btn-primary" href="/allTopics/cancel">Отменить</a>
				</div>
			</div>
		</form:form>
	</div>
</div>
<div class="row">
	<div class="feturedimage">
		<div class="col-md-12 col-xs-12">
			<div class="row firstrow">
				<c:forEach items="${page.content}" var="advert">
					<div
						class="col-lg-12 col-xs-12 col-sm-12 col-md-12 costumcol colborder1"
						style="margin: 10px 0px;">
						<div class="row costumrow">
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 img1colon"
								style="padding-top: 80px;">
								<img
									src="/images/advert/${advert.id}.jpg?version=${advert.version}"
									alt="Here is image of advert">
							</div>
							<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9"
								style="margin-bottom: 20px; margin-top: -45px;">
								<div class="featurecontant">
									<h1>${advert.name}</h1>
									<h1>${advert.user.username}</h1>
									<p>${advert.date}</p>
									<p style="font-size: 15px;">${advert.review}</p>

									<div class="text-right" style="margin: 10px;">
										<security:authorize
											access="isAuthenticated() and principal.username=='${advert.user.username}'">
											<a class="btn btn-warning"
												href="/allTopics/update/${advert.id}">update</a>
											<a class="btn btn-danger"
												href="/allTopics/delete/${advert.id}">delete</a>
										</security:authorize>

									</div>
								</div>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
</div>


<!-- BODY -->
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
