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
package miniTests.inheritFormBeanMessages;

import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

@Jpf.Controller(
    simpleActions={
        @Jpf.SimpleAction(name="begin", path="index.jsp")
    }
)
public class Controller extends PageFlowController
{
    @Jpf.Action(
        forwards={
            @Jpf.Forward(name="index", path="index.jsp")
        },
        validationErrorForward=@Jpf.Forward(name="failure", path="index.jsp")
    )
    public Forward submitBaseFormBean( BaseFormBean form )
    {
        return new Forward( "index" );
    }

    @Jpf.Action(
        forwards={
            @Jpf.Forward(name="index", path="index.jsp")
        },
        validationErrorForward=@Jpf.Forward(name="failure", path="index.jsp")
    )
    public Forward submitDerivedFormBean( DerivedFormBean form )
    {
        return new Forward( "index" );
    }

    @Jpf.Action(
        forwards={
            @Jpf.Forward(name="index", path="index.jsp")
        },
        validationErrorForward=@Jpf.Forward(name="failure", path="index.jsp")
    )
    public Forward submitOverridingFormBean( OverridingFormBean form )
    {
        return new Forward( "index" );
    }

    @Jpf.FormBean(messageBundle="miniTests.inheritFormBeanMessages.BaseMessages")
    public static class BaseFormBean
        implements java.io.Serializable
    {
        private String _foo;

        @Jpf.ValidatableProperty(
            validateRequired=@Jpf.ValidateRequired(messageKey="msg")
        )
        public String getFoo()
        {
            return _foo;
        }

        public void setFoo( String foo )
        {
            _foo = foo;
        }
    }

    public static class DerivedFormBean
        extends BaseFormBean
    {
        private String _bar;

        @Jpf.ValidatableProperty(
            validateRequired=@Jpf.ValidateRequired(messageKey="msg")
        )
        public String getBar()
        {
            return _bar;
        }

        public void setBar( String bar )
        {
            _bar = bar;
        }
    }

    @Jpf.FormBean(messageBundle="miniTests.inheritFormBeanMessages.OverrideMessages")
    public static class OverridingFormBean
        extends BaseFormBean
    {
        private String _baz;

        @Jpf.ValidatableProperty(
            validateRequired=@Jpf.ValidateRequired(messageKey="msg")
        )
        public String getBaz()
        {
            return _baz;
        }

        public void setBaz( String baz )
        {
            _baz = baz;
        }
    }
}
