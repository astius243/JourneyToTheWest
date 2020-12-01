<%-- 
    Document   : viewReport
    Created on : Jul 15, 2020, 9:40:08 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Report Page</title>
    </head>
    <body>
        <h1>View Report Prop Page</h1>
        <s:if test="%{listProps != null}">
            <s:if test="%{listProps.size()>0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Scene ID</th>
                            <th>Scene Name</th>
                            <th>Prop ID</th>
                            <th>Prop Name</th>
                            <th>Quantities</th>
                            <th>Date From</th>
                            <th>Date To</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listProps">
                            <tr>
                                <td><s:property value="sceneID"/></td>
                                <td><s:property value="sceneName"/></td>
                                <td><s:property value="propID"/></td>
                                <td><s:property value="propName"/></td>
                                <td><s:property value="quantities"/></td>
                                <td><s:property value="dateFrom"/></td>
                                <td><s:property value="dateTo"/></td>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
        </s:if>
        <s:a href="director.jsp">Back to Director Page</s:a>
    </body>
</html>
