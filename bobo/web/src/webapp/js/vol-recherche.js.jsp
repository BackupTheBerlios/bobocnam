<%@ page contentType="text/javascript" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
/*
 * $Id: vol-recherche.js.jsp,v 1.1 2005/03/01 18:36:28 romale Exp $
 */

function setAeroport(aeroportCode, aeroportInputElem, aeroportResultElem) {
	setInputValue(aeroportInputElem, aeroportCode);
	hideElement(aeroportResultElem);
}


function initAeroportDepartLiveSearch() {
	var aeroportDepartLiveSearch = new LiveSearch("aeroportDepart",
	    "aeroportDepartResult", "<c:url value='/recherche/vol/aeroportDepart.html'/>", "", 50);	
	aeroportDepartLiveSearch.init();
}


function initAeroportArriveeLiveSearch() {
	var aeroportArriveeLiveSearch = new LiveSearch("aeroportArrivee",
	    "aeroportArriveeResult", "<c:url value='/recherche/vol/aeroportArrivee.html'/>", "", 50);	
	aeroportArriveeLiveSearch.init();
}


addEvent(window, "load", initAeroportDepartLiveSearch);
addEvent(window, "load", initAeroportArriveeLiveSearch);
