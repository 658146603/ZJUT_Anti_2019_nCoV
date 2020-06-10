<%--
  Created by IntelliJ IDEA.
  User: HHR
  Date: 2020/5/21
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="person" class="model.Person" scope="session"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <meta name="theme-color" content="#3f51b5">
    <title>浙江工业大学</title>
    <link rel="stylesheet" type="text/css" href="../../css/mdui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <script src="../../js/md5.min.js"></script>
    <script src="../../js/mdui.min.js"></script>
</head>
<body class="mdui-appbar-with-toolbar  mdui-loaded mdui-theme-primary-indigo   mdui-theme-accent-deep-purple">
<header class="mdui-appbar mdui-appbar-fixed" id="header">
    <div class="mdui-toolbar mdui-color-theme">
       <span class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white"
             onclick="window.location.assign('../admin/router')">
            <i class="mdui-icon material-icons">home</i>
        </span>
        <a href="" class="mdui-typo-headline mdui-hidden-xs"
           style="font-weight: inherit">浙江工业大学</a>
        <a href="" class="mdui-typo-title " style="font-weight: inherit">学生健康信息管理系统</a>
        <div class="mdui-toolbar-spacer"></div>
    </div>
</header>
<script type="text/javascript">
    window.onload = function () {
        mdui.dialog({
            title: '该健康码有误',
            buttons: [
                {
                    text: '确认',
                    onClick: function () {
                        window.location.assign("../admin/router")
                    }
                }
            ],
            history: false,
        });
    }
</script>
</body>
</html>

