<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<!--
    $Id: connexion.jsp,v 1.1 2005/01/19 01:06:47 romale Exp $
-->
<html>
<head><title>Connexion</title></head>
<body>
<form action="<c:url value='/j_acegi_security_check'/>" method="post">
<fieldset>
<legend>Veuillez-vous identifier</legend>
<p>
    <label for="j_username">Identifiant</label>
    <input type="text" maxlength="64" id="j_username" name="j_username" class="default-focus"/>
</p>
<p>
    <label for="j_password">Mot de passe</label>
    <input type="password" maxlength="64" id="j_password" name="j_password"/>
</p>
<p class="boutons">
    <input type="submit" value="Valider"/>
</p>
</fieldset>
</form>
</body>
</html>
