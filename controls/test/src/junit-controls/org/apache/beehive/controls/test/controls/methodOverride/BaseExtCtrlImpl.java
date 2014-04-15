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

package org.apache.beehive.controls.test.controls.methodOverride;

import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.bean.Extensible;

import java.lang.reflect.Method;

/**
 */
@ControlImplementation(isTransient=true)
public class BaseExtCtrlImpl implements BaseExtCtrl, Extensible {


    /**
     * Called by the Controls runtime to handle calls to methods of an
     * extensible control.
     * <p/>
     *
     * @param method The extended operation that was called.
     * @param args   Parameters of the operation.
     * @return The value that should be returned by the operation.
     * @throws Throwable any exception declared on the extended operation may be
     *                   thrown.  If a checked exception is thrown from the implementation that is not declared
     *                   on the original interface, it will be wrapped in a ControlException.
     */
    public Object invoke(Method method, Object[] args) throws Throwable {
        return null;
    }
}
