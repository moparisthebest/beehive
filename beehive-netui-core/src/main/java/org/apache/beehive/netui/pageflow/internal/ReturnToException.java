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
package org.apache.beehive.netui.pageflow.internal;

import org.apache.beehive.netui.pageflow.PageFlowException;
import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.FlowController;

public abstract class ReturnToException extends PageFlowException
{
    private String _returnToType;
    
    
    public ReturnToException( String actionName, Forward fwd, FlowController fc )
    {
        super( actionName, fc );
        _returnToType = fwd.getReturnToTypeAsString();
    }

    public String getReturnToType()
    {
        return _returnToType;
    }

    protected Object[] getMessageArgs()
    {
        return new Object[]{ _returnToType, getActionName(), getFlowControllerURI() };
    }

    protected abstract String[] getMessageParts();

    /**
     * Tell whether the root cause may be session expiration in cases where the requested session ID is different than
     * the actual session ID.  In this case, the answer is <code>true</code>.
     */ 
    public boolean causeMayBeSessionExpiration()
    {
        return true;
    }
}
