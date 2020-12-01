<%-- 
    Document   : admin
    Created on : Jul 1, 2020, 10:26:53 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Welcome, <s:property value="%{#session.FULLNAME}"/></h1>
        <s:a href="viewActor.jsp">View Actor</s:a><br/>
        <s:a href="viewDirector.jsp">View Director</s:a><br/>
        <s:a href="viewProp.jsp">View Prop</s:a><br/>
        <s:a href="viewScene.jsp">View Scene</s:a><br/>
        <s:a href="createActor.jsp">Create Actor</s:a><br/>
        <s:a href="createDirector.jsp">Create Director</s:a><br/>
        <s:a href="createScene.jsp">Create Scene</s:a><br/>
        <s:a href="createProp.jsp">Create Prop</s:a><br/>
        <s:a href="LogoutAction">Log Out</s:a>
    </body>
</html>
