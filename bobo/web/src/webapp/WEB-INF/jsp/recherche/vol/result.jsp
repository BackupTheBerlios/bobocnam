<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<title>Vols entre ${aeroportDepart.ville.nom} et ${aeroportArrivee.ville.nom}</title>

<fmt:formatDate var="dateDepartEncode" value="${dateDepart}" pattern="yyyyMMdd"/>
<fmt:formatDate var="dateArriveeEncode" value="${dateArrivee}" pattern="yyyyMMdd"/>
<c:url var="rssUrl" value="/recherche/vol/rss.xml">
<c:param name="ad" value="${aeroportDepart.aeroportId}"/>
<c:param name="aa" value="${aeroportArrivee.aeroportId}"/>
<c:param name="dd" value="${dateDepartEncode}"/>
<c:param name="da" value="${dateArriveeEncode}"/>
</c:url>
<c:set var="rssUrl">${fn:escapeXml(rssUrl)}</c:set>
<link rel="alternate" href="${rssUrl}" type="application/rss+xml" title="Bobo : vols entre ${aeroportDepart.ville.nom} et ${aeroportArrivee.ville.nom}"/>
</head>
<body id="vol-recherche-resultat">
<h2>Résultats de la recherche</h2>

<c:choose>
<c:when test="${empty vols}">
<p>Aucun vol n'a été trouvé&nbsp;!</p>
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

<div class="rss">
<p><a href="${rssUrl}" title="Afficher le flux RSS"><img src="<c:url value='/images/rss-icon.gif'/>" alt="RSS" width="36" height="14"/></a></p>
</div>

<display:table name="vols" id="vol" sort="page">
<display:column title="Numéro">
<a href="<c:url value='/vol/afficher.html'><c:param name='id' value='${vol.volId}'/></c:url>">${vol.numero}</a>
</display:column>

<display:column property="compagnieAerienne.nom" title="Compagnie"/>

<display:column title="Départ">
<fmt:formatDate value="${vol.dateDepart}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>

<display:column title="Arrivée">
<fmt:formatDate value="${vol.dateArrivee}" dateStyle="short" timeStyle="short" type="both"/>
</display:column>
</display:table>

<jsp:useBean class="java.util.Date" id="now"/>
<p class="date-recherche">Date de la recherche&nbsp;: <fmt:formatDate value="${now}" type="date" dateStyle="short"/></p>

</c:otherwise>
</c:choose>

</body>
</html>
