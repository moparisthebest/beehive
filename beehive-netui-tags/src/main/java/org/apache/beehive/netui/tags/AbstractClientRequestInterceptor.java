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
package org.apache.beehive.netui.tags;

import org.apache.beehive.netui.pageflow.interceptor.request.RequestInterceptor;

abstract public class AbstractClientRequestInterceptor extends RequestInterceptor
{
    protected String getCommand(String cmd, String ctxtPath)
    {
        // catch any runtime errors here and return.
        try {
            cmd = cmd.substring(ctxtPath.length() + 1);
            int idx = cmd.lastIndexOf('.');
            if (idx != -1) {
                cmd = cmd.substring(0, idx);
            }
        }
        catch (RuntimeException e) {
            System.err.println("Runtime Error creating XmlRequestServlet Command:" + e.getClass().getName());
            return null;
        }
        return cmd;
    }
}
