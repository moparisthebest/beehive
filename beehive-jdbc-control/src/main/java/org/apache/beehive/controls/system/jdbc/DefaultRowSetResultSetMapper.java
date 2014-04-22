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

import com.sun.rowset.CachedRowSetImpl;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.system.jdbc.JdbcControl.SQL;

import javax.sql.RowSet;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Default ResultSetMapper implementation for RowSets.
 */
public class DefaultRowSetResultSetMapper extends AbstractResultSetMapper {

    /**
     * Map a ResultSet to a RowSet.  Type of RowSet is defined by the SQL annotation for the method.
     *
     * @param context   A ControlBeanContext instance.
     * @param m         Method assoicated with this call.
     * @param resultSet Result set to map.
     * @param cal       A Calendar instance for resolving date/time values.
     * @return A RowSet object.
     */
    public RowSet mapToResultType(ControlBeanContext context, Method m, ResultSet resultSet, Calendar cal) {
        final SQL methodSQL = (SQL) context.getMethodPropertySet(m, SQL.class);
        final int maxrows = methodSQL.maxRows();

        try {
            CachedRowSetImpl rows = new CachedRowSetImpl();

            if (maxrows > 0) {
                rows.setMaxRows(maxrows);
            }

            rows.populate(resultSet);
            return rows;
        } catch (SQLException e) {
            throw new ControlException(e.getMessage(), e);
        }
    }

    /**
     * Can the ResultSet which this mapper uses be closed by the database control?
     *
     * @return always false
     */
    public boolean canCloseResultSet() { return false; }
}
