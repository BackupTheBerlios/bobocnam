<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<title>Rechercher un vol</title>
<script type="text/javascript"
    src="<c:url value='/js/livesearch.js'/>"></script>
<script type="text/javascript"
    src="<c:url value='/js/vol-recherche.js.jsp'/>"></script>
</head>
<body id="vol-recherche">
<h2>Rechercher un vol</h2>

<form action="<c:url value='/recherche/vol/form.html'/>" method="post">
<fieldset>
<legend>Param�tres de la recherche</legend>
<table legend="Formulaire de recherche de vol">

<tr>
<th><label for="aeroportDepart">A�roport de d�part</label></th>
<td>
<spring:bind path="command.aeroportDepart">
<input type="text" id="aeroportDepart" name="${status.expression}" length="64" value="${status.value}"/>
</spring:bind>
<div id="aeroportDepartResult" class="livesearch">/div>
</td>
</tr>

<tr>
<th><label for="aeroportArrivee">A�roport d'arriv�e</label></th>
<td>
<spring:bind path="command.aeroportArrivee">
<input type="text" id="aeroportArrivee" name="${status.expression}" length="64" value="${status.value}"/>
</spring:bind>
<div id="aeroportArriveeResult" class="livesearch">/div>
</td>
</tr>

<tr>
<td colspan="2">
<div class="aide">
<p>Si vous ne connaissez pas le code international de l'a�roport,
tapez le nom de la ville et choisissez l'a�roport parmi la liste qui s'affiche.</p>
</div>
</td>
</tr>

<tr>
<td>&nbsp;</td>
<td>
<spring:bind path="command.volDirect">
<input type="checkbox" id="volDirect" name="${status.expression}" value="${status.value}"/>
</spring:bind>
<label for="volDirect" class="checkbox">vol direct sans escales</label></td>
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
<th><label for="dateDepart">Date de d�part</label></th>
<td>
<spring:bind path="command.dateDepart">
<input type="text" id="dateDepart" name="${status.expression}" length="16" value="${status.value}"/>
</spring:bind>
</td>
</tr>

<tr>
<th><label for="dateArrivee">Date d'arriv�e</label></th>
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
