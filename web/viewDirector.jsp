<%-- 
    Document   : viewDirector
    Created on : Jul 11, 2020, 3:59:12 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Directors Page</title>
    </head>
    <body>
        <h1>View Directors Page</h1>
        <s:form action="SearchDirectorAction" method="POST">
            <s:textfield name="searchValue" label="Search by Full Name" />
            <s:submit value="Search"/>
        </s:form>
        <s:if test="%{listDirectors != null}">
            <s:if test="%{listDirectors.size()>0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Director ID</th>
                            <th>Username</th>
                            <th>Full name</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listDirectors">
                            <tr>
                                <td><s:property value="id"/></td>
                                <td><s:property value="username"/></td>
                                <td><s:property value="fullname"/></td>
                                <td><s:property value="phone"/></td>
                                <td><s:property value="email"/></td>
                                <td>
                                    <s:url action="EditDirectorAction" id="EditLink">
                                        <s:param name="id" value="id"/>
                                        <s:param name="username" value="username"/>
                                        <s:param name="fullname" value="fullname"/>
                                        <s:param name="phone" value="phone"/>
                                        <s:param name="email" value="email"/>
                                        <s:param name="lastSearchValue" value="%{searchValue}"/>
                                    </s:url>
                                    <s:a href="%{EditLink}">Update</s:a>
                                    </td>
                                    <td>
                                    <s:url action="DeleteDirectorAction" id="DeleteLink">
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
