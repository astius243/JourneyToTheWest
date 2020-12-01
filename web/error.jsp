<%-- 
    Document   : error
    Created on : Jul 1, 2020, 10:26:33 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>ERROR Page</h1>
        <h2>
            <font color="red"><s:property value="%{#request.ERROR}"/></font>
        </h2>
        <s:a href="%{#request.LINK}">
            <s:property value="%{#request.LINKLABEL}"/>
        </s:a>
    </body>
</html>
