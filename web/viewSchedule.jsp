<%-- 
    Document   : viewFilmedScene
    Created on : Jul 20, 2020, 5:44:16 PM
    Author     : Hau Huong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Schedule Page</title>
    </head>
    <body>
        <h1>View Schedule Page</h1>
        <s:if test="%{listSchedule != null}">
            <s:if test="%{listSchedule.size()>0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Scene Name</th>
                            <th>Role</th>
                            <th>End Date</th>
                            <th>Script</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listSchedule">
                            <tr>
                                <td><s:property value="sceneName"/></td>
                                <td><s:property value="role"/></td>
                                <td><s:property value="date"/></td>
                                <td>
                                    <s:url action="DownloadFileAction" id="DownloadLink">
                                        <s:param name="script" value="script"/>
                                    </s:url>
                                    <s:a href="%{DownloadLink}">Download Script</s:a>
                                    </td>
                                </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                No Record<br/>
            </s:else>
        </s:if>
        <s:a href="actor.jsp">Back to Actor Page</s:a>
    </body>
</html>
