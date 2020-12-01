<%-- 
    Document   : createProp
    Created on : Jul 12, 2020, 9:36:01 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Prop Page</title>
        <s:head/>
    </head>
    <body>
        <h1>Create Prop Page</h1>
        <s:form action="CreatePropAction" method="POST">
            <s:if test="%{exception.message.indexOf('duplicate')>-1}">
                <font color="red">
                <s:property value="%{id}"/> is existed!!!
                </font>
            </s:if>
            <s:textfield name="id" value="%{id}" label="Prop ID"/>
            <s:textfield name="name" value="%{name}" label="Prop Name"/>
            <s:textfield name="quantities" value="%{quantities}" label="Quantities"/>
            <s:textfield name="description" value="%{description}" label="Description"/>
            <s:submit value="Create Prop"/>
        </s:form>
        <s:a href="admin.jsp" >Back To Admin Page</s:a>
    </body>
</html>
