<!--Generated by WebLogic Workshop-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<netui:html>
    <head>
        <title>
            Readonly Actions
        </title>
    </head>
    <body>
        <h3>Readonly Actions</h3>
        
        <netui:anchor action="readonlyAction">readonly action</netui:anchor>
        <br>
        <netui:anchor action="nonReadonlyAction">non-readonly action</netui:anchor>
        <br>
        <netui:anchor action="readonlyExceptionHandler">readonly exception-handler</netui:anchor>
        <br>
        <netui:anchor action="nonReadonlyExceptionHandler">non-readonly exception-handler</netui:anchor>
        <br>
        <netui:anchor action="goNested">see a fully-readonly page flow</netui:anchor>
        <br>
        <hr>
        History:
        <ul>
        <netui-data:repeater dataSource="pageFlow.history">
            <netui-data:repeaterItem>
                <li><netui:span value="${container.item}"/></li>
            </netui-data:repeaterItem>
        </netui-data:repeater>
        </ul>
    </body>
</netui:html>
