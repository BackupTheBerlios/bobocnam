<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<title>R�sultats de la recherche</title>
</head>
<body>
<h2>R�sultats de la recherche</h2>

<c:choose>
<c:when test="${empty vols}">
<p>Aucun vol n'a �t� trouv�&nbsp;!</p>
</c:when>
<c:otherwise>

<display:table name="vols" id="vol">
<display:column property="code" title="Num�ro"/>

<display:column title="A�roport de d�part">
${vol.aeroportDepart.aeroportId} [${vol.aeroportDepart.ville.nom}]
</display:column>

<display:column title="A�roport d'arriv�e">
${vol.aeroportArrivee.aeroportId} [${vol.aeroportArrivee.ville.nom}]
</display:column>

<display:column title="Date d'arriv�e">
<fmt:formatDate value="${vol.dateDepart}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>

<display:column title="Date de d�part">
<fmt:formatDate value="${vol.dateArrivee}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>
</display:table>

</c:otherwise>
</c:choose>

</body>
</html>
