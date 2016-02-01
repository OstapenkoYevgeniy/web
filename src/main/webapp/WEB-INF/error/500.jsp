<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
<style>
    html {
        height: 100%;
    }

    body {
        text-align: center;
        height: 100%;
        padding: 0;
        margin: 0;
    }

    body::before {
        content: "";
        height: 100%;
        display: inline-block;
        vertical-align: middle;
        width: 0;
    }

    .box {
        width: 400px;
        min-height: 300px;
        margin: 0 auto;
        display: inline-block;
        vertical-align: middle;
    }
</style>
<div class="box">
    <img src="/css/img/error/500.png" alt="500">
</div>
</body>
</html>
