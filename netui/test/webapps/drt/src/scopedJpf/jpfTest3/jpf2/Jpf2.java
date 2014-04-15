/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Header:$
 */
package scopedJpf.jpfTest3.jpf2;

import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
import shared.FormA;

/**
 * @jpf:controller
 * @jpf:forward name="gotoDone" path="/resources/jsp/done.jsp"
 * @jpf:forward name="gotoError" path="/resources/jsp/error.jsp"
 * @jpf:view-properties view-properties::
 * <!-- This data is auto-generated. Hand-editing this section is not recommended. -->
 * <view-properties>
 * <pageflow-object id="pageflow:/scopedJpf/jpfTest3/jpf2/Jpf2.jpf"/>
 * <pageflow-object id="action:begin.do">
 *   <property value="240" name="x"/>
 *   <property value="160" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="action:testSecure.do#shared.FormA">
 *   <property value="60" name="x"/>
 *   <property value="40" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="action:unsecure.do#shared.FormA">
 *   <property value="800" name="x"/>
 *   <property value="180" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="action:goNested.do">
 *   <property value="240" name="x"/>
 *   <property value="240" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="action:nestedDone.do">
 *   <property value="500" name="x"/>
 *   <property value="380" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="action:finish.do">
 *   <property value="380" name="x"/>
 *   <property value="300" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="page:/resources/jsp/done.jsp">
 *   <property value="240" name="x"/>
 *   <property value="480" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="page:/resources/jsp/error.jsp">
 *   <property value="380" name="x"/>
 *   <property value="480" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="action-call:@page:Jsp2a.jsp@#@action:testSecure.do#shared.FormA@">
 *   <property value="764,430,430,96" name="elbowsX"/>
 *   <property value="281,281,32,32" name="elbowsY"/>
 *   <property value="West_0" name="fromPort"/>
 *   <property value="East_1" name="toPort"/>
 * </pageflow-object>
 * <pageflow-object id="action-call:@page:Jsp2a.jsp@#@action:goNested.do@">
 *   <property value="764,520,520,276" name="elbowsX"/>
 *   <property value="303,303,232,232" name="elbowsY"/>
 *   <property value="West_2" name="fromPort"/>
 *   <property value="East_1" name="toPort"/>
 * </pageflow-object>
 * <pageflow-object id="action-call:@page:Jsp2a.jsp@#@action:unsecure.do#shared.FormA@">
 *   <property value="800,800,789,789" name="elbowsX"/>
 *   <property value="256,240,240,224" name="elbowsY"/>
 *   <property value="North_1" name="fromPort"/>
 *   <property value="South_0" name="toPort"/>
 * </pageflow-object>
 * <pageflow-object id="page:Jsp2a.jsp">
 *   <property value="800" name="x"/>
 *   <property value="300" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="action-call:@external-jpf:../subJpf1/SubJpf1.jpf@#@action:nestedDone.do@">
 *   <property value="276,370,370,464" name="elbowsX"/>
 *   <property value="372,372,372,372" name="elbowsY"/>
 *   <property value="East_1" name="fromPort"/>
 *   <property value="West_1" name="toPort"/>
 * </pageflow-object>
 * <pageflow-object id="external-jpf:../subJpf1/SubJpf1.jpf">
 *   <property value="240" name="x"/>
 *   <property value="380" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="forward:path#success#Jsp2a.jsp#@action:begin.do@">
 *   <property value="276,520,520,764" name="elbowsX"/>
 *   <property value="163,163,292,292" name="elbowsY"/>
 *   <property value="East_2" name="fromPort"/>
 *   <property value="West_1" name="toPort"/>
 *   <property value="success" name="label"/>
 * </pageflow-object>
 * <pageflow-object id="forward:path#success#Jsp2a.jsp#@action:testSecure.do#shared.FormA@">
 *   <property value="96,430,430,764" name="elbowsX"/>
 *   <property value="43,43,281,281" name="elbowsY"/>
 *   <property value="East_2" name="fromPort"/>
 *   <property value="West_0" name="toPort"/>
 *   <property value="success" name="label"/>
 * </pageflow-object>
 * <pageflow-object id="forward:path#success#Jsp2a.jsp#@action:unsecure.do#shared.FormA@">
 *   <property value="800,800,789,789" name="elbowsX"/>
 *   <property value="224,240,240,256" name="elbowsY"/>
 *   <property value="South_1" name="fromPort"/>
 *   <property value="North_0" name="toPort"/>
 *   <property value="success" name="label"/>
 * </pageflow-object>
 * <pageflow-object id="forward:path#success#../subJpf1/SubJpf1.jpf#@action:goNested.do@">
 *   <property value="240,240,240,240" name="elbowsX"/>
 *   <property value="284,310,310,336" name="elbowsY"/>
 *   <property value="South_1" name="fromPort"/>
 *   <property value="North_1" name="toPort"/>
 *   <property value="success" name="label"/>
 * </pageflow-object>
 * <pageflow-object id="return-to:@forward:return-to#success#currentPage#@action:nestedDone.do@@">
 *   <property value="500" name="x"/>
 *   <property value="480" name="y"/>
 * </pageflow-object>
 * <pageflow-object id="forward:return-to#success#currentPage#@action:nestedDone.do@">
 *   <property value="500,500,500,500" name="elbowsX"/>
 *   <property value="424,430,430,436" name="elbowsY"/>
 *   <property value="South_1" name="fromPort"/>
 *   <property value="North_1" name="toPort"/>
 *   <property value="success" name="label"/>
 * </pageflow-object>
 * <pageflow-object id="forward:path#gotoDone#/resources/jsp/done.jsp"/>
 * <pageflow-object id="forward:path#gotoError#/resources/jsp/error.jsp"/>
 * </view-properties>
 * ::
 *
 */
