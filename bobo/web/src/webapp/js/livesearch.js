/*
 * $Id: livesearch.js,v 1.1 2005/03/01 18:36:10 romale Exp $
 */

/*
// +----------------------------------------------------------------------+
// | Orginial Code Care Of:                                               |
// +----------------------------------------------------------------------+
// | Copyright (c) 2004 Bitflux GmbH                                      |
// +----------------------------------------------------------------------+
// | Licensed under the Apache License, Version 2.0 (the "License");      |
// | you may not use this file except in compliance with the License.     |
// | You may obtain a copy of the License at                              |
// | http://www.apache.org/licenses/LICENSE-2.0                           |
// | Unless required by applicable law or agreed to in writing, software  |
// | distributed under the License is distributed on an "AS IS" BASIS,    |
// | WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or      |
// | implied. See the License for the specific language governing         |
// | permissions and limitations under the License.                       |
// +----------------------------------------------------------------------+
// | Author: Bitflux GmbH <devel@bitflux.ch>                              |
// |         http://blog.bitflux.ch/p1735.html                            |
// +----------------------------------------------------------------------+
//
//
// +----------------------------------------------------------------------+
// | Heavily Modified by Jeff Minard (07/09/04)                           |
// +----------------------------------------------------------------------+
// | Same stuff as above, yo!                                             |
// +----------------------------------------------------------------------+
// | Author: Jeff Minard <jeff-js@creatimation.net>                       |
// |         http://www.creatimation.net                                  |
// +----------------------------------------------------------------------+
//
//
// +----------------------------------------------------------------------+
// | What is this nonsense?? (07/09/04)                                   |
// +----------------------------------------------------------------------+
// | This is a script that, by using XMLHttpRequest javascript objects    |
// | you can quickly add some very click live interactive feed back to    |
// | your pages that reuire server side interaction.                      |
// |                                                                      |
// | For instance, you use this to emulate a "live searching" feature     |
// | wherein users type in key phrases, and once they have stopped typing |
// | the script will automatically search and retrive *without* a page    |
// | reload.
// |                                                                      |
// | In another instance, I use this to product live comments by passing  |
// | the text to a Textile class that parses it to valid HTML. After      |
// | parsing, the html is returned and displayed on the page as the       |
// | user types.                                                          | 
// +----------------------------------------------------------------------+
//
//
// +----------------------------------------------------------------------+
// | Heavily Modified (again) by Alexandre ROMAN (01/03/05)               |
// +----------------------------------------------------------------------+
// | All the functions are now in an javascript object, in order to       |
// | be able to reuse it several times in a page.                         |
// +----------------------------------------------------------------------+
// | Author: Alexandre ROMAN <romale@users.berlios.de>                    |
// |         http://bobocnam.berlios.de                                   |
// +----------------------------------------------------------------------+

*/


function LiveSearch(searchFieldId, resultFieldId, processUri, emptyString, timeout) {
	this.searchFieldId = searchFieldId;
	this.resultFieldId = resultFieldId;
	this.processUri = processUri;
	this.emptyString = emptyString;
	this.timeout = timeout;
	
	this.liveReq = false;
	this.t = null;
	this.liveReqLast = "";
	this.isIE = false;

	if (window.XMLHttpRequest) {
		this.liveReq = new XMLHttpRequest();
	}
}

LiveSearch.prototype.init = function() {
	var pThis = this;
	var f = function(){ pThis.start(); }

	if (navigator.userAgent.indexOf("Safari") > 0) {
		document.getElementById(this.searchFieldId).addEventListener("keydown", f,false);
	} else if (navigator.product == "Gecko") {
		document.getElementById(this.searchFieldId).addEventListener("keypress", f,false);
	} else {
		document.getElementById(this.searchFieldId).attachEvent('onkeydown', f);
		isIE = true;
	}

	if(this.emptyString == '') {
		// set the result field to hidden, or to default string
		document.getElementById(this.resultFieldId).style.display = "none";
	} else {
		document.getElementById(this.resultFieldId).innerHTML = this.emptyString;
	}
}
	
LiveSearch.prototype.start = function() {
	if (this.t) {
		window.clearTimeout(this.t);
	}

	var pThis = this;
	var f = function(){ pThis.doRequest(); }
	this.t = window.setTimeout(f, this.timeout);
}

LiveSearch.prototype.doRequest = function() {
	if (this.liveReqLast != document.getElementById(this.searchFieldId).value && document.getElementById(this.searchFieldId).value != "") {
		if (this.liveReq && this.liveReq.readyState < 4) {
			this.liveReq.abort();
		}
		if (window.XMLHttpRequest) {
			// branch for IE/Windows ActiveX version
		} else if (window.ActiveXObject) {
			this.liveReq = new ActiveXObject("Microsoft.XMLHTTP");
		}

		var pThis = this;
		var f = function(){ pThis.processReqChange(); }

		this.liveReq.onreadystatechange = f;
		this.liveReq.open("GET", this.processUri + "?q=" + escape(document.getElementById(this.searchFieldId).value));
		this.liveReqLast = document.getElementById(this.searchFieldId).value;
		this.liveReq.send(null);
	} else if(document.getElementById(this.searchFieldId).value == "") {
		if(this.emptyString == '') {
			document.getElementById(this.resultFieldId).innerHTML = '';
			document.getElementById(this.resultFieldId).style.display = "none";
		} else {
			document.getElementById(this.resultFieldId).innerHTML = this.emptyString;
		}
	}
}

LiveSearch.prototype.processReqChange = function() {
	if (this.liveReq.readyState == 4) {
		document.getElementById(this.resultFieldId).innerHTML = this.liveReq.responseText;
		if(this.emptyString == '') {
			document.getElementById(this.resultFieldId).style.display = "block";
		}
	}
}
