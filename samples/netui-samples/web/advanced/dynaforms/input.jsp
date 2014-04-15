<%--
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

   $Header:$
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="netui" uri="http://beehive.apache.org/netui/tags-html-1.0"%>
<%@ taglib prefix="netui-data" uri="http://beehive.apache.org/netui/tags-databinding-1.0"%>
<%@ taglib prefix="netui-template" uri="http://beehive.apache.org/netui/tags-template-1.0"%>


<netui-template:template templatePage="/resources/template/template.jsp">
    <netui-template:setAttribute name="sampleTitle" value="DynaForms"/>
    <netui-template:section name="main">

        <p>
            The form bean for the <code>submit</code> action is a DynaActionForm defined in
            merge-struts-config-dynaforms.xml.  This file contains the form bean definition (notice
            that the form bean type is <code>org.apache.beehive.netui.pageflow.DynaFormData</code>)
            and the addition of <code>name="myDynaForm"</code> to the <code>submit</code> action.
        </p>
        <p>
            The merge-struts-config-dynaforms.xml file is merged into the page flow controller
            through the <code>strutsMerge</code> attribute on <code>@Jpf.Controller</code> in
            Controller.java.
        </p>
        <p>
            In this sample, we have added validation annotations on the action that apply to
            properties on the DynaActionForm.  This of course is optional.
        </p>

        <hr/>
        <netui:form action="submit">
            <table>
                <tr>
                    <td>Name (at least 2 chars, only letters/spaces):</td>
                    <td><netui:textBox dataSource="actionForm.name"/></td>
                    <td><span style="color:red"><netui:error key="name"/></span></td>
                </tr>
                <tr>
                    <td>Age (must be within the range 1-150):</td>
                    <td><netui:textBox dataSource="actionForm.age"/></td>
                    <td><span style="color:red"><netui:error key="age"/></span></td>
                </tr>
            </table>
            <br/>
            <netui:button value="submit"/>
        </netui:form>

    </netui-template:section>
</netui-template:template>
