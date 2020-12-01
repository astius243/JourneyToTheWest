<%-- 
    Document   : updateDirector
    Created on : Jul 11, 2020, 9:11:29 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Director Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Update Director Page</h1>
        <s:form action="UpdateDirectorAction" method="POST">
            <s:textfield name="username" value="%{username}" label="Username" readonly="true"/>
            <s:password name="password" value="" label="Password"/>
            <s:password name="confirm" value="" label="Confirm Password"/>
            <s:textfield name="fullname" value="%{fullname}" label="Fullname"/>
            <s:textfield name="phone" value="%{phone}" label="Phone"/>
            <s:textfield name="email" value="%{email}" label="Email"/>
            <s:hidden name="id" value="%{id}"/>
            <s:hidden name="lastSearchValue" value="%{lastSearchValue}"/>
            <s:submit value="Update Director"/>
        </s:form>
        <s:url action="SearchDirectorAction" id="SearchLink">
            <s:param name="searchValue" value="%{lastSearchValue}"/>
        </s:url>
        <s:a href="%{SearchLink}" >Back To View Directors Page</s:a>
    </body>
</html>
