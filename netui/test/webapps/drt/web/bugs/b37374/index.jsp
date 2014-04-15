<!--Generated by WebLogic Workshop-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-databinding"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<netui:html>
    <head>
        <title>
            Web Application Page
        </title>
    </head>
    <netui:body>
        <netui:form action="submitIt">
        <h4>Null Items in Container Item</h4>
        <p style="color:green">
        This test binds to an array of items with a repeater.  Inside the the repeater we create a
        set of anchors that point to a results page.  The second item in the array is null.  In this case
        the href on the anchor and imageAnchor should have an href value of "".  There is a bit of
        JSTL inside the repeater so that the anchor will have a value of <b>[Null Item]</b> if the
        value of container.item is null.
        </p>
        <table>
        <netui-databinding:repeater dataSource="actionForm.detectedWebsites">
        <netui-databinding:repeaterItem> 
            <tr><td>Anchor [${container.index}]
                <netui:anchor href="${container.item.domainOwner}"> 
                    <netui:content value="${container.item.domainOwner}"/>
                    <c:if test="${container.item == null}">[Null Item]</c:if>
                </netui:anchor>
            </td> </tr>
        </netui-databinding:repeaterItem> 
        </netui-databinding:repeater> 
        </table>
        </netui:form>
        <hr />
        <netui:form action="submitIt">
        <table>
        <netui-databinding:repeater dataSource="actionForm.detectedWebsites"> 
        <netui-databinding:repeaterItem> 
            <tr><td>ImageAnchor [${container.index}]
                <netui:imageAnchor href="${container.item.domainOwner}" src="/coreWeb/resources/images/bar-background.gif"/> 
            </td> 
            </tr> 
        </netui-databinding:repeaterItem> 
        </netui-databinding:repeater> 
        </table>
        </netui:form>
    </netui:body>
</netui:html>
