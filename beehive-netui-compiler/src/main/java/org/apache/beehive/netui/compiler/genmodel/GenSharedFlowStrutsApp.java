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
package org.apache.beehive.netui.compiler.genmodel;

import org.apache.beehive.netui.compiler.typesystem.declaration.ClassDeclaration;
import org.apache.beehive.netui.compiler.typesystem.env.CoreAnnotationProcessorEnv;
import org.apache.beehive.netui.compiler.FlowControllerInfo;
import org.apache.beehive.netui.compiler.JpfLanguageConstants;
import org.apache.beehive.netui.compiler.Diagnostics;
import org.apache.beehive.netui.compiler.FatalCompileTimeException;

import java.io.File;
import java.io.IOException;


public class GenSharedFlowStrutsApp
        extends GenStrutsApp
        implements JpfLanguageConstants
{
    public GenSharedFlowStrutsApp( File sourceFile, ClassDeclaration jclass, CoreAnnotationProcessorEnv env,
                                   FlowControllerInfo fcInfo, boolean checkOnly, Diagnostics diagnostics )
            throws IOException, FatalCompileTimeException
    {
        super( sourceFile, jclass, env, fcInfo, checkOnly, diagnostics );
        recalculateStrutsConfigFile();  // it changes based on _isGlobalApp
        setSharedFlow( true );
    }

    String getStrutsConfigURI()
    {
        return getStrutsConfigURI( getContainingPackage(), true );
    }
    
    protected String getValidationFilePrefix()
    {
        return "sharedflow-validation";
    }
}
