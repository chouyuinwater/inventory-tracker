<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<div class="body-dialog">
    <form id="system_form" method="post">
        <input name="id" id="systemid" type="hidden" value="${system.id}">
        <div class="row">
            <label class="label">系统名:</label>
            <input name="sysname" class="easyui-textbox" style="height:25px;" data-options="required:true"
                   value="${system.sysname}">
        </div>
        <div class="row">
            <label class="label">关联系统:</label>
            <input name="relation" class="easyui-textbox" style="height:25px;" data-options="required:true"
                   value="${system.relation}">
        </div>
    </form>
</div>