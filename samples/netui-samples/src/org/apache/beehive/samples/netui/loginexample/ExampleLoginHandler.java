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
package org.apache.beehive.samples.netui.loginexample;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import org.apache.beehive.netui.pageflow.handler.BaseHandler;
import org.apache.beehive.netui.pageflow.handler.LoginHandler;
import org.apache.beehive.netui.pageflow.handler.FlowControllerHandlerContext;

public class ExampleLoginHandler
    extends BaseHandler
    implements LoginHandler {

    public void login(FlowControllerHandlerContext context, String username, String password)
        throws LoginException {
        if (username.equals("good") && password.equals("good")) {
            getSession(context).setAttribute("_principal", new UserPrincipal());
        }
        else throw new LoginException(username);
    }

    public void logout(FlowControllerHandlerContext context, boolean invalidateSessions) {
        getSession(context).removeAttribute("_principal");
    }

    public boolean isUserInRole(FlowControllerHandlerContext context, String roleName) {
        return false;
    }

    public Principal getUserPrincipal(FlowControllerHandlerContext context) {
        return (Principal) getSession(context).getAttribute("_principal");
    }

    private static HttpSession getSession(FlowControllerHandlerContext context) {
        assert context.getRequest() instanceof HttpServletRequest;
        return ((HttpServletRequest)context.getRequest()).getSession();
    }

    private static class UserPrincipal
        implements Principal {

        public String getName() {
            return "good";
        }
    }
}
