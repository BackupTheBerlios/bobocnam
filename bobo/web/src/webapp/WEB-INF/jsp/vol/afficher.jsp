<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<html>
<head>
<fmt:formatDate var="dateDepart" value="${vol.dateDepart}" type="date" dateStyle="short"/>
<title>Détails du vol ${vol.numero} du ${dateDepart}</title>
</head>
<body id="vol-details">
<h2>Détails du vol ${vol.numero}</h2>

<table summary="Caractéristiques du vol ${vol.volId}">
<tr>
<td><label><fmt:message key="aeroportDepart"/></label></td>
<td>${vol.aeroportDepart.nom} (${vol.aeroportDepart.ville.nom}, ${vol.aeroportDepart.ville.pays.nom})</td>
</tr>
<tr>
<td><label><fmt:message key="aeroportArrivee"/></label></td>
<td>${vol.aeroportArrivee.nom} (${vol.aeroportArrivee.ville.nom}, ${vol.aeroportArrivee.ville.pays.nom})</td>
</tr>
</table>
</body>
</html>