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

<div class="resume">
<p><strong>${aeroportDepart.nom}</strong> (${aeroportDepart.ville.nom}, ${aeroportDepart.ville.pays.nom})</p>
<p>vers</p>
<p><strong>${aeroportArrivee.nom}</strong> (${aeroportArrivee.ville.nom}, ${aeroportArrivee.ville.pays.nom})</p>
<p>du <strong><fmt:formatDate value="${dateDepart}" type="date" dateStyle="short"/></strong> au
<strong><fmt:formatDate value="${dateArrivee}" type="date" dateStyle="short"/></strong>&nbsp;:</p>
</div>

<div class="nouvelle-recherche">
<p><a href="<c:url value='/recherche/vol/form.html'/>">Nouvelle recherche</a></p>
</div>

<display:table name="vols" id="vol" sort="page">
<display:column title="Num�ro">
<a href="<c:url value='/vol/afficher.html'><c:param name='id' value='${vol.volId}'/></c:url>">${vol.numero}</a>
</display:column>

<display:column property="compagnieAerienne.nom" title="Compagnie"/>

<display:column title="D�part">
<fmt:formatDate value="${vol.dateDepart}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>

<display:column title="Arriv�e">
<fmt:formatDate value="${vol.dateArrivee}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>
</display:table>

<jsp:useBean class="java.util.Date" id="now"/>
<p class="date-recherche">Date de la recherche&nbsp;: <fmt:formatDate value="${now}" type="date" dateStyle="short"/></p>

</c:otherwise>
</c:choose>

</body>
</html>
