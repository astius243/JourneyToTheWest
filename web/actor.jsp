<%-- 
    Document   : user
    Created on : Jul 1, 2020, 10:26:59 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actor Page</title>
    </head>
    <body>
        <h1>Welcome, <s:property value="%{#session.FULLNAME}"/> </h1>
        <s:if test="%{#session.UPDATE.get(username) != null}">
            <s:iterator value="%{#session.UPDATE.get(username)}">
                <font color="red">
                <s:property value="message"/><br/>
                </font>
            </s:iterator>
        </s:if>
        <s:form action="ViewScheduleAction" method="POST">
            <s:submit value="View Schedule"/>
        </s:form>
        <s:form action="ViewFilmedSceneAction" method="POST">
            <s:submit value="View Filmed Scene"/>
        </s:form>
        <s:a href="LogoutAction">Log Out</s:a>
    </body>
</html>
