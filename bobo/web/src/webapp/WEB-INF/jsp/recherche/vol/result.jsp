<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<title>Résultats de la recherche</title>
</head>
<body id="vol-recherche-resultat">
<h2>Résultats de la recherche</h2>

<c:choose>
<c:when test="${empty vols}">
<p>Aucun vol n'a été trouvé&nbsp;!</p>
</c:when>
<c:otherwise>

<p>Liste des vols entre l'aéroport <strong>${aeroportDepart.aeroportId}</strong> (${aeroportDepart.ville.nom})
et l'aéroport <strong>${aeroportArrivee.aeroportId}</strong> (${aeroportArrivee.ville.nom}), dans la période du
<strong><fmt:formatDate value="${dateDepart}" type="date" dateStyle="short"/></strong> au
<strong><fmt:formatDate value="${dateArrivee}" type="date" dateStyle="short"/></strong>&nbsp;:</p>

<display:table name="vols" id="vol">
<display:column property="code" title="Numéro"/>

<display:column title="Date d'arrivée">
<fmt:formatDate value="${vol.dateDepart}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>

<display:column title="Date de départ">
<fmt:formatDate value="${vol.dateArrivee}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>
</display:table>

<jsp:useBean class="java.util.Date" id="now"/>
<p class="date-recherche">Date de la recherche&nbsp;: <fmt:formatDate value="${now}" type="date" dateStyle="short"/>.</p>

</c:otherwise>
</c:choose>

</body>
</html>
