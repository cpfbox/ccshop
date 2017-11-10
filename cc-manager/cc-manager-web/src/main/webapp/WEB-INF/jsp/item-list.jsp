<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<table id="tb"></table>
<script>

    var toolbar = [{
        iconCls:"icon-add",
        text:"新增",
        handler:function () {
            console.log("add");
        }
    },{
        iconCls:"icon-remove",
        text:"删除",
        handler:function () {
            var selections = $("#tb").datagrid("getSelections");
            if(selections.length==0){
                $.messager.alert("提示","请至少选中一条记录!","warn");
                return;
            }
            $.messager.confirm("确认","你确定要删除吗?",function (r) {
                if(r){
                    var ids = [];
                    for(var i=0;i<selections.length;i++){
                        ids.push(selections[i].id);
                    }
                    $.post(
                        "items/batch",
                        {"ids[]":ids,status:3},
                        function () {
                            $("#tb").datagrid("reload");
                        },
                        "json"
                    )
                }
            })
        }
    },{
        iconCls:"icon-edit",
        text:"编辑",
        handler:function () {
            console.log("edit");
        }
    },{
        iconCls:"icon-down",
        text:"下架",
        handler:function () {
            var selections = $("#tb").datagrid("getSelections");
            if(selections.length==0){
                $.messager.alert("提示","请至少选择一条记录!","warn");
                return;
            }
            $.messager.confirm("确认","你确定要下架吗?",function (r) {
                if(r){
                    var ids = [];
                    for(var i=0;i<selections.length;i++){
                        ids.push(selections[i].id);
                    }
                    $.post(
                        "items/batch",
                        {"ids[]":ids,status:2},
                        function () {
                            $("#tb").datagrid("reload");
                        },
                        "json"
                    )
                }
            })
        }
    },{
        iconCls:"icon-up",
        text:"上架",
        handler:function () {
            var selections = $("#tb").datagrid("getSelections");
            if(selections.length==0){
                $.messager.alert("提示","请至少选择一条记录!","warn");
                return;
            }
            $.messager.confirm("确认","你确定要上架吗?",function (r) {
                if(r){
                    var ids = [];
                    for(var i=0;i<selections.length;i++){
                        ids.push(selections[i].id);
                    }
                    $.post(
                        "items/batch",
                        {"ids[]":ids,status:1},
                        function () {
                            $("#tb").datagrid("reload");
                        },
                        "json"
                    )
                }
            })
        }
    }]

    $("#tb").datagrid({
        multiSort:true,
        toolbar:toolbar,
        url:'items',
        striped:true,
        pagination:true,
        rownumbers:true,
        fit:true,
        pageSize:20,
        pageList:[20,50,100],
        columns:[[
            {field:'ck',checkbox:true},
            {field:'id',title:'商品编号',width:100},
            {field:'title',title:'商品名称',width:100,sortable:true},
            {field:'sellPoint',title:'卖点',width:100,sortable:true},
            {field:'status',title:'状态',width:100,formatter:function (value,row,index) {
//                console.group();
//                console.log(value);
//                console.log(row);
//                console.log(index);
//                console.groupEnd();
                switch (value){
                    case 1:
                        return "正常";
                        break;
                    case 2:
                        return "下架";
                        break;
                    case 3:
                        return "删除";
                        break;
                    default:
                        return "未知";
                        break;
                }
            }},
            {field:'catName',title:'分类名称',width:100},
            {field:'price',title:'价格',width:100},
            {field:'created',title:'创建时间',width:100,formatter:function (value,row,index) {
                return moment(value).format("ll");
            }},
            {field:'updated',title:'修改时间',width:100,formatter:function (value,row,index) {
                return moment(value).format("ll");
            }}

        ]]
    });

</script>
