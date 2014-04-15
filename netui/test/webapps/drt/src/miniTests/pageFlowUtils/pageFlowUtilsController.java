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
package miniTests.pageFlowUtils;
import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
import org.apache.struts.action.ActionForm;

@Jpf.Controller(
    messageBundles = {
        @Jpf.MessageBundle(bundlePath = "pageFlowUtils.ValidationMessages") 
    },
    sharedFlowRefs={
        @Jpf.SharedFlowRef(name="tempSharedFlow", type=TempSharedFlow.class)
    }
)
public class pageFlowUtilsController extends PageFlowController
{
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "validation.jsp") 
        })
    protected Forward validation()
    {
        return new Forward("success");
    }

    public static class TestForm extends ActionForm
    {
    }
    
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "index.jsp") 
        })
    protected Forward begin()
    {
        return new Forward( "success" );
    }

    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "/miniTests/pageFlowUtils/nested/nestedController.jpf") 
        })
    protected Forward goNested()
    {
        return new Forward("success");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "index.jsp") 
        })
    protected Forward nestedDone()
    {
        return new Forward("success");
    }
}
