<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="rodrigo.hernandez.brea@gmail.com">
	<!--<link rel="icon" href="../../favicon.ico">-->
	
	<title><tiles:insertAttribute name="title" /></title>
	
	<tiles:insertAttribute name="head" />
</head>	
<body>
	<tiles:insertAttribute name="navbar" />
	<tiles:useAttribute id="list" name="modals" classname="java.util.List" />
	<c:forEach var="modal" items="${list}">
	  <tiles:insertAttribute value="${modal}" flush="true" />
	</c:forEach>
	<div id="main-container" class="container-fluid" style="margin-top:2%;">
		<div class="row">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<tiles:insertAttribute name="scripts" />

</body>
</html>