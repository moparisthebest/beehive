<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0"        prefix="netui" %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data" %>

<html>
    <head>
        <title>ReturnTo Test44</title>
    </head>
    <body>
        <h3 align="center">ReturnTo Test44 - Jsp1.jsp</h3>
        <hr width="95%"/>
        <br/>
        <center>
            <br/>
            <h2><font color="blue">
             Test return-to="currentPage" from within a nested pageflow and just
             after returning from a nested pageFlow.
            </font></h2>
            <br/>
            <netui:anchor action="action1">Continue...</netui:anchor>
        </center>
    </body>
</html>
