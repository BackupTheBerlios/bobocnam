<%@ page contentType="text/javascript" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
/*
 * $Id: vol-recherche.js.jsp,v 1.2 2005/03/03 23:18:16 romale Exp $
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


function initDateDepartCalendar() {
	Calendar.setup({
		inputField : "dateDepart",
		ifFormat   : "%d/%m/%Y",
	});
}


function initDateArriveeCalendar() {
	Calendar.setup({
		inputField : "dateArrivee",
		ifFormat   : "%d/%m/%Y",
	});
}


addEvent(window, "load", initAeroportDepartLiveSearch);
addEvent(window, "load", initAeroportArriveeLiveSearch);

addEvent(window, "load", initDateDepartCalendar);
addEvent(window, "load", initDateArriveeCalendar);
