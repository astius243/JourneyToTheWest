<%-- 
    Document   : index
    Created on : Jul 1, 2020, 10:10:49 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Login Page</h1>
        <s:form action="LoginAction" method="POST">
            <s:textfield name="username" label="Username"/>
            <s:password name="password" label="Password"/>
            <s:submit value="Login"/>
        </s:form>
    </body>
</html>
