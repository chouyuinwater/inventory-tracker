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
                field: 'sourceStep',
                width: '40%',
                title: "系统A",
                rowspan: 1,
                align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    return '<div class="sourceStep' + rowIndex + '"></div>';
                }
            }, {
                field: 'targetStep',
                width: '40%',
                title: "系统B",
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

                if(value.targetTracker) {
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
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            sku: $("#sku").val(),
            eventno: $("#sku").val(),
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
