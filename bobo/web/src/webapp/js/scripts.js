/*
 * $Id: scripts.js,v 1.1 2005/01/18 09:26:18 romale Exp $
 */

function init() {
    initMenu();
}

// http://www.alistapart.com/articles/horizdropdowns/
function initMenu() {
    // cette fonction concerne uniquement IE
    if (document.all && document.getElementById) {
        navRoot = document.getElementById("menu");
        liChildren = navRoot.getElementsByTagName("li");
        for (i = 0; i < liChildren.length; i++) {
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
