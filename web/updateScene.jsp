<%-- 
    Document   : updateScene
    Created on : Jul 12, 2020, 12:55:33 AM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Scene Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Update Scene Page</h1>
        <s:form action="UpdateSceneAction" method="POST">
            <s:textfield name="name" value="%{name}" label="Scene Name"/>
            <s:textfield name="location" value="%{location}" label="Location"/>
            <s:textfield name="dateFrom" value="%{dateFrom}" label="Date From" type="date"/>
            <s:textfield name="dateTo" value="%{dateTo}" label="Date To" type="date"/>  
            <s:textfield name="cuts" value="%{cuts}" label="Total Cuts"/>
            <s:textfield name="description" value="%{description}" label="Description"/>
            <s:textfield name="directorID" value="%{directorID}" label="Director ID"/>
            <s:hidden name="previousID" value="%{previousID}"/>
            <s:hidden name="id" value="%{id}"/>
            <s:hidden name="props" value="%{props}"/>
            <s:hidden name="lastSearchValue" value="%{lastSearchValue}"/>
            <s:submit name="Update Scene"/>
        </s:form>
        <s:url action="SearchSceneAction" id="SearchLink">
            <s:param name="searchValue" value="%{lastSearchValue}"/>
        </s:url>
        <s:a href="%{SearchLink}" >Back To View Scenes Page</s:a>
    </body>
</html>