@Jpf.Controller(
    forwards = {
        @Jpf.Forward(
            name = "gotoDone",
            path = "/resources/jsp/done.jsp"),
        @Jpf.Forward(
            name = "gotoError",
            path = "/resources/jsp/error.jsp") 
    })
@Jpf.ViewProperties(
    value = {
        "<!-- This data is auto-generated. Hand-editing this section is not recommended. -->",
        "<view-properties>",
        "<pageflow-object id='pageflow:/scopedJpf/jpfTest3/jpf2/Jpf2.jpf'/>",
        "<pageflow-object id='action:begin.do'>",
        "  <property value='240' name='x'/>",
        "  <property value='160' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='action:testSecure.do#shared.FormA'>",
        "  <property value='60' name='x'/>",
        "  <property value='40' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='action:unsecure.do#shared.FormA'>",
        "  <property value='800' name='x'/>",
        "  <property value='180' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='action:goNested.do'>",
        "  <property value='240' name='x'/>",
        "  <property value='240' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='action:nestedDone.do'>",
        "  <property value='500' name='x'/>",
        "  <property value='380' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='action:finish.do'>",
        "  <property value='380' name='x'/>",
        "  <property value='300' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='page:/resources/jsp/done.jsp'>",
        "  <property value='240' name='x'/>",
        "  <property value='480' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='page:/resources/jsp/error.jsp'>",
        "  <property value='380' name='x'/>",
        "  <property value='480' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='action-call:@page:Jsp2a.jsp@#@action:testSecure.do#shared.FormA@'>",
        "  <property value='764,430,430,96' name='elbowsX'/>",
        "  <property value='281,281,32,32' name='elbowsY'/>",
        "  <property value='West_0' name='fromPort'/>",
        "  <property value='East_1' name='toPort'/>",
        "</pageflow-object>",
        "<pageflow-object id='action-call:@page:Jsp2a.jsp@#@action:goNested.do@'>",
        "  <property value='764,520,520,276' name='elbowsX'/>",
        "  <property value='303,303,232,232' name='elbowsY'/>",
        "  <property value='West_2' name='fromPort'/>",
        "  <property value='East_1' name='toPort'/>",
        "</pageflow-object>",
        "<pageflow-object id='action-call:@page:Jsp2a.jsp@#@action:unsecure.do#shared.FormA@'>",
        "  <property value='800,800,789,789' name='elbowsX'/>",
        "  <property value='256,240,240,224' name='elbowsY'/>",
        "  <property value='North_1' name='fromPort'/>",
        "  <property value='South_0' name='toPort'/>",
        "</pageflow-object>",
        "<pageflow-object id='page:Jsp2a.jsp'>",
        "  <property value='800' name='x'/>",
        "  <property value='300' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='action-call:@external-jpf:../subJpf1/SubJpf1.jpf@#@action:nestedDone.do@'>",
        "  <property value='276,370,370,464' name='elbowsX'/>",
        "  <property value='372,372,372,372' name='elbowsY'/>",
        "  <property value='East_1' name='fromPort'/>",
        "  <property value='West_1' name='toPort'/>",
        "</pageflow-object>",
        "<pageflow-object id='external-jpf:../subJpf1/SubJpf1.jpf'>",
        "  <property value='240' name='x'/>",
        "  <property value='380' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='forward:path#success#Jsp2a.jsp#@action:begin.do@'>",
        "  <property value='276,520,520,764' name='elbowsX'/>",
        "  <property value='163,163,292,292' name='elbowsY'/>",
        "  <property value='East_2' name='fromPort'/>",
        "  <property value='West_1' name='toPort'/>",
        "  <property value='success' name='label'/>",
        "</pageflow-object>",
        "<pageflow-object id='forward:path#success#Jsp2a.jsp#@action:testSecure.do#shared.FormA@'>",
        "  <property value='96,430,430,764' name='elbowsX'/>",
        "  <property value='43,43,281,281' name='elbowsY'/>",
        "  <property value='East_2' name='fromPort'/>",
        "  <property value='West_0' name='toPort'/>",
        "  <property value='success' name='label'/>",
        "</pageflow-object>",
        "<pageflow-object id='forward:path#success#Jsp2a.jsp#@action:unsecure.do#shared.FormA@'>",
        "  <property value='800,800,789,789' name='elbowsX'/>",
        "  <property value='224,240,240,256' name='elbowsY'/>",
        "  <property value='South_1' name='fromPort'/>",
        "  <property value='North_0' name='toPort'/>",
        "  <property value='success' name='label'/>",
        "</pageflow-object>",
        "<pageflow-object id='forward:path#success#../subJpf1/SubJpf1.jpf#@action:goNested.do@'>",
        "  <property value='240,240,240,240' name='elbowsX'/>",
        "  <property value='284,310,310,336' name='elbowsY'/>",
        "  <property value='South_1' name='fromPort'/>",
        "  <property value='North_1' name='toPort'/>",
        "  <property value='success' name='label'/>",
        "</pageflow-object>",
        "<pageflow-object id='return-to:@forward:return-to#success#currentPage#@action:nestedDone.do@@'>",
        "  <property value='500' name='x'/>",
        "  <property value='480' name='y'/>",
        "</pageflow-object>",
        "<pageflow-object id='forward:return-to#success#currentPage#@action:nestedDone.do@'>",
        "  <property value='500,500,500,500' name='elbowsX'/>",
        "  <property value='424,430,430,436' name='elbowsY'/>",
        "  <property value='South_1' name='fromPort'/>",
        "  <property value='North_1' name='toPort'/>",
        "  <property value='success' name='label'/>",
        "</pageflow-object>",
        "<pageflow-object id='forward:path#gotoDone#/resources/jsp/done.jsp'/>",
        "<pageflow-object id='forward:path#gotoError#/resources/jsp/error.jsp'/>",
        "</view-properties>"
    })
