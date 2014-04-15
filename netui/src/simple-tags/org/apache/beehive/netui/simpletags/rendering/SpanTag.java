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
package org.apache.beehive.netui.simpletags.rendering;

import org.apache.beehive.netui.simpletags.html.HtmlConstants;
import org.apache.beehive.netui.simpletags.appender.Appender;

import java.util.HashMap;

/**
 * Body, Start Tag: optional, End tag: optional
 * Required href
 */
public abstract class SpanTag extends TagHtmlBase
{
    public static void add(HashMap html, HashMap htmlQuirks, HashMap xhtml)
    {
        html.put(SPAN_TAG, new Rendering());
        htmlQuirks.put(SPAN_TAG, new Rendering());
        xhtml.put(SPAN_TAG, new Rendering());
    }

    public static class State extends AbstractHtmlState
    {
        public void clear()
        {
            super.clear();
        }
    }

    private static class Rendering extends SpanTag implements HtmlConstants
    {
        public void doStartTag(Appender sb, AbstractTagState renderState)
        {
            assert(sb != null) : "Parameter 'sb' must not be null";
            assert(renderState != null) : "Parameter 'renderState' must not be null";
            assert(renderState instanceof State) : "Paramater 'renderState' must be an instance of SpanTag.State";

            State state = (State) renderState;

            renderTag(sb, SPAN);
            renderAttribute(sb, ID, state.id);
            renderAttribute(sb, CLASS, state.styleClass);

            renderAttributes(AbstractHtmlState.ATTR_GENERAL, sb, state);
            renderAttribute(sb, STYLE, state.style);
            renderAttributes(AbstractHtmlState.ATTR_JAVASCRIPT, sb, state);
            sb.append(">");
        }

        public void doEndTag(Appender sb)
        {
            renderEndTag(sb, SPAN);
        }
    }
}
