<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0"        prefix="netui" %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data" %>

<html>
   <head>
      <title>FormBeanRePost Test52</title>
   </head>
   <body>
      <h3 align="center">FormBeanRePost Test52 - Jsp2.jsp</h3>
      <hr width="95%"/>
      <br/>
      <center>
         <h3>
            Testing Re-Posting of form beans.
         </h3>
         <br/>
         <netui:form action="action3">
            <font color="blue">
               Form String 1: <netui:textBox dataSource="actionForm.string1"/>
            </font>
            <br/>

            <font color="blue">
               Form String 2: <netui:textBox dataSource="actionForm.string2"/>
            </font>
            <br/>

            <font color="blue">
               PageFlow String 3: <netui:textBox dataSource="pageFlow.string"/>
            </font>
            <br/><br/>
            <netui:button type="submit">Continue...</netui:button>
         </netui:form>
      </center>
   </body>
</html>
