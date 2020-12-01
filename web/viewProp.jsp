<%-- 
    Document   : viewProp
    Created on : Jul 12, 2020, 4:52:08 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Properties Page</title>
    </head>
    <body>
        <h1>View Properties Page</h1>
        <s:form action="SearchPropAction" method="POST">
            <s:textfield name="searchValue" label="Search by Prop Name"/>
            <s:submit value="Search"/>
        </s:form>
        <s:if test="%{listProps != null}">
            <s:if test="%{listProps.size()>0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Prop ID</th>
                            <th>Prop Name</th>
                            <th>Image</th>
                            <th>Quantities</th>
                            <th>Available</th>
                            <th>Description</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator var="prop" value="listProps">
                            <tr>
                                <td><s:property value="id"/></td>
                                <td><s:property value="name"/></td>
                                <td><img src="${prop.image}" width="100" height="100"></td>
                                <td><s:property value="quantities"/></td>
                                <td><s:property value="available"/></td>
                                <td><s:property value="description"/></td>
                                <td>
                                    <s:url action="EditPropAction" id="EditLink">
                                        <s:param name="id" value="id"/>
                                        <s:param name="name" value="name"/>
                                        <s:param name="image" value="image"/>
                                        <s:param name="quantities" value="quantities"/>
                                        <s:param name="available" value="available"/>
                                        <s:param name="description" value="description"/>
                                        <s:param name="lastSearchValue" value="%{searchValue}"/>
                                    </s:url>
                                    <s:a href="%{EditLink}">Update</s:a>
                                    </td>
                                    <td>
                                    <s:url action="DeletePropAction" id="DeleteLink">
                                        <s:param name="id" value="id"/>
                                        <s:param name="lastSearchValue" value="%{searchValue}"/>
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
    </body>
</html>
