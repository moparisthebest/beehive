package org.apache.beehive.controls.system.jdbc;

import org.apache.beehive.controls.api.context.ControlBeanContext;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.*;

/**
 * Refer to org.apache.beehive.controls.system.jdbc.ResultSetMapper for how this class operates
 */
public class NewDefaultObjectResultSetMapper extends com.moparisthebest.jdbc.CaseInsensitiveMapResultSetMapper implements org.apache.beehive.controls.system.jdbc.ResultSetMapper {
	public NewDefaultObjectResultSetMapper() {
		super(-1);
	}

	/**
	 * Map the ResultSet to the method's return type. The object type returned is defined by the return type of the method.
	 *
	 * @param context A ControlBeanContext instance, see Beehive controls javadoc for additional information
	 * @param m       Method assoicated with this call.
	 * @param rs      Result set to map.
	 * @param cal     A Calendar instance for time/date value resolution.
	 * @return The Object resulting from the ResultSet
	 */
	@SuppressWarnings({"unchecked"})
	public Object mapToResultType(ControlBeanContext context, Method m, ResultSet rs, Calendar cal) {
		final Type type = m.getGenericReturnType();
		// todo: is genericArray: false correct here? good enough I guess...
		return toType(rs, m.getReturnType(), type instanceof ParameterizedType ? (ParameterizedType) type : null, false, context.getMethodPropertySet(m, JdbcControl.SQL.class).arrayMaxLength(), cal);
	}

	// todo: true is probably not valid for Stream and ResultSetIterable
	public boolean canCloseResultSet() {
		return true;
	}
}
