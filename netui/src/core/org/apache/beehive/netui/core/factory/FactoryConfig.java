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
package org.apache.beehive.netui.core.factory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration object passed to a {@link Factory}.
 */ 
public class FactoryConfig
        implements Serializable
{
    private Map/*< String, String >*/ _customProperties = null;
    
    void addCustomProperty( String name, String value )
    {
        if ( _customProperties == null ) _customProperties = new HashMap();
        _customProperties.put( name, value );
    }
    
    public String getCustomProperty( String name )
    {
        return _customProperties != null ? ( String ) _customProperties.get( name ) : null;
    }
}
