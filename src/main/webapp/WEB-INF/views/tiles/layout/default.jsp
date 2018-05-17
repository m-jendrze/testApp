<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title" /></title>
	    <link rel="shortcut icon" href="<c:url value='/static/favicon.ico' />" />
        <link href="<c:url value='/static/css/jquery-ui.min.css' />" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/static/js/jquery/jtable/themes/metro/darkorange/jtable.min.css' />" rel="stylesheet" type="text/css" />

        <script src="<c:url value='/static/js/jquery/jquery.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/static/js/jquery/jquery-ui.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/static/js/jquery/jtable/jquery.jtable.min.js' />" type="text/javascript"></script>

    </head>
    <body>
            <header id="header">
                <tiles:insertAttribute name="header" />
            </header>

            <section id="sidemenu">
                <tiles:insertAttribute name="menu" />
            </section>

            <section id="content">
                <tiles:insertAttribute name="body" />
            </section>

            <footer id="footer">
                <tiles:insertAttribute name="footer" />
            </footer>
    </body>
</html>