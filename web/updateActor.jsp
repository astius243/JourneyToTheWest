<%-- 
    Document   : updateActor
    Created on : Jul 5, 2020, 10:04:09 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Actor Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Update Actor Page</h1>
        <s:form action="UpdateActorAction" method="POST">
            <s:textfield name="username" value="%{username}" label="Username" readonly="true"/>
            <s:password name="password" label="Password"/>
            <s:password name="confirm" label="Confirm Password"/>
            <s:textfield name="fullname" value="%{fullname}" label="Fullname"/>
            <s:textfield name="phone" value="%{phone}" label="Phone"/>
            <s:textfield name="email" value="%{email}" label="Email"/>
            <s:textfield name="description" value="%{description}" label="Description"/>
            <s:hidden name="id" value="%{id}"/>
            <s:hidden name="lastSearchValue" value="%{lastSearchValue}"/>
            <s:submit value="Update Actor"/>
        </s:form>
        <s:url action="SearchActorAction" id="SearchLink">
            <s:param name="searchValue" value="%{lastSearchValue}"/>
        </s:url>
        <s:a href="%{SearchLink}">Back To View Actors Page</s:a>
    </body>
</html>
