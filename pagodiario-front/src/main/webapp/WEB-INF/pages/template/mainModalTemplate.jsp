<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<tiles:insertAttribute name="head" />
</head>
<body>
	<tiles:insertAttribute name="navbar" />
	<tiles:useAttribute id="list" name="modals" classname="java.util.List" />
	<c:forEach var="modal" items="${list}">
	  <tiles:insertAttribute value="${modal}" flush="true" />
	  <br/>
	</c:forEach>
	<div id="main-container" class="container-fluid">
		<div class="row">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<tiles:insertAttribute name="scripts" />

</body>
</html>