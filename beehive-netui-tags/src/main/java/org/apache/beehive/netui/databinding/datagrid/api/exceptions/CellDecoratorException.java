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
package org.apache.beehive.netui.databinding.datagrid.api.exceptions;

/**
 * Exception thrown when an error occurs while rendering a
 * {@link org.apache.beehive.netui.databinding.datagrid.api.rendering.CellDecorator}.
 */
public class CellDecoratorException
        extends RuntimeException {

    /**
     * Default constructor
     */
    public CellDecoratorException() {
        super();
    }

    /**
     * Constructor with cause message
     *
     * @param message
     */
    public CellDecoratorException(String message) {
        super(message);
    }

    /**
     * Constructor with cause
     *
     * @param cause
     */
    public CellDecoratorException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause
     *
     * @param message
     * @param cause
     */
    public CellDecoratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
