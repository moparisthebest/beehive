/**
 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 $Header:$
 */
package org.apache.beehive.samples.petstore.forms;

import org.apache.beehive.netui.pageflow.FormData;

/**
 *
 */
public class SecurityCheckForm
    extends FormData {

    private String _j_username;
    private String _j_password;

    public void setj_password(String j_password) {
        _j_password = j_password;
    }

    public String getj_password() {
        return _j_password;
    }

    public void setj_username(String j_username) {
        _j_username = j_username;
    }

    public String getj_username() {
        return _j_username;
    }
}
