<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<%@ page isErrorPage="true" %>
<!--
    $Id: 500.jsp,v 1.1 2005/01/19 16:21:43 romale Exp $
-->
<page:applyDecorator name="normal">
<html>
<head><title>Erreur du serveur</title></head>
<body id="erreur">
<h2>Erreur du serveur</h2>
<p><strong>Erreur 500</strong>. Le serveur a rencontré une erreur.</p>
<c:set var="id"><fmt:formatDate value="${date}" pattern="yyyyMMddHHmmss"/></c:set>
<c:set var="mail_dest"><fmt:bundle basename="mail"><fmt:message key="mail.dest"/></fmt:bundle></c:set>
<p>Merci de
<a href="mailto:${fn:replace(mail_dest, "@", " at ")}?subject=Erreur <fmt:message key='webapp.titre'/> ${id}">nous contacter</a>
pour nous aider à résoudre ce problème.</p>
<pre>${pageContext.errorData.throwable.message}</pre>
</body>
</html>
</page:applyDecorator>
