(function () {
    $('#tracker_grid').datagrid({
        title: "对比列表",
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
                width: '10%',
                title: "事件",
                align: 'center'
            }, {
                field: 'op',
                width: '5%',
                title: "操作",
                align: 'center',
                sortable: false,
                formatter: function (value, rowData, rowIndex) {
                    var html = "<a class='format_link_button' style='color: #e68900' href='javascript:alert(1)'>明细</a>";
                    return html;
                }
            }
        ]],
        columns: [
            [
                {
                    title: "原系统",
                    colspan: 2
                },
                {
                    title: "目标系统",
                    colspan: 2
                }
            ],
            [{
                field: 'sourceTemplate',
                width: '10%',
                title: "模板",
                rowspan: 1,
                align: 'center',
            }, {
                field: 'sourceStep',
                width: '30%',
                title: "状态",
                rowspan: 1,
                align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    return '<div class="sourceStep' + rowIndex + '"></div>';
                }
            }, {
                field: 'targetTemplate',
                width: '10%',
                title: "模板",
                rowspan: 1,
                align: 'center',
            }, {
                field: 'targetStep',
                width: '30%',
                title: "状态",
                rowspan: 1,
                align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    return '<div class="targetStep' + rowIndex + '"></div>';
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
                var sourceSteps = [];
                value.sourceTracker.innerTemplates.forEach(function (innerValue, innerIndex, innerArray) {
                    var obj = {};
                    obj["title"] = innerValue.desc;
                    obj["content"] = innerValue.status;
                    sourceSteps.push(obj);
                });

                $(".sourceStep" + index).loadStep({
                    size: "small",
                    color: "green",
                    steps: sourceSteps
                });
                $(".sourceStep" + index).setStep(value.sourceTracker.currentStep);

                if (value.targetTracker) {
                    var targetSteps = [];
                    value.targetTracker.innerTemplates.forEach(function (innerValue, innerIndex, innerArray) {
                        var obj = {};
                        obj["title"] = innerValue.desc;
                        obj["content"] = innerValue.status;
                        targetSteps.push(obj);
                    });

                    $(".targetStep" + index).loadStep({
                        size: "small",
                        color: "green",
                        steps: targetSteps
                    });
                    $(".targetStep" + index).setStep(value.targetTracker.currentStep);
                }
            });

            $('#tracker_grid').datagrid('fixRowHeight');

        }
    });

    $("#tracker_query_button").on('click', function () {
        $('#tracker_grid').datagrid('load', {
            sourceSysid: $("#sourceSysidHidden").val(),
            targetSysid: $("#targetSysidHidden").val(),
            startTime: $("#startTimeHidden").val(),
            endTime: $("#endTimeHidden").val(),
            sku: $("#sku").val(),
            eventno: $("#eventno").val(),
            diff: $("#diff").is(":checked")
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
