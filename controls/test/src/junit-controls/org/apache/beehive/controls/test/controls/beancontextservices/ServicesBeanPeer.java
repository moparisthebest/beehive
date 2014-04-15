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

package org.apache.beehive.controls.test.controls.beancontextservices;

import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextServices;
import java.beans.beancontext.BeanContextServiceProvider;
import java.beans.beancontext.BeanContextServiceRevokedListener;
import java.beans.beancontext.BeanContextServicesListener;
import java.beans.beancontext.BeanContextMembershipListener;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.util.TooManyListenersException;
import java.util.Iterator;
import java.util.Collection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: cschoett
 * Date: Apr 13, 2006
 * Time: 9:20:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServicesBeanPeer implements BeanContextServices {

    private String _message;

    public String getMessage() { return _message; }
    public void setMessage(String message) { _message = message; }

    public boolean addService(Class serviceClass, BeanContextServiceProvider serviceProvider) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void revokeService(Class serviceClass, BeanContextServiceProvider serviceProvider, boolean revokeCurrentServicesNow) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean hasService(Class serviceClass) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getService(BeanContextChild child, Object requestor, Class serviceClass, Object serviceSelector, BeanContextServiceRevokedListener bcsrl) throws TooManyListenersException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void releaseService(BeanContextChild child, Object requestor, Object service) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterator getCurrentServiceClasses() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterator getCurrentServiceSelectors(Class serviceClass) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addBeanContextServicesListener(BeanContextServicesListener bcsl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeBeanContextServicesListener(BeanContextServicesListener bcsl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object instantiateChild(String beanName) throws IOException, ClassNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public InputStream getResourceAsStream(String name, BeanContextChild bcc) throws IllegalArgumentException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public URL getResource(String name, BeanContextChild bcc) throws IllegalArgumentException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addBeanContextMembershipListener(BeanContextMembershipListener bcml) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeBeanContextMembershipListener(BeanContextMembershipListener bcml) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setBeanContext(BeanContext bc) throws PropertyVetoException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public BeanContext getBeanContext() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addPropertyChangeListener(String name, PropertyChangeListener pcl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removePropertyChangeListener(String name, PropertyChangeListener pcl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addVetoableChangeListener(String name, VetoableChangeListener vcl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeVetoableChangeListener(String name, VetoableChangeListener vcl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int size() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isEmpty() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean contains(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterator iterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object[] toArray() {
        return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean add(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean remove(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean addAll(Collection c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void clear() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean retainAll(Collection c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean removeAll(Collection c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean containsAll(Collection c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object[] toArray(Object[] a) {
        return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setDesignTime(boolean designTime) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isDesignTime() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean needsGui() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void dontUseGui() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void okToUseGui() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean avoidingGui() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void serviceAvailable(BeanContextServiceAvailableEvent bcsae) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void serviceRevoked(BeanContextServiceRevokedEvent bcsre) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
