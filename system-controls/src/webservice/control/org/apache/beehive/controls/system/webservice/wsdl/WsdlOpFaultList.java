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

package org.apache.beehive.controls.system.webservice.wsdl;

import org.w3c.dom.Element;

import javax.wsdl.Fault;
import javax.wsdl.Message;
import javax.wsdl.Part;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * WsdlOpFaultList represents the list of parameters for a WSDL operaiton.
 */
public final class WsdlOpFaultList extends ArrayList<WsdlOpFault> {

    private final WsdlTypes _wsdlTypes;

    /**
     * Constructor.
     *
     * @param wsdlTypes Type information from the WSDL.
     */
    protected WsdlOpFaultList(WsdlTypes wsdlTypes) {
        _wsdlTypes = wsdlTypes;
    }

    /**
     * Add a fault to this list.
     *
     * @param fault Fault to add.
     * @return true if this collection was modified as a result of this call.
     */
    protected boolean add(Fault fault) {
        return add(getFault(fault));
    }

    /**
     * Create a WsdlOpFault from the Fault.
     *
     * @param fault Fault to process.
     * @return WsdlOpFault Result of processing.
     */
    private WsdlOpFault getFault(Fault fault) {
        Message m = fault.getMessage();

        // a fault should only have one message part.
        Map partMap = m.getParts();
        assert partMap.size() == 1 : "Invalid part count for fault!!";
        Part faultPart = (Part) partMap.values().iterator().next();
        boolean complexType = false;

        // type of fault is specified either in Part's type or element attribute.
        QName type = faultPart.getTypeName();
        if (type == null) {
            type = faultPart.getElementName();
            Element schemaElement = _wsdlTypes.findNamedElement(type);
            assert schemaElement.hasAttribute("type");
            type = _wsdlTypes.getTypeQName(schemaElement.getAttribute("type"));
            complexType = true;
        }
        return new WsdlOpFault(fault.getName(), type, complexType, _wsdlTypes);
    }

    /**
     * private contstructor.
     */
    private WsdlOpFaultList() {
        _wsdlTypes = null;
    }

    /**
     * private constructor.
     *
     * @param capacity
     */
    private WsdlOpFaultList(int capacity) {
        this();
    }

    /**
     * private constructor.
     *
     * @param c
     */
    private WsdlOpFaultList(Collection<WsdlOpParameter> c) {
        this();
    }
}
