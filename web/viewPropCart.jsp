<%-- 
    Document   : viewPropCart
    Created on : Jul 14, 2020, 11:04:45 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Prop Cart Page</title>
    </head>
    <body>
        <h1>View Prop Cart Page</h1>
        <s:if test="%{#session.PROPCART == null}">
            No Item<br/>
        </s:if>
        <s:else>
            <s:if test="%{#session.PROPCART.cart.size()>0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Scene Name</th>
                            <th>Prop Name</th>
                            <th>Quantities</th>
                            <th>Date From</th>
                            <th>Date To</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{#session.PROPCART.cart}" status="counter">
                            <tr>
                                <td><s:property value="%{#counter.count}"/></td>
                                <td><s:property value="value.scene.name"/></td>
                                <td><s:property value="value.prop.name"/></td>
                                <td><s:property value="value.quantities"/></td>
                                <td><s:property value="value.dateFrom"/></td>
                                <td><s:property value="value.dateTo"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                No Item
            </s:else>
        </s:else>
        <s:form action="InsertPropCartAction" method="POST">
            <s:submit value="Insert To Database"/>
        </s:form>
        <s:a href="GoShoppingPropAction">Back To Shopping Prop</s:a>
    </body>
</html>
