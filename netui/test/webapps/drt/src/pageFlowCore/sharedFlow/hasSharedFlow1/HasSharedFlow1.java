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
package pageFlowCore.sharedFlow.hasSharedFlow1;

import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

@Jpf.Controller(
    sharedFlowRefs={
        @Jpf.SharedFlowRef(name="rootSharedFlow", type=webappRoot.SharedFlow.class),
        @Jpf.SharedFlowRef(name="sf1", type=SharedFlow1.class)
    },
    simpleActions={
        @Jpf.SimpleAction(name="begin", path="index.jsp")
    }
)
public class HasSharedFlow1 extends PageFlowController
{
    @Jpf.SharedFlowField(name="sf1")
    private SharedFlow1 sf;
    
    public String getSharedFlowField()
    {
        return sf.getDisplayName();
    }
}
