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
package org.apache.beehive.netui.test.script;

import javax.servlet.ServletRequest;

import org.apache.beehive.netui.pageflow.PageFlowUtils;
import org.apache.beehive.netui.pageflow.internal.InternalUtils;

/**
 *
 */
public abstract class PageInputTest
    extends AbstractExpressionTest {

    public void testPageInputs()
        throws Exception {
        ServletRequest request = getPageContext().getRequest();

        String value = "This is a Page Input!";
        PageFlowUtils.addPageInput("test", value, request);

        request.setAttribute("pageInput", InternalUtils.getPageInputMap(request));

        Object result = evaluateExpression("{pageInput.test}", getPageContext());
        assert result.equals(value);
    }

    public void testActionOutputs()
        throws Exception {
        ServletRequest request = getPageContext().getRequest();

        String value = "This is a Page Input!";
        PageFlowUtils.addActionOutput("test", value, request);

        request.setAttribute("pageInput", InternalUtils.getPageInputMap(request));

        Object result = evaluateExpression("{pageInput.test}", getPageContext());

        assert result.equals(value);
    }

    public PageInputTest(String name) {
        super(name);
    }
}
