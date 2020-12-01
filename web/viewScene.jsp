<%-- 
    Document   : viewScene
    Created on : Jul 12, 2020, 12:20:44 AM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Scenes Page</title>
    </head>
    <body>
        <h1>View Scenes Page</h1>
        <s:form action="SearchSceneAction" method="POST">
            <s:textfield name="searchValue" label="Search by Scene Name"/>
            <s:submit value="Search"/>
        </s:form>
        <s:if test="%{listScenes != null}">
            <s:if test="%{listScenes.size()>0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Scene ID</th>
                            <th>Scene Name</th>
                            <th>Location</th>
                            <th>Date From</th>
                            <th>Date To</th>
                            <th>Total Cuts</th>
                            <th>Total Props</th>
                            <th>Description</th>
                            <th>Director ID</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listScenes">
                            <tr>
                                <td><s:property value="id"/></td>
                                <td><s:property value="name"/></td>
                                <td><s:property value="location"/></td>
                                <td><s:property value="dateFrom"/></td>
                                <td><s:property value="dateTo"/></td>
                                <td><s:property value="cuts"/></td>
                                <td><s:property value="props"/></td>
                                <td><s:property value="description"/></td>
                                <td><s:property value="directorID"/></td>
                                <td>
                                    <s:url action="EditSceneAction" id="EditLink">
                                        <s:param name="id" value="id"/>
                                        <s:param name="name" value="name"/>
                                        <s:param name="location" value="location"/>
                                        <s:param name="dateFrom" value="dateFrom"/>
                                        <s:param name="dateTo" value="dateTo"/>
                                        <s:param name="cuts" value="cuts"/>
                                        <s:param name="props" value="props"/>
                                        <s:param name="description" value="description"/>
                                        <s:param name="directorID" value="directorID"/>
                                        <s:param name="lastSearchValue" value="%{searchValue}"/>
                                    </s:url>
                                    <s:a href="%{EditLink}">Update</s:a>
                                    </td>
                                    <td>
                                    <s:url action="DeleteSceneAction" id="DeleteLink">
                                        <s:param name="id" value="id"/>
                                        <s:param name="lastSearchValue" value="%{searchValue}"/>
                                        <s:param name="directorID" value="directorID"/>
                                    </s:url>
                                    <s:a href="%{DeleteLink}">Delete</s:a>
                                    </td>
                                </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                No Record Found<br/>
            </s:else>
        </s:if>
        <s:a href="admin.jsp">Back to Admin Page</s:a>
</html>
