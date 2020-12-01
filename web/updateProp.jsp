<%-- 
    Document   : updateProp
    Created on : Jul 12, 2020, 7:19:25 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Prop Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Update Prop Page</h1>
        <s:form action="UpdatePropAction" method="POST">
            <s:textfield name="name" value="%{name}" label="Prop Name"/>
            <s:textfield name="quantities" value="%{quantities}" label="Quantities"/>
            <s:textfield name="description" value="%{description}" label="Description"/>
            <s:hidden name="id" value="%{id}"/>
            <s:hidden name="lastSearchValue" value="%{lastSearchValue}"/>
            <s:submit value="Update Prop"/>
        </s:form>
        <s:url action="SearchPropAction" id="SearchLink">
            <s:param name="searchValue" value="%{lastSearchValue}"/>
        </s:url>
        <s:a href="%{SearchLink}" >Back To View Props Page</s:a>
    </body>
</html>
