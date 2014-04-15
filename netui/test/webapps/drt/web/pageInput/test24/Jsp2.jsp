<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0"        prefix="netui" %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data" %>

<html>
   <head>
      <title>PageInput Test24</title>
   </head>
   <body>
      <h3 align="center">PageInput Test24 - Jsp2.jsp</h3>
      <hr width="95%"/>
      <br/>
      <center>
         <br/>
         <h2><font color="blue">Testing PageInput tags</font></h2>
         <br/><br/>
         <netui-data:declarePageInput name="string" type="java.lang.String" />

         PageInput context value:
         <font color="blue">
            <netui:span value="${pageInput.string}" />
         </font>
         <br/><br/>
         PageFlow context value:
         <font color="blue">
            <netui:span value="${pageFlow.string}" />
         </font>
         <br/><br/>

         <netui:anchor action="finish">Finish...</netui:anchor>
      </center>
   </body>
</html>
