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
package miniTests.buttonSubmit;


import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

import java.io.Serializable;

@Jpf.Controller
public class Controller extends PageFlowController
{
    private String _action;

    public String getAction() {
        return _action;
    }
    public void setAction(String action) {
        _action = action;
    }

    public static class Form implements Serializable {
    }

    /**
     * @jpf:action
     * @jpf:forward name="buttonOne" path="Begin.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "buttonOne",
                path = "Begin.jsp") 
        })
    public Forward buttonOne(Form form)
    {
        _action = "buttonOne";
        return new Forward("buttonOne");
    }

    /**
     * @jpf:action
     * @jpf:forward name="buttonTwo" path="Begin.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "buttonTwo",
                path = "Begin.jsp") 
        })
    public Forward buttonTwo(Form form)
    {
        _action = "buttonTwo";
        return new Forward("buttonTwo");
    }

    /**
     * @jpf:action
     * @jpf:forward name="begin" path="Begin.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "begin",
                path = "Begin.jsp") 
        })
    public Forward begin()
    {
        _action = "begin";
        return new Forward("begin");
    }
}
