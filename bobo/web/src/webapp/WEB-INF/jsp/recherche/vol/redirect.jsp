<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:formatDate var="dateDepartValue" value="${dateDepart}" pattern="yyyyMMdd"/>
<fmt:formatDate var="dateArriveeValue" value="${dateArrivee}" pattern="yyyyMMdd"/>

<c:redirect url="/recherche/vol/result.html">
<c:param name="ad" value="${aeroportDepart.aeroportId}"/>
<c:param name="aa" value="${aeroportArrivee.aeroportId}"/>
<c:param name="dd" value="${dateDepartValue}"/>
<c:param name="da" value="${dateArriveeValue}"/>
</c:redirect>
