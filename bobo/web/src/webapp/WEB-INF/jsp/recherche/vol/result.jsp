<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<title>Résultats de la recherche</title>
</head>
<body>
<h2>Résultats de la recherche</h2>

<c:choose>
<c:when test="${empty vols}">
<p>Aucun vol n'a été trouvé&nbsp;!</p>
</c:when>
<c:otherwise>

<display:table name="vols" id="vol">
<display:column property="code" title="Numéro"/>

<display:column title="Aéroport de départ">
${vol.aeroportDepart.aeroportId} [${vol.aeroportDepart.ville.nom}]
</display:column>

<display:column title="Aéroport d'arrivée">
${vol.aeroportArrivee.aeroportId} [${vol.aeroportArrivee.ville.nom}]
</display:column>

<display:column title="Date d'arrivée">
<fmt:formatDate value="${vol.dateDepart}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>

<display:column title="Date de départ">
<fmt:formatDate value="${vol.dateArrivee}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>
</display:table>

</c:otherwise>
</c:choose>

</body>
</html>
