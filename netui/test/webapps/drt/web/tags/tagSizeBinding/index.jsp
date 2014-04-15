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
    <body>
    <netui:anchor action="goImageErrors">Image Errors</netui:anchor>
    <hr />
    <h4>Images</h4>
    <netui:image width="${pageFlow.imageWidth}"
        src="/coreWeb/index/Beehive.gif"
        alt="${pageFlow.imageAlt}"
        height="${pageFlow.imageHeight}"
        hspace="${pageFlow.imageHSpace}"
        vspace="${pageFlow.imageVSpace}" />
    <br>
    <netui:imageAnchor
        action="begin"
        border="${pageFlow.imageBorder}"
        width="${pageFlow.imageWidth}"
        src="/coreWeb/index/Beehive.gif"
        alt="${pageFlow.imageAlt}"
        height="${pageFlow.imageHeight}"
        hspace="${pageFlow.imageHSpace}"
        vspace="${pageFlow.imageVSpace}" />
      <br>
    <netui:imageButton
        src="/coreWeb/index/Beehive.gif"
        alt="${pageFlow.imageAlt}"
        value="This is the value"
        />
    </body>
</netui:html>
