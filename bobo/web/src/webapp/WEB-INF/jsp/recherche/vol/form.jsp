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

<%@ include file="/WEB-INF/jspf/errors.jspf" %>

<form action="<c:url value='/recherche/vol/form.html'/>" method="post">
<fieldset>
<legend>Paramètres de la recherche</legend>
<table summary="Formulaire de recherche de vol">

<tr>
<th><label for="aeroportDepart"><fmt:message key="aeroportDepart"/></label></th>
<td>
<c:import url="/WEB-INF/jspf/html-text.jspf">
<c:param name="path" value="command.aeroportDepart"/>
<c:param name="id" value="aeroportDepart"/>
</c:import>
<div id="aeroportDepartResult" class="livesearch"></div>
</td>
</tr>

<tr>
<th><label for="aeroportArrivee"><fmt:message key="aeroportArrivee"/></label></th>
<td>
<c:import url="/WEB-INF/jspf/html-text.jspf">
<c:param name="path" value="command.aeroportArrivee"/>
<c:param name="id" value="aeroportArrivee"/>
</c:import>
<div id="aeroportArriveeResult" class="livesearch"></div>
</td>
</tr>

<tr>
<td colspan="2">
<div class="aide">
<p>Si vous ne connaissez pas le code international de l'aéroport,
tapez le nom de la ville et choisissez l'aéroport parmi la liste qui s'affiche.</p>
</div>
</td>
</tr>

<tr>
<td>&nbsp;</td>
<td>
<c:import url="/WEB-INF/jspf/html-checkbox.jspf">
<c:param name="path" value="command.volDirect"/>
<c:param name="id" value="volDirect"/>
</c:import>
<label for="volDirect" class="checkbox"><fmt:message key="volDirect"/></label></td>
</tr>

<tr>
<td>&nbsp;</td>
<td>
<c:import url="/WEB-INF/jspf/html-checkbox.jspf">
<c:param name="path" value="command.allerSimple"/>
<c:param name="id" value="allerSimple"/>
</c:import>
<label for="allerSimple" class="checkbox"><fmt:message key="allerSimple"/></label></td>
</tr>

<tr>
<th><label for="dateDepart"><fmt:message key="dateDepart"/></label></th>
<td>
<c:import url="/WEB-INF/jspf/html-text.jspf">
<c:param name="path" value="command.dateDepart"/>
<c:param name="id" value="dateDepart"/>
</c:import>
</td>
</tr>

<tr>
<th><label for="dateArrivee"><fmt:message key="dateArrivee"/></label></th>
<td>
<c:import url="/WEB-INF/jspf/html-text.jspf">
<c:param name="path" value="command.dateArrivee"/>
<c:param name="id" value="dateArrivee"/>
</c:import>
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
