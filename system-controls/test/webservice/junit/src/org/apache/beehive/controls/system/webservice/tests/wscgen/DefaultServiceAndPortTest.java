/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.apache.beehive.controls.system.webservice.tests.wscgen;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.beehive.controls.system.webservice.ServiceControl;

/**
 * If a WSDL file contains multiple services and a service name is not specfied for the
 * web service control, the last service defined will be the one the generator uses to
 * gen the wsc.
 */
public class DefaultServiceAndPortTest extends TestCase {

    private final Class genWsc = test1.TestService2.class;

    public void testServiceName() throws Exception {
        ServiceControl.WSDL wsdl =
                (ServiceControl.WSDL) genWsc.getAnnotation(ServiceControl.WSDL.class);
        assertEquals("TestService2", wsdl.service());
    }

    public void testPortName() throws Exception {
        ServiceControl.WSDL wsdl =
                (ServiceControl.WSDL) genWsc.getAnnotation(ServiceControl.WSDL.class);
        assertEquals("TestService2", wsdl.portName());
    }

    public static Test suite() {
        return new TestSuite(DefaultServiceAndPortTest.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}



