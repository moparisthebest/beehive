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
package org.apache.beehive.controls.system.webservice.units.doclitwrapped.xmlbeangen;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.test.junit.ControlTestCase;
import complextypes.doclitwrapped.xmlbeangen.BankAccount;
import complextypes.doclitwrapped.xmlbeangen.AccountTransaction;
import complextypes.doclitwrapped.xmlbeangen.ArrayOfAccountTransaction;

/**
 * Tests for complex object types for a doc/lit/wrapped service.
 */
public class ComplexTypesDocLitWrappedXmlbeangenTest
    extends ControlTestCase {

    @Control
    public xmlbeangentest.ComplexTypesDocLitWrappedXmlbeangenService client;

    /**
     * Test echoing a complex type.
     */
    public void testEchoBankAccount() throws Exception {
        BankAccount account = BankAccount.Factory.newInstance();
        account.setAccountHolderName("Mike Nelson");
        account.setAccountNumber(12345678L);
        account.setAccountBalance(123.33F);

        ArrayOfAccountTransaction array = ArrayOfAccountTransaction.Factory.newInstance();
        AccountTransaction tx = array.addNewItem();
        tx.setAmount(1.00F);
        tx.setPayee("Servo");

        tx = array.addNewItem();
        tx.setAmount(66.01F);
        tx.setPayee("Crow");
        account.setTransactions(array);

        BankAccount result = client.echoAccount(account);

        assertTrue(result.getAccountBalance() == 123.33F );
        assertTrue("Mike Nelson".equals(result.getAccountHolderName()));
        assertTrue(result.getAccountNumber() == 12345678L);
// todo: array not coming through -- needs further investigation!
// appears to be same issue as rpc/lit case wsm is not using the correct tags
//        ArrayOfAccountTransaction transactions = result.getTransactions();
//        AccountTransaction[] items = transactions.getItemArray();
//        assertTrue(items[0].getAmount() == 1.00F);
//        assertTrue(items[0].getPayee().equals("Servo"));
//
//        assertTrue(items[1].getAmount() == 66.00F);
//        assertTrue(items[1].getPayee().equals("Crow"));
    }

    public static Test suite() {
        return new TestSuite(ComplexTypesDocLitWrappedXmlbeangenTest.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}