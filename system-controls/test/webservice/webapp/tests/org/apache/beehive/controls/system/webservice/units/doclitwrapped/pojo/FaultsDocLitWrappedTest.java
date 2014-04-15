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
package org.apache.beehive.controls.system.webservice.units.doclitwrapped.pojo;

import complextypes.doclitwrapped.pojo.ComplexAccountException;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.test.junit.ControlTestCase;

import java.rmi.RemoteException;

/**
 * Tests for faults from a doc/lit/wrapped service.
 */
public class FaultsDocLitWrappedTest
        extends ControlTestCase {

    @Control
    public pojotypetest.ComplexTypesDocLitWrappedService client;

    /**
     * Test fault handling for a RemoteException.
     *
     * @throws Exception
     */
    public void testThrowAccountException() throws Exception {
        try {
            client.throwAccountException(66);
        }
        catch (Exception e) {
            assertTrue(e instanceof RemoteException);
            assertTrue("org.apache.beehive.complextypes.AccountException: AccountException; input value=\"66\"".equals(e.getMessage()));
            return;
        }
        fail("Expected AccountException to be thrown!");
    }

    /**
     * Test complex exception/fault handling.
     *
     * @throws Exception
     */
    public void testThrowComplexAccountException() throws Exception {
        try {
            client.throwComplexAccountException();
        }
        catch (Exception e) {
            assertTrue(e instanceof RemoteException);
            assertTrue(((RemoteException) e).detail instanceof ComplexAccountException);

            ComplexAccountException cae = (ComplexAccountException) ((RemoteException) e).detail;
            assertTrue("FirstMessage".equals(cae.getMsg1()));
            assertTrue("SecondMessage".equals(cae.getMsg2()));
            return;
        }
        fail("Expected ComplexAccountException to be thrown!");
    }

    public static Test suite() {
        return new TestSuite(FaultsDocLitWrappedTest.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}