<!--Generated by WebLogic Workshop-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<netui:html>
    <head>
        <title>
            Exceptions in Lifecycle Methods
        </title>
    </head>
    <body>
        <h3>Exceptions in Lifecycle Methods</h3>
        
        <netui:anchor action="throwInBeforeAction">throw in beforeAction()</netui:anchor><br>
        <netui:anchor action="throwInAction">throw in an action</netui:anchor><br>
        <netui:anchor action="throwInAfterAction">throw in afterAction()</netui:anchor><br>
        <netui:anchor action="throwEverywhere">throw in the action and in afterAction()</netui:anchor><br>
        <netui:anchor action="noThrow">don't throw anything</netui:anchor>
        <br>
        <br>
        <hr>
        History:
        <ul>
        <netui-data:repeater dataSource="pageFlow.history">
            <netui-data:repeaterItem>
                <li><netui:span value="${container.item.message}"/>
                        threw a <netui:span value="${container.item.exceptionName}"/>
                        (<netui:span value="${container.item.actionName}"/>)</li>
            </netui-data:repeaterItem>
        </netui-data:repeater>
        </ul>
    </body>
</netui:html>
