/*
 * $Id: scripts.js,v 1.2 2005/01/19 01:08:35 romale Exp $
 */

function init() {
    initMenu();
    setDefaultFocus();
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
// TODO corriger les erreurs de syntaxe
/*
    var forms = document.getElementsByTagName("form");
    for(var i = 0; i < forms.length; ++i) {
        var form = forms[i];
        var children = form.childNodes;
        for(var j = 0; j < children.length; ++j) {
            var elem = children[j];
            var elemClass = elem.className;
            if(elemClass && elemClass.indexOf("default-focus") != -1) {
                elem.focus();
            }
        }
    }
*/
}
