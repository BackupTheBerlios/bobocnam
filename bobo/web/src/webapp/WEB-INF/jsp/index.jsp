<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<!--
    $Id: index.jsp,v 1.3 2005/01/19 16:25:22 romale Exp $
-->
<html>
<head>
<title>Page d'accueil</title>
</head>
<body id="accueil">
<h2>Page d'accueil</h2>
<div class="intro">
<p>Bienvenue dans <fmt:message key="webapp.titre"/>&nbsp;!</p>
<p>Cette application est actuellement en cours de développement.
Il est possible que certaines fonctionnalités ne soient pas encore
pleinement implémentées, ou qu'elles comportent des erreurs.</p>
<c:set var="mail_dest"><fmt:bundle basename="mail"><fmt:message key="mail.dest"/></fmt:bundle></c:set>
<p>Merci de bien vouloir <a href='mailto:${fn:replace(mail_dest, "@", " at ")}'>nous contacter</a>
pour nous faire part de votre avis.</p>
</div>
</body>
</html>
