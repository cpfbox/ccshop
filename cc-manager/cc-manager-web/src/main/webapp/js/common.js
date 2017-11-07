
var ccshop = {

    registerMenuEvent:function(){
        var $tree = $("#menu .easyui-tree");
        //console.log($tree);
        $tree.tree({
            onClick:function (node) {
                //console.log(node.attributes.href);
                var href = node.attributes.href;
                var text = node.text;
                $("#tab").tabs("add",{
                    title:text,
                    href:href,
                    closable:true
                });
            }
        })
    }

};



