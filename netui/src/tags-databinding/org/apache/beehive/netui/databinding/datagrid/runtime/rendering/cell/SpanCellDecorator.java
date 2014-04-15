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
package org.apache.beehive.netui.databinding.datagrid.runtime.rendering.cell;

import javax.servlet.jsp.JspContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.beehive.netui.databinding.datagrid.api.exceptions.CellDecoratorException;
import org.apache.beehive.netui.databinding.datagrid.api.rendering.CellDecorator;
import org.apache.beehive.netui.databinding.datagrid.api.rendering.CellModel;
import org.apache.beehive.netui.databinding.datagrid.runtime.model.cell.SpanCellModel;
import org.apache.beehive.netui.databinding.datagrid.runtime.util.JspUtil;
import org.apache.beehive.netui.tags.rendering.AbstractRenderAppender;
import org.apache.beehive.netui.tags.rendering.TagRenderingBase;

/**
 *
 */
public final class SpanCellDecorator
    extends CellDecorator {

    public void decorate(JspContext jspContext, AbstractRenderAppender appender, CellModel cellModel)
            throws CellDecoratorException {

        assert cellModel instanceof SpanCellModel;
        SpanCellModel spanCellModel = (SpanCellModel)cellModel;

        HttpServletRequest request = JspUtil.getRequest(jspContext);
        TagRenderingBase span = TagRenderingBase.Factory.getRendering(TagRenderingBase.SPAN_TAG, request);

        String formatted = cellModel.formatText(spanCellModel.getValue());

        span.doStartTag(appender, spanCellModel.getSpanState());
        appender.append(formatted);
        span.doEndTag(appender);

        String javascript = spanCellModel.getJavascript();
        if(javascript != null)
            appender.append(javascript);
    }
}
