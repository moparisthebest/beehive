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
package org.apache.beehive.netui.compiler.typesystem.impl;

public class DelegatingImpl
{
    private Object _delegate;
    
    protected DelegatingImpl( Object delegate )
    {
        assert delegate != null;
        _delegate = delegate;
    }
    
    public boolean equals( Object o )
    {
        if ( o == null ) return false;
        if ( o == this ) return true;
        if ( ! ( o instanceof DelegatingImpl ) ) return false;
        return _delegate.equals( ( ( DelegatingImpl ) o )._delegate );
    }
    
    public int hashCode()
    {
        return _delegate.hashCode();
    }
    
    public String toString()
    {
        return _delegate.toString();
    }
    
    protected Object getDelegate()
    {
        return _delegate;
    }
}
