<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<title>R�sultats de la recherche</title>
</head>
<body id="vol-recherche-resultat">
<h2>R�sultats de la recherche</h2>

<c:choose>
<c:when test="${empty vols}">
<p>Aucun vol n'a �t� trouv�&nbsp;!</p>
</c:when>
<c:otherwise>

<p>Liste des vols entre l'a�roport <strong>${aeroportDepart.aeroportId}</strong> (${aeroportDepart.ville.nom})
et l'a�roport <strong>${aeroportArrivee.aeroportId}</strong> (${aeroportArrivee.ville.nom}), dans la p�riode du
<strong><fmt:formatDate value="${dateDepart}" type="date" dateStyle="short"/></strong> au
<strong><fmt:formatDate value="${dateArrivee}" type="date" dateStyle="short"/></strong>&nbsp;:</p>

<display:table name="vols" id="vol">
<display:column property="code" title="Num�ro"/>

<display:column title="Date d'arriv�e">
<fmt:formatDate value="${vol.dateDepart}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>

<display:column title="Date de d�part">
<fmt:formatDate value="${vol.dateArrivee}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>
</display:table>

<jsp:useBean class="java.util.Date" id="now"/>
<p class="date-recherche">Date de la recherche&nbsp;: <fmt:formatDate value="${now}" type="date" dateStyle="short"/>.</p>

</c:otherwise>
</c:choose>

</body>
</html>
