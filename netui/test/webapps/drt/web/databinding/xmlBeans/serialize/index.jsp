<!--Generated by WebLogic Workshop-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-template"%>
<netui:html>
  <head>
    <title>SerializeXML Tag Test</title>
  </head>
  <body>
    <p>
      <b>SerializeXML Tag Test</b>
    <br/>
    <br/>
    <netui-data:serializeXML source="${pageInput.xmlbean}" divName="xmlbean"/>
    <br/>
    </p>
  </body>
</netui:html>
