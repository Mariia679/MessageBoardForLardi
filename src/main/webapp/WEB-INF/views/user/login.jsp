<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<body class="templatemo-bg-image-1">
	<div class="container">
		<div class="col-md-12">
			<form:form class="form-horizontal templatemo-login-form-2"
				action="/login" method="post">
				<div class="row">
					<div class="col-md-12">
						<h1>Login Form Two</h1>
					</div>
				</div>
				<div class="row">
					<div class="templatemo-one-signin col-md-12">
						<div class="form-group">
							<div class="col-md-12">
								<label for="username" class="control-label">Username</label>
								<div class="templatemo-input-icon-container">
									<i class="fa fa-user"></i> <input type="text"
										class="form-control" id="username" name="login">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label for="password" class="control-label">Password</label>
								<div class="templatemo-input-icon-container">
									<i class="fa fa-lock"></i> <input type="password"
										class="form-control" name="password" id="password">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<div class="checkbox">
									<label> <input type="checkbox" name="remember-me">
										Remember me
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input type="submit" value="LOG IN" class="btn btn-warning">
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>