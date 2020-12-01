<%-- 
    Document   : shopCart
    Created on : Jul 14, 2020, 9:25:41 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Go Shopping Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Welcome, <s:property value="%{#session.FULLNAME}"/></h1>
        <h2>Go Shopping Props Page</h2>
        <s:form action="AddPropCartAction" method="POST">
            <s:if test="%{#request.FULL!=null}">
                <font color="red">
                <s:property value="%{#request.FULL}"/>
                </font>
            </s:if>
            <s:select name="sceneID" list="listScenes" label="Scene Name"/>
            <s:select name="propID" list="listProps" label="Prop Name"/>
            <s:textfield name="dateFrom" value="%{dateFrom}" label="Date From" type="date"/>
            <s:textfield name="dateTo" value="%{dateTo}" label="Date To" type="date"/>
            <s:submit value="Add to Cart"/>
        </s:form>
        <s:a href="viewPropCart.jsp">View Prop Cart</s:a><br/>
        <s:a href="director.jsp">Back To Director Page</s:a>
    </body>
</html>
