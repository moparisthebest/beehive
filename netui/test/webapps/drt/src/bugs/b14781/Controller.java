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
package bugs.b14781;


import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

/**
 * @jpf:forward name="begin" path="Begin.jsp"
 */
@Jpf.Controller(
    forwards = {
        @Jpf.Forward(
            name = "begin",
            path = "Begin.jsp") 
    })
public class Controller extends PageFlowController
{
    String _selected = null;
    String[] _options = {"Option 1", "Option 2", "Option 3", "Option 4"};
    String _defaultValue = _options[2];

    public String getSelected() {
	return _selected;
    }
    public void setSelected(String selected) {
	_selected = selected;
    }

    public String[] getOptions() {
	return _options;
    }
    
    public String getDefaultValue() {
	return _defaultValue;
    }

    /**
     * @jpf:action
     */
    @Jpf.Action(
        )
    public Forward postback()
    {
        return new Forward("begin");
    }

    /**
     * @jpf:action
     */
    @Jpf.Action(
        )
    public Forward begin()
    {
        return new Forward("begin");
    }
}
