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
package org.apache.beehive.netui.util.config.bean;

/**
 *
 */
public class PrefixHandlerConfig {
    private String _name;
    private String _handlerClass;

    public PrefixHandlerConfig(String name, String handlerClass) {
        _name = name;
        _handlerClass = handlerClass;
    }

    public String getName() {
        return _name;
    }

    public String getHandlerClass() {
        return _handlerClass;
    }
}
