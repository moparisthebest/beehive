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
package org.apache.beehive.controls.api.events;

import java.lang.reflect.InvocationTargetException;

import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlHandle;
import org.apache.beehive.controls.api.context.ControlThreadContext;

/**
 * The EventDispatchHelper class is a simple implementation of the EventDispatcher interface
 * that is suitable for use <b>inside</b> the execution context of a control container.  It 
 * assumes that you are already running inside the target container instance, and all that is 
 * required is the correct routing of the event to the correct control.
 */
public class EventDispatchHelper implements EventDispatcher
{
    public Object dispatchEvent(ControlHandle target, EventRef event, Object [] args)
        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        //
        // Obtain the current active control container context
        //
        ControlContainerContext context = ControlThreadContext.getContext();
        if (context == null)
            throw new IllegalStateException("No active control container context");

        //
        // Dispatch the event using it.
        //
        return context.dispatchEvent(target, event, args);
    }
}
