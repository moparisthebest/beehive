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
package formBean.test1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import formBean.test1.FormBeanTest1Form1;

public final class Struts1 extends Action
    {
    public final static String  STRUTS1_FORM_VALUE  = "Struts1 form value";
    public ActionForward execute(ActionMapping inMapping
                                 ,ActionForm inForm
                                 ,HttpServletRequest inRequest
                                 ,HttpServletResponse inResponse)
                                 throws Exception
        {
        // Get the form bean and change the value of field1.
        //----------------------------------------------------------------------
        formBean.test1.FormBeanTest1Form1 frm
            = (formBean.test1.FormBeanTest1Form1) inForm;
        //System.out.println(">>> Struts1.execute - ("
        //                   + inForm.toString() + ").");
        frm.setField1(STRUTS1_FORM_VALUE);

        // Forward control to the specified success target
        //----------------------------------------------------------------------
        return(inMapping.findForward("gotoJsp2"));
        }
    }
