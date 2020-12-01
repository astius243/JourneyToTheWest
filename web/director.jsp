<%-- 
    Document   : director
    Created on : Jul 11, 2020, 1:41:14 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Director Page</title>
    </head>
    <body>
        <h1>Welcome, <s:property value="%{#session.FULLNAME}"/></h1>
        <s:if test="%{#session.UPDATE.get(username) != null}">
            <s:iterator value="%{#session.UPDATE.get(username)}">
                <font color="red">
                <s:property value="message"/><br/>
                </font>
            </s:iterator>
        </s:if>
        <s:form action="GoShoppingPropAction" method="POST">
            <s:submit value="Go Shopping Prop"/>  
        </s:form>
        <s:form action="GoShoppingActorAction" method="POST">
            <s:submit value="Go Shopping Actor"/>  
        </s:form>
        <s:form action="ReportPropSceneAction" method="POST">
            <s:submit value="View Report Prop Cart"/>
        </s:form>
        <s:a href="LogoutAction">Log Out</s:a>
    </body>
</html>
