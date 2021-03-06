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
package org.apache.beehive.netui.compiler;

import org.apache.beehive.netui.compiler.genmodel.GenSharedFlowStrutsApp;
import org.apache.beehive.netui.compiler.genmodel.GenStrutsApp;
import org.apache.beehive.netui.compiler.typesystem.declaration.ClassDeclaration;
import org.apache.beehive.netui.compiler.typesystem.env.CoreAnnotationProcessorEnv;

import java.io.File;
import java.io.IOException;


public class SharedFlowGenerator
    extends FlowControllerGenerator
{
    public SharedFlowGenerator( CoreAnnotationProcessorEnv env, FlowControllerInfo fcInfo, Diagnostics diags )
    {
        super( env, fcInfo, diags );
    }

    protected GenStrutsApp createStrutsApp( ClassDeclaration jclass )
            throws IOException, FatalCompileTimeException
    {
        File sourceFile = CompilerUtils.getSourceFile( jclass, true );
        return new GenSharedFlowStrutsApp( sourceFile, jclass, getEnv(), getFCSourceFileInfo(), false, getDiagnostics() );
    }
}
