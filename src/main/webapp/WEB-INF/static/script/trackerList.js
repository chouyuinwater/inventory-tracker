(function () {
    $('#tracker_grid').datagrid({
        title: "模板列表",
        url: "getTrackers",
        method: 'post',
        nowrap: true,
        fitColumns: false,
        pageSize: 15,
        collapsible: false,
        checkOnSelect: true,
        singleSelect: true,
        pagination: true,
        rownumbers: true,
        loadMsg: "正在载入，请稍后...",
        pageList: [15, 30, 50],
        frozenColumns: [[
            {
                field: 'eventno',
                width: '15%',
                title: "事件",
                align: 'center'
            },
        ]],
        columns: [
            [
                {
                    title: "sku",
                    colspan: 2
                }
            ],
            [{
                field: 'currentStep',
                width: '40%',
                title: "系统A",
                rowspan: 1,
                align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    return '<div class="ystep' + rowIndex + '"></div>';
                }
            }, {
                field: 'currentStep',
                width: '40%',
                title: "系统B",
                rowspan: 1,
                align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    return '<div class="ystep' + rowIndex + '"></div>';
                }
            }
            ]
        ],
        onLoadSuccess: function (data) {
            $(".format_link_button").linkbutton({
                plain: true
            });

            var data = $("#tracker_grid").datagrid('getData');
            data.rows.forEach(function (value, index, array) {
                /*var steps = [];
                var obj = {};
                value.keyValuePairs.forEach(function (innerValue, innerIndex, innerArray) {
                    obj[innerValue.key] = innerValue.value;
                    steps.push(obj);
                });*/

                $(".ystep" + index).loadStep({
                    size: "large",
                    color: "green",
                    steps: [{
                        title: "定位",
                        content: "20"
                    }, {
                        title: "下架",
                        content: "30"
                    }, {
                        title: "复合",
                        content: "40"
                    }, {
                        title: "出库",
                        content: "50"
                    }, {
                        title: "回传",
                        content: "60"
                    }]
                });
                $(".ystep" + index).setStep(index % 5);
            });

            $('#tracker_grid').datagrid('fixRowHeight');

        }
    });

    $("#tracker_query_button").on('click', function () {
        $('#tracker_grid').datagrid('load', {
            sysid: $("#sysid").val(),
            sku: $("#sku").val()
        });

    });

    $("#tracker_detail_button").on('click', function () {
        var horizontalPadding = 30;
        var verticalPadding = 30;
        var width = 650, height = 800;
        // $('#dlg').dialog({
        //     width: width,
        //     height: height,
        //     modal: true,
        //     resizable: true,
        //     autoResize: true,})
        //     .width(width - horizontalPadding)
        //     .height(height - verticalPadding);  ;
        $('#dlg').dialog('open');
    });
})();
