<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<%@ page isErrorPage="true" %>
<!--
    $Id: 404.jsp,v 1.1 2005/01/19 16:21:43 romale Exp $
-->
<page:applyDecorator name="normal">
<html>
<head><title>Page introuvable</title></head>
<body id="erreur">
<h2>Page introuvable</h2>
<p><strong>Erreur 404</strong>. La page</a> que vous avez demandée est introuvable.</p>
<p><a href="${url}">${url}</a></p>
</body>
</html>
</page:applyDecorator>
