<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="netui" uri="http://beehive.apache.org/netui/tags-html-1.0"%>

<netui:html documentType="html4-loose">
    <head>
        <title>
           Test Attribute Tag
        </title>
    </head>
    <body>
        <p>
        image Tag Errors
        </p>
        <netui:image src="./folder.gif" style="theStyle" styleClass="theClass"> 
            <%-- Test override --%>
            <netui:attribute  name="class" value="MyAttributeClass"/>
            <netui:attribute name="style" value="MyAttributeStyle"/>
            
            <%-- Test netui expression --%>
            <%
                pageContext.setAttribute("color", new String("red"));
            %>
            <netui:attribute name="customAttr" value="${pageScope.color}"/>

            <%-- Test a regular old attribute --%>
            <netui:attribute name="anotherCustomAttr" value="anotherCustomValue"/>

            <%-- Test disallowed attributes --%>
            <%-- these cause errors --%>         
            <netui:attribute name="id" value="whatever"/>
            <netui:attribute name="name" value="whatever"/>
            <netui:attribute name="src" value="whatever"/>
        </netui:image>
    </body>
</netui:html>
