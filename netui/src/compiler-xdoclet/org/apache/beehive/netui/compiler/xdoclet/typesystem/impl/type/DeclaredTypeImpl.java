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
package org.apache.beehive.netui.compiler.xdoclet.typesystem.impl.type;

import org.apache.beehive.netui.compiler.typesystem.declaration.TypeDeclaration;
import org.apache.beehive.netui.compiler.typesystem.type.DeclaredType;
import org.apache.beehive.netui.compiler.typesystem.type.InterfaceType;
import org.apache.beehive.netui.compiler.xdoclet.typesystem.impl.WrapperFactory;
import xjavadoc.XClass;

public class DeclaredTypeImpl
        extends ReferenceTypeImpl
        implements DeclaredType
{
    public DeclaredTypeImpl( XClass delegate )
    {
        super( delegate );
    }
    
    public TypeDeclaration getDeclaration()
    {
        return WrapperFactory.get().getTypeDeclaration( getDelegateXClass() );
    }

    public DeclaredType getContainingType()
    {
        return WrapperFactory.get().getDeclaredType( getDelegateXClass().getContainingClass() );
    }

    public InterfaceType[] getSuperinterfaces()
    {
        return WrapperFactory.get().getDeclaredType( getDelegateXClass().getContainingClass() ).getSuperinterfaces();
    }
    
    public XClass getDelegateXClass()
    {
        return ( XClass ) super.getDelegate();
    }
    
    public boolean equals( Object o )
    {
        return super.equals( o ) ||
               ( o instanceof DeclaredType && ( ( DeclaredType ) o ).getDeclaration().equals( getDeclaration() ) );
    }
}
