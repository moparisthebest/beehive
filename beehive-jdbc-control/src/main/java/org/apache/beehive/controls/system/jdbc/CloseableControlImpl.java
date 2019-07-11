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
package org.apache.beehive.controls.system.jdbc;

import com.moparisthebest.jdbc.codegen.JdbcMapperFactory;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.bean.Extensible;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.api.context.ResourceContext;
import org.apache.beehive.controls.api.context.ResourceContext.ResourceEvents;
import org.apache.beehive.controls.api.events.EventHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Closeable;
import java.lang.reflect.Method;

import static com.moparisthebest.jdbc.TryClose.tryClose;

/**
 * The implementation class for the database controller.
 */
@ControlImplementation
public class CloseableControlImpl implements CloseableControl, Extensible, java.io.Serializable {

    //
    // contexts provided by the beehive controls runtime
    //
    @org.apache.beehive.controls.api.context.Context
    protected ControlBeanContext _context;
    @org.apache.beehive.controls.api.context.Context
    protected ResourceContext _resourceContext;

    protected transient Closeable closeable;

    private static final Log LOGGER = LogFactory.getLog(CloseableControlImpl.class);

    /**
     * Constructor
     */
    public CloseableControlImpl() { }

    /**
     * Invoked by the controls runtime when a new instance of this class is aquired by the runtime
     */
    @EventHandler(field = "_resourceContext", eventSet = ResourceEvents.class, eventName = "onAcquire")
    public void onAquire() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Enter: onAquire()");
        }

        try {
            @SuppressWarnings("unchecked")
            final Closeable closeable = (Closeable) JdbcMapperFactory.create(_context.getControlInterface());
            this.closeable = closeable;
        } catch (Exception se) {
            throw new ControlException("Exception while attempting to instantiate implementation.", se);
        }
    }

    /**
     * Invoked by the controls runtime when an instance of this class is released by the runtime
     */
    @EventHandler(field = "_resourceContext", eventSet = ResourceEvents.class, eventName = "onRelease")
    public void close() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Enter: onRelease()");
        }

        tryClose(closeable);

        closeable = null;
    }

    /**
     * Called by the Controls runtime to handle calls to methods of an extensible control.
     *
     * @param method The extended operation that was called.
     * @param args   Parameters of the operation.
     * @return The value that should be returned by the operation.
     * @throws Throwable any exception declared on the extended operation may be
     *                   thrown.  If a checked exception is thrown from the implementation that is not declared
     *                   on the original interface, it will be wrapped in a ControlException.
     */
    public Object invoke(Method method, Object[] args) throws Throwable {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Enter: invoke()");
        }

        assert closeable != null : "invoke(): Closeable has been closed!!!!";

        return method.invoke(closeable, args);
    }
}
