<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>

<html>
<head>
    <title>Error de la Aplicación</title>
    <style type="text/css">
        body {
            background-color: #F4F4F4;
            padding: 0;
            margin: 0;
            overflow: auto;
            scrollbar-base-color: #F0F7F4;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 8pt;
            color: #01603E;
        }
        h1 {
            margin: 5 5 5 5;
            padding: 4 4;
            font-size: 8pt;
            color: #FFFFFF;
            background-color: #01603E;
            text-align: center;
        }
        table {
            font-size: 8pt;
        }
    </style> 
</head>
<body>
    <h1>ERROR</h1>    
    <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
        <tr><td align="center" height="20px">Ha ocurrido un error en la aplicacion, consulte con el Administrador</td></tr>
        <tr><td><%= exception %></td></tr>
        <%--<tr><td><pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre></td></tr>--%>
    </table>
</body>
</html>
