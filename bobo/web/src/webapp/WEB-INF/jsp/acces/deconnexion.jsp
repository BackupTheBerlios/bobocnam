<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<!--
    $Id: deconnexion.jsp,v 1.2 2005/01/19 10:05:41 romale Exp $
-->
<html>
<head>
<title>Session déconnectée</title>
<meta http-equiv="refresh" content="10;url=<c:url value='/'/>">
</head>
<body>
<h2>Session déconnectée</h2>
<p>Votre session a été fermée. Vous pouvez maintenant quitter
votre navigateur en toute sécurité.</p>
<p>Dans quelques instants, vous serez redirigé automatiquement vers
<a href="<c:url value='/'/>">la page d'accueil</a>.</p>
</body>
</html>
