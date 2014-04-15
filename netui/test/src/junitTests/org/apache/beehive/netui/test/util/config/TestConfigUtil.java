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
package org.apache.beehive.netui.test.util.config;

import java.io.InputStream;

import org.apache.beehive.netui.util.config.ConfigUtil;
import org.apache.beehive.netui.util.config.ConfigInitializationException;
import org.apache.beehive.netui.util.xml.XmlInputStreamResolver;

/**
 *
 */
public class TestConfigUtil
    extends ConfigUtil {

    private static final String DEFAULT_CONFIG = "WEB-INF/beehive-netui-config.xml";

    public static void testInit() {
        try {
            internalInit(null);
        } catch(ConfigInitializationException cie) {
            /*
              sometimes bad form to do this, but in the default case for testing,
              it's convenient to just bail with a runtime exception to just fail the test
             */
            throw new IllegalStateException("Caught exception initializing the default config file \"" + DEFAULT_CONFIG + "\"", cie);
        }
    }
}
