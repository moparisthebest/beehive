<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui" %>

<html>
    <head>
        <title>Merge Test4</title>
    </head>
    <body>
        <h3 align="center">Merge Test4 - Jsp1.jsp</h3>
        <hr width="95%"/>
        <br/>
        <center>
            <netui:form action="jpfAction1">
                <table>
                    <tr>
                        <td>Field1</td>
                        <td>
                            <netui:textBox dataSource="actionForm.field1"/>
                        </td>
                    </tr>
                </table>
            </netui:form>
            <netui:anchor action="jpfAction1">continue</netui:anchor>
        </center>
    </body>
</html>
