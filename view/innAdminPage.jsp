<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertAttribute name="inn.cssResources" />
<tiles:insertAttribute name="inn.jsResources" />
<body>
	<tiles:insertAttribute name="inn.header" />
	
	<tiles:insertAttribute name="inn.leftStrip" />
	<!-- <h3>Hello</h3> -->
	<tiles:insertAttribute name="inn.footer" />
</body>
</html>