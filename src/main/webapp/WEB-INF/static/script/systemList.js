(function () {

    $('#system_grid').datagrid({
        title: "系统列表",
        url: "getSystems",
        method: 'post',
        nowrap: true,
        striped: true,
        fitColumns: true,
        collapsible: false,
        checkOnSelect: true,
        singleSelect: true,
        rownumbers: true,
        loadMsg: "正在载入，请稍后...",
        columns: [
            [{
                field: 'id',
                width: '15%',
                title: "系统编号",
                align: 'center'
            }, {
                field: 'sysname',
                width: '15%',
                title: "系统名",
                align: 'center'
            }, {
                field: 'up',
                width: '15%',
                title: "上游系统",
                align: 'center'
            }, {
                field: 'down',
                width: '15%',
                title: "下游系统",
                align: 'center',
            }, {
                field: 'op',
                width: '23%',
                title: "操作",
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, rowIndex) {
                    var html = "<a class='format_link_button' style='color: #e68900' href='javascript:to_edit_system()'>编辑</a>";
                    return html;
                }
            }
            ]
        ],
        toolbar: [
            {
                text: '添加系统',
                iconCls: 'icon-add',
                handler: 'to_add_system'
            }
        ],
        onLoadSuccess: function (data) {
            $(".format_link_button").linkbutton({
                plain: true
            });
            $('#system_grid').datagrid('fixRowHeight');
        }
    });

    $("#system_query_button").on('click', function () {
        $('#system_grid').datagrid('load', {
            sysname: $("#sysname").val()
        });
    });
})
();

function to_add_system() {
    $('#system_dialog').openDialog({
        title: "添加系统",
        url: "toAddSystem",
        handler: "edit_system()",
        height: 210,
        width:360
    });
}

function to_edit_system() {
    $('#system_dialog').toEditObj({
        title: "修改系统",
        url: "toEditSystem",
        grid: "system_grid",
        handler: "edit_system()",
        height: 210,
        width:360
    });
}

function edit_system() {
    $('#system_form').editObj({
        url: "doEditSystem",
        dialog: "system_dialog",
        grid: "system_grid"
    });
}