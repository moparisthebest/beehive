<!--Generated by WebLogic Workshop-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<netui:html>
    <head>
        <title>
            Return To Action
        </title>
    </head>
    <body>
        <h3>
            Return To Action
        </h3>
        
        <font color="Green">Number of times testAction called:
            <netui:span value="${pageFlow.testActionCount}"/></font>
        
        <br>
        <br>
        <netui:form action="testAction">
            <netui:textBox dataSource="actionForm.foo"/>
            <br>
            <netui:button>testAction</netui:button>
        </netui:form>
        <br>
        <br>
        <netui:anchor action="returnToPreviousAction">returnTo="previousAction"</netui:anchor>
        <br>
        <netui:anchor action="returnToAction">returnTo="action"</netui:anchor>
        <br>
        <netui:anchor action="returnToPreviousActionOverrideForm">returnTo="previousAction", override form</netui:anchor>
        <br>
        <netui:anchor action="returnToActionOverrideForm">returnTo="action", override form</netui:anchor>
        <br>
        <netui:anchor action="globalReturnToPreviousAction">Global.app returnTo="previousAction"</netui:anchor>
        <br>
        <netui:anchor action="globalReturnToAction">Global.app returnTo="action"</netui:anchor>
        <br>
        <br>
        <netui:anchor href="nested/nestedController.jpf">go to nested</netui:anchor>
        <br>
        <br>
        <netui:anchor action="done">
            Exit returnToAction
        </netui:anchor>
    </body>
</netui:html>
