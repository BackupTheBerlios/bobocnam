<?xml version="1.0" encoding="ISO-8859-1"?>
<rss version="0.91" encoding="ISO-8859-1">
<%@ page contentType="text/xml; charset=ISO-8859-1" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<fmt:formatDate var="dateDepartEncode" value="${dateDepart}" pattern="yyyyMMdd"/>
<fmt:formatDate var="dateArriveeEncode" value="${dateArrivee}" pattern="yyyyMMdd"/>

<fmt:formatDate var="dateDepartFormate" value="${dateDepart}" type="date" dateStyle="short"/>
<fmt:formatDate var="dateArriveeFormate" value="${dateArrivee}" type="date" dateStyle="short"/>

<channel>
<title><fmt:message key="webapp.titre"/> : vols entre ${aeroportDepart.ville.nom} - ${aeroportArrivee.ville.nom}</title>
<link>${absoluteUrl}</link>
<description>Vols entre le ${dateDepartFormate} et le ${dateArriveeFormate} entre ${aeroportDepart.ville.nom} et ${aeroportArrivee.ville.nom}</description>
<copyright>Copyright 2005 <fmt:message key="webapp.titre.long"/></copyright>

<items>

<c:forEach var="vol" items="${vols}">
<item>
<title>${vol.volGenerique.compagnieAerienne.nom} ${vol.volGenerique.numero} <fmt:formatDate value="${vol.periode.dateDebut}" dateStyle="short" timeStyle="short" type="both"/> - <fmt:formatDate value="${vol.periode.dateFin}" dateStyle="short" timeStyle="short" type="both"/></title>
<author><fmt:message key="webapp.titre"/></author>
<c:url var="volUrl" value="${absoluteUrl}/vol/${vol.volId}.html"/>
<link>${volUrl}</link>
<guid>${volUrl}</guid>
</item>
</c:forEach>

</items>
</channel>
</rss>