public class Jpf2 extends PageFlowController {
    private FormA _form;
    public String scope;

    /**
     * @jpf:action
     * @jpf:forward name="success" path="Jsp2a.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "Jsp2a.jsp") 
        })
    protected Forward begin() {
        scope = getRequest().getParameter("jpfScopeID");
        //System.out.println(">>> Jpf2.begin - jpfScopeID: " + scope
        //                    + "jpf instance: " + this.toString());
        _form = new FormA();
        _form.setString1(scope);
        return new Forward("success", _form);
    }

    /**
     * @jpf:action form="_form"
     * @jpf:forward name="success" path="Jsp2a.jsp"
     */
    @Jpf.Action(
        useFormBean = "_form",
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "Jsp2a.jsp") 
        })
    protected Forward testSecure(FormA inForm) {
        //System.out.println(">>> Jpf2.testSecure: " + this.toString()
        //        + "\n\tinForm: " + inForm.toString()
        //        + "\n\t_form: " + _form.toString());
        if (this.getRequest().isSecure() == true)
            {
            return new Forward("success");
            }
        return new Forward("gotoError");
    }

    /**
     * @jpf:action form="_form"
     * @jpf:forward name="success" path="Jsp2a.jsp"
     */
    @Jpf.Action(
        useFormBean = "_form",
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "Jsp2a.jsp") 
        })
    protected Forward unsecure(FormA inForm) {
        //System.out.println(">>> Jpf2.unsecure: " + this.toString()
        //        + "\n\tinForm: " + inForm.toString()
        //        + "\n\t_form: " + _form.toString());
        return new Forward("success");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="../subJpf1/SubJpf1.jpf"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "../subJpf1/SubJpf1.jpf") 
        })
    protected Forward goNested() {
    //System.out.println(">>> Jpf2.goNested: " + this.toString());
        return new Forward("success");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" return-to="currentPage"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                navigateTo = Jpf.NavigateTo.currentPage) 
        })
    protected Forward nestedDone() {
    //System.out.println(">>> Jpf2.nestedDone: " + this.toString());
        return new Forward("success");
    }

    /**
     * @jpf:action
     */
    @Jpf.Action(
        )
    protected Forward finish() {
    //System.out.println(">>> Jpf2.finish");
        return new Forward("gotoDone");
    }
}
