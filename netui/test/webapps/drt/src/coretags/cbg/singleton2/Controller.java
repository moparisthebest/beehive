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
package coretags.cbg.singleton2;

import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

@Jpf.Controller(
    )
public class Controller extends PageFlowController
{
    private String[] _resultsOne;
    private String[] _resultsTwo;

    public String[] getResultsOne()
    {
        return _resultsOne;
    }

    public void setResultsOne(String[] _resultsOne)
    {
        this._resultsOne = _resultsOne;
    }

    public String[] getResultsTwo()
    {
        return _resultsTwo;
    }

    public void setResultsTwo(String[] _resultsTwo)
    {
        this._resultsTwo = _resultsTwo;
    }

    protected void onCreate()
    {        
    }
    
    /**
     * @jpf:action
     * @jpf:forward name="index" path="index.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "index",
                path = "index.jsp") 
        })
    protected Forward begin()
    {
        return new Forward("index");
    }
    
    /**
     * @jpf:action
     * @jpf:forward name="index" path="Results.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "index",
                path = "Results.jsp") 
        })
    protected Forward post()
    {
        return new Forward("index");
    }
}
