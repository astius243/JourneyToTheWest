<%-- 
    Document   : createActor
    Created on : Jul 2, 2020, 9:16:23 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Actor Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Create Actor Page</h1>
        <s:form action="CreateActorAction" method="POST">
            <s:if test="%{exception.message.indexOf('duplicate')>-1}">
                <font color = "red">
                <s:if test="%{exception.message.contains('tblLogin')}">                
                    <s:property value="%{username}"/> is existed!!!<br/>
                </s:if>
                <s:if test="%{exception.message.contains('tblActors')}">                
                    <s:property value="%{id}"/> is existed!!!
                    <s:action name="DeleteLoginAction">
                        <s:param name="username" value="%{username}"/>
                        <s:param name="link" value="createActor.jsp"/>
                    </s:action>
                </s:if>
                </font>
            </s:if>
            <s:textfield name="id" label="Actor ID"/>
            <s:textfield name="username" value="%{username}" label="Username"/>
            <s:password name="password" label="Password"/>
            <s:password name="confirm" label="Confirm Password"/>
            <s:textfield name="fullname" value="%{fullname}" label="Fullname"/>
            <s:textfield name="phone" value="%{phone}" label="Phone"/>
            <s:textfield name="email" value="%{email}" label="Email"/>
            <s:textfield name="description" value="%{description}" label="Description"/>
            <s:submit value="Create Actor"/>
        </s:form>
        <s:a href="admin.jsp">Back to Admin Page</s:a>
    </body>
</html>
