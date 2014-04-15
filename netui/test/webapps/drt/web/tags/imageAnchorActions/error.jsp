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
    <h4>Databinding Error in ImageAnchor</h4>
    <p style="color:green">
    The following two links contain errors.  These errors are for databinding to an invalid action and a
    null object.  These should both report errors at the bottom of the page.  These errors will differ.
    </p>
    <hr>
        Invalid Action:<netui:imageAnchor action="${pageFlow.invalidAction}"
                src="/coreWeb/resources/images/back.gif" /><br>
        Null Action:<netui:imageAnchor action="${pageFlow.nullAction}"
                src="/coreWeb/resources/images/back.gif"/><br>
        <hr>
        <netui:anchor action="${pageFlow.sortedActions[2]}">Home</netui:anchor><br>
        </p>
    </netui:body>
</netui:html>
