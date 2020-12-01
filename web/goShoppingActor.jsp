<%-- 
    Document   : goShoppingActor
    Created on : Jul 14, 2020, 10:27:57 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Go Shopping Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Welcome, <s:property value="%{#session.FULLNAME}"/></h1>
        <h2>Go Shopping Actors Page</h2>
        <s:form action="AddActorCartAction" method="POST">
            <s:if test="%{#request.EXISTED!=null}">
                <font color="red">
                <s:property value="%{#request.EXISTED}"/>
                </font>
            </s:if>
            <s:if test="%{#request.EXISTEDINCART!=null}">
                <font color="red">
                <s:property value="%{#request.EXISTEDINCART}"/>
                </font>
            </s:if>
            <s:if test="%{#request.EXISTEDROLEINCART!=null}">
                <font color="red">
                <s:property value="%{#request.EXISTEDROLEINCART}"/>
                </font>
            </s:if>
            <s:if test="%{#request.EXISTEDROLE!=null}">
                <font color="red">
                <s:property value="%{#request.EXISTEDROLE}"/>
                </font>
            </s:if>
            <s:select name="sceneID" list="listScenes" label="Scene Name"/>
            <s:select name="actorID" list="listActors" label="Actor Name"/>
            <s:textfield name="role" value="%{role}" label="Role"/>
            <s:submit value="Add to Cart"/>
        </s:form>
        <s:a href="viewActorCart.jsp">View Actor Cart</s:a><br/>
        <s:a href="director.jsp">Back To Director Page</s:a>
    </body>
</html>
