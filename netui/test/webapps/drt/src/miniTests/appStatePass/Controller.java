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
package miniTests.appStatePass;

import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

import javax.servlet.http.HttpSession;

@Jpf.Controller
public class Controller extends PageFlowController
{
    protected transient global.Global globalApp;
    public String getState() {
	    return globalApp.getPageFlowState();
    }

    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "nest",
                path = "nested/Controller.jpf") 
        })
    public Forward nest()
    {
        globalApp.setPageFlowState(globalApp.getPageFlowState() + "<br />nest");
        return new Forward("nest");
    }

    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "begin",
                path = "Begin.jsp") 
        })
    public Forward returnToPage()
    {
        HttpSession session = getSession();
        session.setAttribute("pageFlow",this);
        globalApp.setPageFlowState(globalApp.getPageFlowState() + "<br />returnToPage");
        return new Forward("begin");
    }

    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "begin",
                path = "Begin.jsp") 
        })
    public Forward returnToAction()
    {
        HttpSession session = getSession();
        session.setAttribute("pageFlow",this);
        globalApp.setPageFlowState(globalApp.getPageFlowState() + "<br />returnToAction");
        return new Forward("begin");
    }

    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "begin",
                path = "Begin.jsp") 
        })
    public Forward begin()
    {
        globalApp.setPageFlowState(globalApp.getPageFlowState() + "<br />begin");
        return new Forward("begin");
    }
}
