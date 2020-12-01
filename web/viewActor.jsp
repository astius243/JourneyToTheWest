<%-- 
    Document   : viewActor
    Created on : Jul 5, 2020, 10:05:41 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Actors Page</title>
    </head>
    <body>
        <h1>View Actors Page</h1>
        <s:form action="SearchActorAction" method="POST">
            <s:textfield name="searchValue" label="Search by Full Name"/>
            <s:submit value="Search"/>
        </s:form>
        <s:if test="%{listActors != null}">
            <s:if test="%{listActors.size()>0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Actor ID</th>
                            <th>Username</th>
                            <th>Image</th>
                            <th>Full name</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Description</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator var="dto" value="listActors">
                            <tr>
                                <td><s:property value="id"/></td>
                                <td><s:property value="username"/></td>
                                <td><img src="${dto.image}" width="100" height="100"/></td>
                                <td><s:property value="fullname"/></td>
                                <td><s:property value="phone"/></td>
                                <td><s:property value="email"/></td>
                                <td><s:property value="description"/></td>
                                <td>
                                    <s:url action="EditActorAction" id="EditLink">
                                        <s:param name="id" value="id"/>
                                        <s:param name="username" value="username"/>
                                        <s:param name="fullname" value="fullname"/>
                                        <s:param name="phone" value="phone"/>
                                        <s:param name="email" value="email"/>
                                        <s:param name="image" value="image"/>
                                        <s:param name="description" value="description"/>
                                        <s:param name="lastSearchValue" value="%{searchValue}"/>
                                    </s:url>
                                    <s:a href="%{EditLink}">Update</s:a>
                                    </td>
                                    <td>
                                    <s:url action="DeleteActorAction" id="DeleteLink">
                                        <s:param name="username" value="username"/>
                                        <s:param name="id" value="id"/>
                                        <s:param name="lastSearchValue" value="%{searchValue}"/>
                                    </s:url>
                                    <s:a href="%{DeleteLink}">Delete</s:a>
                                    </td>
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
    </body>
</html>
