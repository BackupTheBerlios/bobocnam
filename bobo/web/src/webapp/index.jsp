<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<!--
    $Id: index.jsp,v 1.1 2005/01/13 13:39:52 romale Exp $
-->
<html>
<head>
<title>Hello world!</title>
</head>
<body>
<h1>Hello world!</h1>
<jsp:useBean id="now" class="java.util.Date"/>
<p>Il est <fmt:formatDate pattern="HH:mm:ss" value="${now}"/>.
Nous sommes le <fmt:formatDate pattern="dd/MM/yyyy" value="${now}"/>.</p>
</body>
</html>
 