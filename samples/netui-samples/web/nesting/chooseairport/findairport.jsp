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

    <netui-template:setAttribute name="sampleTitle" value="Nesting"/>

    <netui-template:section name="main">
        <h3>Choose Airport (nested page flow)</h3>
        <netui:form action="doSearch">
            Airport city or name:
            <netui:textBox dataSource="actionForm.searchText"/>
            <netui:error key="searchText"/>
            <br/>
            <netui:button>OK</netui:button>
            <netui:button action="cancelSearch">cancel</netui:button>
        </netui:form>
    </netui-template:section>

</netui-template:template>
