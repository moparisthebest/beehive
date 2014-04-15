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
// ------------------------------------------------------------------------------
//  Generated by WebLogic Workshop
// 
//  Created on: Tue Jul 29 19:03:54 GMT-07:00 2003
//  By: rfeit
// -----------------------------------------------------------------------------
package miniTests.readonlyAction;
import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @jpf:controller read-only="false"
 * @jpf:catch type="readonlyActionController.TestExceptionRO" method="handleExceptionRO"
 * @jpf:catch type="readonlyActionController.TestException" method="handleException"
 */
@Jpf.Controller(
    readOnly = false,
    catches = {
        @Jpf.Catch(
            type = readonlyActionController.TestExceptionRO.class,
            method = "handleExceptionRO"),
        @Jpf.Catch(
            type = readonlyActionController.TestException.class,
            method = "handleException") 
    })
public class readonlyActionController extends PageFlowController
{
    private ArrayList history = new ArrayList();

    public ArrayList getHistory() {
        return history;
    }
    
    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "index.jsp") 
        })
    protected Forward begin()
    {
        return new Forward( "success" );
    }

    /**
     * @jpf:action read-only="true"
     * @jpf:forward name="success" path="index.jsp"
     */
    @Jpf.Action(
        readOnly = true,
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "index.jsp") 
        })
    protected Forward readonlyAction()
    {
        return new Forward("success");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "index.jsp") 
        })
    protected Forward nonReadonlyAction()
    {
        return new Forward("success");
    }
    
    public void ensureFailover( HttpServletRequest request )
    {
        String exHandlerName = ( String ) request.getAttribute( "exHandlerName" );
        String name = ( exHandlerName != null ? exHandlerName : getCurrentActionName() );
        history.add( "ensureFailover called from " + name );
        super.ensureFailover( request );
    }
    
    public void beforeAction()
    {
        history.add( "calling action " + getCurrentActionName() );
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="/miniTests/readonlyAction/nested/nestedController.jpf"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "/miniTests/readonlyAction/nested/nestedController.jpf") 
        })
    protected Forward goNested()
    {
        return new Forward("success");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    @Jpf.Action(
        forwards = {
            @Jpf.Forward(
                name = "success",
                path = "index.jsp") 
        })
    protected Forward nestedDone()
    {
        return new Forward("success");
    }

    /**
     * @jpf:action read-only="true"
     */
    @Jpf.Action(
        readOnly = true)
    protected Forward readonlyExceptionHandler()
        throws Exception
    {
        throw new TestExceptionRO();
    }

    /**
     * @jpf:action read-only="true"
     */
    @Jpf.Action(
        readOnly = true)
    protected Forward nonReadonlyExceptionHandler()
        throws Exception
    {
        throw new TestException();
    }
    
    /**
     * @jpf:exception-handler
     * @jpf:forward name="current" return-to="currentPage"
     */
    @Jpf.ExceptionHandler(
        forwards = {
            @Jpf.Forward(
                name = "current1",
                navigateTo = Jpf.NavigateTo.currentPage) 
        })
    protected Forward handleException( TestException e, String actionName, String message, Object form )
    {
        getRequest().setAttribute( "exHandlerName", "exception-handler handleException" );
        return new Forward( "current1" );
    }
    
    /**
     * @jpf:exception-handler read-only="true"
     * @jpf:forward name="current" return-to="currentPage"
     */
    @Jpf.ExceptionHandler(
        readOnly = true,
        forwards = {
            @Jpf.Forward(
                name = "current2",
                navigateTo = Jpf.NavigateTo.currentPage) 
        })
    protected Forward handleExceptionRO( TestExceptionRO e, String actionName, String message, Object form )
    {
        getRequest().setAttribute( "exHandlerName", "exception-handler handleExceptionRO" );
        return new Forward( "current2" );
    }
    
    public static class TestException extends Exception
    {
    }

    public static class TestExceptionRO extends Exception
    {
    }
}
