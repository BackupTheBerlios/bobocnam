<?xml version="1.0" encoding="ISO-8859-1"?>
<%@ page contentType="text/xml; charset=ISO-8859-1" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<c:if test="${!empty aeroports}">
<div class="livesearch">
<ul>
<c:forEach var="aeroport" items="${aeroports}">
<li><a href="#" onclick="setAeroport('${aeroport.aeroportId}', '${aeroportSearchField}', '${aeroportResultField}'); return false;"><strong>${aeroport.aeroportId}</strong> ${aeroport.nom} (${aeroport.ville.nom}, ${aeroport.ville.pays.nom})</a></li>
</c:forEach>
</ul>
</div>
</c:if>
