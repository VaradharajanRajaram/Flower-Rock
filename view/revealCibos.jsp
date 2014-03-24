<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>revealCibos</title>
<tiles:insertAttribute name="inn.cssResources" />
<tiles:insertAttribute name="inn.jsResources" />
<body>


	<tiles:insertAttribute name="inn.header" />
	<%-- <tiles:insertAttribute name="inn.leftStrip" /> --%>
	<h6>reveal Cibos</h6>
	<form:form method="post" action="/revealFood">
	<form:input path="foodName" cssStyle=""/>
	<form:input path="foodCost" cssStyle=""/>
	<form:input path="foodImg" cssStyle="" />

	</form:form>
	<tiles:insertAttribute name="inn.footer" />
</body>
</html>