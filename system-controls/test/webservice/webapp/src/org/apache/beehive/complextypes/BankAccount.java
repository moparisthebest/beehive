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

package org.apache.beehive.complextypes;

import java.io.Serializable;

/**
 * Complex type test bean.
 */
public class BankAccount extends Account implements Serializable {

    private AccountTransaction[] _transactions;
    private float _accountBalance;
    private String _accountHolderName;


    public BankAccount() {
        super();
    }

    public String getAccountHolderName() {
        return _accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        _accountHolderName = accountHolderName;
    }

    public float getAccountBalance() {
        return _accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        _accountBalance = accountBalance;
    }

    public void setTransactions(AccountTransaction[] transactions) {
        _transactions = transactions;
    }

    public AccountTransaction[] getTransactions() {
        return _transactions;
    }
}
