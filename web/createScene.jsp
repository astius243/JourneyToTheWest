<%-- 
    Document   : createFilm
    Created on : Jul 2, 2020, 11:08:50 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Scene Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Create Scene Page</h1>
        <s:form action="CreateSceneAction" method="POST">
            <s:if test="%{exception.message.indexOf('duplicate')>-1}">
                <font color = "red">
                <s:property value="%{id}"/> is existed!!!!
                </font>
            </s:if>
            <s:textfield name="id" value="%{id}" label="Scene ID"/>
            <s:textfield name="name" value="%{name}" label="Scene Name"/>
            <s:textfield name="location" value="%{location}" label="Location"/>
            <s:textfield name="dateFrom" value="%{dateFrom}" label="Date From" type="date"/>
            <s:textfield name="dateTo" value="%{dateTo}" label="Date To" type="date"/>
            <s:textfield name="cuts" value="%{cuts}" label="Total Cuts"/>
            <s:textfield name="description" value="%{description}" label="Description"/>
            <s:textfield name="directorID" value="%{directorID}" label="Director ID"/>
            <s:submit name="Create Scene"/>
        </s:form>
        <s:a href="admin.jsp" >Back To Admin Page</s:a>
    </body>
</html>
