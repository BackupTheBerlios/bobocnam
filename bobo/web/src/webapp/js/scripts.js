/*
 * $Id: scripts.js,v 1.4 2005/02/23 20:13:28 romale Exp $
 */

// variables de configuration pour le script de compatibilité IE 7
// http://dean.edwards.name/IE7/usage/configure.html
IE7_PNG_SUFFIX = ".png";


function init() {
    installArrayExtensions();

    initMenu();
    setDefaultFocus();
}


// http://www.alistapart.com/d/popuplinks/lib.js
function installArrayExtensions() {
    if (!Array.prototype.push) {
        Array.prototype.push = function() {
            for (var i = 0; i < arguments.length; ++i) {
                this[this.length] = arguments[i];
            }
            return this.length;
        }
    }

    Array.prototype.find = function(value, start) {
        start = start || 0;
        for (var i = start; i < this.length; ++i) {
            if (this[i] == value) {
                return i;
            }
        }
        return -1;
    }

    Array.prototype.has = function(value) {
        return this.find(value) !== -1;
    }
}

// http://www.alistapart.com/articles/horizdropdowns/
function initMenu() {
    // cette fonction concerne uniquement IE
    if (document.all && document.getElementById) {
        var navRoot = document.getElementById("menu");
        var liChildren = navRoot.getElementsByTagName("li");
        for (var i = 0; i < liChildren.length; ++i) {
            node = liChildren[i];
            node.onmouseover = function() {
                this.className += " over";
            }
            node.onmouseout = function() {
                this.className = this.className.replace(" over", "");
            }
        }
    }
}


function setDefaultFocus() {
    var elems = getElementsByClassName("default-focus");
    if(elems.length > 0) {
        var elem = elems[0];
        if(!isUndefined(elem)) {
            elem.focus();
        }
    }
}


function filter(list, func) {
    var result = [];
    func = func || function(v) {
        return v;
    };
    map(list, function(v) {
        if (func(v)) {
            result.push(v);
        }
    });
    return result;
}


function getElement(elem) {
    if (document.getElementById) {
        if (typeof elem == "string") {
            elem = document.getElementById(elem);
            if (elem === null) {
                throw "L'élément n'existe pas: " + elem;
            }
        } else if (typeof elem != "object") {
            throw "Type de donnée invalide";
        }
    } else {
        throw "Impossible de retourner l'élément (erreur DOM): " + elem;
    }
    return elem;
}


function getElementsByClassName(className, tagName, parentNode) {
    parentNode = !isUndefined(parentNode) ? getElement(parentNode) : document;
    if (isUndefined(tagName)) {
        tagName = '*';
    }
    return filter(parentNode.getElementsByTagName(tagName),
        function(elem) { return hasClass(elem, className) });
}


function hasClass(elem, className) {
    return getElement(elem).className.split(' ').has(className);
}


function map(list, func) {
    var result = [];
    func = func || function(v) {return v};
    for (var i = 0; i < list.length; ++i) {
        result.push(func(list[i], i, list));
    }
    return result;
}


function isUndefined(v) {
    var undef;
    return v === undef;
}
