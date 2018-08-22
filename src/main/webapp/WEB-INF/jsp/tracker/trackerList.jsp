<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CMS</title>
    <link id="easyuiTheme" rel="stylesheet" type="text/css"
          href="/static/css/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/css/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/static/css/default.css">
    <link rel="stylesheet" type="text/css" href="/static/css/ystep/ystep.css">
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/jquery.form.js"></script>
    <script type="text/javascript" src="/static/js/easyui/jquery.easyui.min.js"></script>

</head>
<body>
<div>
    <div class="easyui-panel" title="查询" data-options="iconCls:'icon-search',collapsible:true"
         style="width: 100%">
        <div class="body-dialog">
            <form id="tracker_query_form">
                <input name="sysid" id="sysid" type="hidden"/>
                <div class="row">
                    <div class="cols">
                        <lable>发起系统：</lable>
                        <input type="text" id="sourceSysid" class="easyui-combobox input" style="height:25px;"
                               data-options="
                                       required: true,
                                       url:'/system/getSystems',
                                       method:'post',
                                       valueField:'id',
                                       editable:false,
                                       textField:'sysname',
                                       panelHeight:'auto',
                                       onSelect: function(data){
                                       }
                        ">
                    </div>
                    <div class="cols">
                        <lable>目标系统：</lable>
                        <input type="text" id="targetSysid" class="easyui-combobox input" style="height:25px;"
                               data-options="
                                       required: true,
                                       url:'/system/getSystems?sysid='+$('sourceSysid').val(),
                                       method:'post',
                                       valueField:'id',
                                       editable:false,
                                       textField:'sysname',
                                       panelHeight:'auto',
                                       onSelect: function(data){
                                       }
                        ">
                    </div>

                </div>
                <div class="row">
                    <div class="cols">
                        <lable>起始时间：</lable>
                        <input type="text" id="startTime" class="easyui-datebox input"
                               data-options="required:true,editable:false" style="height:25px;"/>
                    </div>
                    <div class="cols">
                        <lable>终止时间：</lable>
                        <input type="text" id="endTime" class="easyui-datebox input"
                               data-options="required:true,editable:false" style="height:25px;"/>
                    </div>
                </div>

                <div class="row">
                    <div class="cols">
                        <lable>对比sku：</lable>
                        <input type="text" id="sku" class="easyui-textbox input" style="height:25px;"/>
                    </div>
                    <div class="cols">
                        <lable>展示全部：</lable>
                        <input type="checkbox" id="all"/>
                    </div>
                </div>
                <div class="row">
                    <div class="cols">
                        <a id="tracker_query_button" href="javascript:void(0)" class="easyui-linkbutton btn"
                           iconCls="icon-search">开始对比</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div style="margin-top:10px;">
        <table id="tracker_grid" style="width: 100%">
        </table>
    </div>
</div>
<div id="tracker_dialog" class="dialog"></div>
<script type="text/javascript" src="/static/js/easyui/easyui-lang-zh_CN.js"></script>
<script src="/static/js/ystep.js"></script>
<script src="/static/script/jquery.fn.extend.js"></script>
<script src="/static/script/trackerList.js"></script>
<script src="/static/script/Date.extend.js"></script>
</body>
</html>
