<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<fmt:formatDate var="dateDepart" value="${vol.periode.dateDebut}" type="date" dateStyle="short"/>
<title>Détails du vol ${vol.volGenerique.numero} du ${dateDepart}</title>
</head>
<body id="vol-details">
<h2>Détails du vol ${vol.volGenerique.numero}</h2>

<table summary="Caractéristiques du vol ${vol.volId}">
<tr>
<td><label><fmt:message key="aeroportDepart"/></label></td>
<td>${vol.volGenerique.aeroportDepart.nom} (${vol.volGenerique.aeroportDepart.ville.nom}, ${vol.volGenerique.aeroportDepart.ville.pays.nom})</td>
</tr>
<tr>
<td><label><fmt:message key="aeroportArrivee"/></label></td>
<td>${vol.volGenerique.aeroportArrivee.nom} (${vol.volGenerique.aeroportArrivee.ville.nom}, ${vol.volGenerique.aeroportArrivee.ville.pays.nom})</td>
</tr>
</table>
</body>
</html>