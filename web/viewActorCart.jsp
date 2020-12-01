<%-- 
    Document   : viewActorCart
    Created on : Jul 15, 2020, 12:22:54 AM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Actor Cart Page</title>
    </head>
    <body>
        <h1>View Actor Cart Page</h1>
        <s:if test="%{#session.ACTORCART == null}">
            No Item<br/>
        </s:if>
        <s:else>
            <s:if test="%{#session.ACTORCART.cart.size>0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Scene Name</th>
                            <th>Actor Name</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{#session.ACTORCART.cart}" status="counter">
                            <tr>
                                <td><s:property value="%{#counter.count}"/></td>
                                <td><s:property value="value.scene.name"/></td>
                                <td><s:property value="value.actor.fullname"/></td>
                                <td><s:property value="value.role"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                No Item
            </s:else>
        </s:else>
        <s:form action="InsertActorCartAction" method="POST">
            <s:submit value="Insert To Database"/>
        </s:form>
        <s:a href="GoShoppingActorAction">Back To Shopping Prop</s:a>
    </body>
</html>
