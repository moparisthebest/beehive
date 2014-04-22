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

package org.apache.beehive.controls.system.jdbc;

import org.apache.beehive.controls.api.context.ControlBeanContext;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Default ResultSetMapper implementation for Iterators.
 */
public class DefaultIteratorResultSetMapper extends AbstractResultSetMapper {

    /**
     * Map a ResultSet to an object type
     * Type of object to interate over is defined in the SQL annotation for the method.
     *
     * @param context   A ControlBeanContext instance, see Beehive controls javadoc for additional information
     * @param m         Method assoicated with this call.
     * @param resultSet Result set to map.
     * @param cal       A Calendar instance for time/date value resolution.
     * @return          The Iterator object instance resulting from the ResultSet
     */
    public Iterator mapToResultType(ControlBeanContext context, Method m, ResultSet resultSet, Calendar cal) {
        return new ResultSetIterator(context, m, resultSet, cal);
    }

    /**
     * Can the ResultSet which this mapper uses be closed by the database control on return from its invoke() method?
     * @return always returns false
     */
    public boolean canCloseResultSet() { return false; }
}
