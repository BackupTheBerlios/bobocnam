<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<title>Rechercher un vol</title>
</head>
<body id="vol-recherche">
<h2>Rechercher un vol</h2>

<form action="<c:url value='/recherche/vol/form.html'/>" method="post">
<fieldset>
<legend>Paramètres de la recherche</legend>
<table legend="Formulaire de recherche de vol">

<tr>
<th><label for="aeroportDepart">Aéroport de départ</label></th>
<td>
<spring:bind path="command.aeroportDepart">
<input type="text" id="aeroportDepart" name="${status.expression}" length="64" value="${status.value}"/>
</spring:bind>
</td>
</tr>

<tr>
<th><label for="aeroportArrivee">Aéroport d'arrivée</label></th>
<td>
<spring:bind path="command.aeroportArrivee">
<input type="text" id="aeroportArrivee" name="${status.expression}" length="64" value="${status.value}"/>
</spring:bind>
</td>
</tr>

<tr>
<td>&nbsp;</td>
<td>
<spring:bind path="command.volsDirects">
<input type="checkbox" id="volsDirects" name="${status.expression}" value="${status.value}"/>
</spring:bind>
<label for="volsDirects" class="checkbox">uniquement les vols directs</label></td>
</tr>

<tr>
<td>&nbsp;</td>
<td>
<spring:bind path="command.allerSimple">
<input type="checkbox" id="allerSimple" name="${status.expression}" value="${status.value}"/>
</spring:bind>
<label for="allerSimple" class="checkbox">aller simple</label></td>
</tr>

<tr>
<th><label for="dateDepart">Date de départ</label></th>
<td>
<spring:bind path="command.dateDepart">
<input type="text" id="dateDepart" name="${status.expression}" length="16" value="${status.value}"/>
</spring:bind>
</td>
</tr>

<tr>
<th><label for="dateArrivee">Date d'arrivée</label></th>
<td>
<spring:bind path="command.dateArrivee">
<input type="text" id="dateArrivee" name="${status.expression}" length="16" value="${status.value}"/>
</spring:bind>
</td>
</tr>

<tr class="boutons">
<td colspan="4">
<input type="submit" value="<fmt:message key='action.valider'/>" class="ok"/>
</td>
</tr>
</table>
</fieldset>
</form>
</body>
</html>
