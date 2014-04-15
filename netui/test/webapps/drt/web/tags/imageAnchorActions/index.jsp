<!--Generated by WebLogic Workshop-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<netui:html>
    <head>
        <title>
            Web Application Page
        </title>
    </head>
    <netui:body>
    <h4>Databinding to ImageAnchor</h4>
    <p style="color:green">
    This tests demonstrates the ability to bind to an ImageAnchor.  The <b>action</b> and <b>source</b> are databound
    (within a repeater).  When these links are pressed, we go back to this page and the last action should be set.
    On the bottom of the page is a link to a set of errors.
    </p>
    <hr>
    <h4>Image Repeater</h4>
    <netui-data:repeater dataSource="pageFlow.pageFlowActions">
            <netui-data:repeaterHeader><ul></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                <netui:imageAnchor action="${container.item.action}" src="${container.item.image}"/>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ul></netui-data:repeaterFooter>
    </netui-data:repeater>
    <b>Last Action:</b><netui:span value="${pageFlow.lastAction}"/><br>
    <hr>
    <netui:anchor action="goErrors">Errors</netui:anchor><br>
    </netui:body>
</netui:html>
