package com.moparisthebest.jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import static com.moparisthebest.jdbc.TryClose.tryClose;

public class QueryMapper implements Closeable {

	static {
		try{
			final Class<?> ensureContext = Class.forName(System.getProperty("QueryMapper.ensureContext.class", "com.gcl.containerless.EnsureContext"));
			final Method method = ensureContext.getMethod(System.getProperty("QueryMapper.ensureContext.method", "setup"));
			method.invoke(null);
		}catch(Throwable e){
			// ignore
			e.printStackTrace();
		}
	}

	protected final ResultSetMapper cm;
	protected final Connection conn;
	protected final Context context;

	protected QueryMapper(Connection conn, String jndiName, ResultSetMapper cm) {
		this.cm = cm == null ? new ResultSetMapper() : cm;
		Context context = null;
		if (conn == null && jndiName != null)
                    try {
                        context = new InitialContext();
                        DataSource ds = (DataSource) context.lookup(jndiName);
                        conn = ds.getConnection();
                    } catch (Throwable e) {
                        e.printStackTrace();
                        tryClose(conn);
                        tryClose(context);
                    }
		this.conn = conn;
		this.context = context;
		if (this.conn == null)
			throw new NullPointerException("Connection needs to be non-null for QueryMapper...");
	}

	public QueryMapper(Connection conn, ResultSetMapper cm) {
		this(conn, null, cm);
	}

	public QueryMapper(Connection conn) {
		this(conn, null);
	}

	public QueryMapper(String jndiName, ResultSetMapper cm) {
		this(null, jndiName, cm);
	}

	public QueryMapper(String jndiName) {
		this(jndiName, null);
	}

	/**
	 * Only meant to be called by implementing classes
	 */
	protected QueryMapper() {
		this.cm = null;
		this.conn = null;
		this.context = null;
	}

	@Override
	public void close() {
		if (context != null) {
			tryClose(conn);
			tryClose(context);
		}
	}

	private static class StringWrapper {
		public final String s;

		private StringWrapper(String s) {
			this.s = s;
		}

		public String toString() {
			return s;
		}

		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof StringWrapper)) return false;
			StringWrapper that = (StringWrapper) o;
			return !(s != null ? !s.equals(that.s) : that.s != null);
		}

		public int hashCode() {
			return s != null ? s.hashCode() : 0;
		}
	}

	private static class ClobString extends StringWrapper {
		private ClobString(String s) {
			super(s);
		}
	}

	private static class BlobString extends StringWrapper {
		private BlobString(String s) {
			super(s);
		}
	}

	public static Object wrapClob(String s) {
		return new ClobString(s);
	}

	public static Object wrapBlob(String s) {
		return new BlobString(s);
	}

	public static void setObject(final PreparedStatement ps, final int index, final Object o) throws SQLException {
		// we are going to put most common ones up top so it should execute faster normally
		if (o == null || o instanceof String || o instanceof Number)
			ps.setObject(index, o);
			// java.util.Date support, put it in a Timestamp
		else if (o instanceof java.util.Date)
			ps.setObject(index, o.getClass().equals(java.util.Date.class) ? new java.sql.Timestamp(((java.util.Date)o).getTime()) : o);
			// CLOB support
		else if (o instanceof Reader)
			ps.setClob(index, (Reader) o);
		else if (o instanceof ClobString)
			ps.setObject(index, ((ClobString) o).s == null ? null : ((ClobString) o).s);
		else if (o instanceof Clob)
			ps.setClob(index, (Clob) o);
			// BLOB support
		else if (o instanceof byte[])
			ps.setBlob(index, new ByteArrayInputStream((byte[]) o));
		else if (o instanceof InputStream)
			ps.setBlob(index, (InputStream) o);
		else if (o instanceof File)
			try {
				ps.setBlob(index, new FileInputStream((File) o));
			} catch (FileNotFoundException e) {
				throw new SQLException("File to Blob FileNotFoundException", e);
			}
		else if (o instanceof BlobString)
			try {
				ps.setBlob(index, ((BlobString) o).s == null ? null : new ByteArrayInputStream(((BlobString) o).s.getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				throw new SQLException("String to Blob UnsupportedEncodingException", e);
			}
		else if (o instanceof Blob)
			ps.setBlob(index, (Blob) o);
		else
			ps.setObject(index, o); // probably won't get here ever, but just in case...
		/*
		switch(ps.getParameterMetaData().getParameterType(index)){ // 'java.sql.SQLException: Unsupported feature', fully JDBC 3.0 compliant my ass, freaking oracle...
			case Types.CLOB:
				if(o instanceof String)
					ps.setObject(index, o);
				else if (o instanceof Reader)
					ps.setClob(index, (Reader) o);
				else if (o instanceof Clob)
					ps.setClob(index, (Clob) o);
				return;
			case Types.BLOB:
				if (o instanceof byte[])
					ps.setBlob(index, new ByteArrayInputStream((byte[])o));
				else if (o instanceof InputStream)
					ps.setBlob(index, (InputStream) o);
				else if (o instanceof File)
					try {
						ps.setBlob(index, new FileInputStream((File) o));
					} catch (FileNotFoundException e) {
						throw new SQLException("File to Blob FileNotFoundException", e);
					}
				else if (o instanceof Blob)
					ps.setBlob(index, (Blob) o);
				else if(o instanceof String)
					try{
						ps.setBlob(index, new ByteArrayInputStream(((String) o).getBytes("UTF-8")));
					}catch(UnsupportedEncodingException e){
						throw new SQLException("String to Blob UnsupportedEncodingException", e);
					}
				return;
			default:
				ps.setObject(index, o);
		}
		*/
	}

	public static int recursiveBind(final PreparedStatement ps, final Object... bindObjects) throws SQLException {
		return recursiveBind(ps, 0, bindObjects);
	}

	private static int recursiveBind(final PreparedStatement ps, int index, final Object... bindObjects) throws SQLException {
		if (bindObjects != null && bindObjects.length > 0) {
			for (Object o : bindObjects) {
				if (o != null) {
					if (o instanceof Object[]) {
						index = recursiveBind(ps, index, (Object[]) o);
						continue;
					} else if (o instanceof Collection) {
						index = recursiveBind(ps, index, ((Collection) o).toArray());
						continue;
					}
				}
				//System.out.printf("index: '%d' bound to '%s'\n", index+1, o);
				setObject(ps, ++index, o);
				//ps.setObject(++index, o);
			}
		}
		return index;
	}

	public static PreparedStatement bindStatement(final PreparedStatement ps, final Object... bindObjects) throws SQLException {
		recursiveBind(ps, bindObjects);
		return ps;
	}

	protected PreparedStatement bind(final PreparedStatement ps, final Object... bindObjects) throws SQLException {
		return bindStatement(ps, bindObjects);
	}

	protected ResultSet bindExecute(final PreparedStatement ps, final Object... bindObjects) throws SQLException {
		return bind(ps, bindObjects).executeQuery();
	}

	// these update the database

	public int executeUpdate(PreparedStatement ps, final Object... bindObjects) throws SQLException {
		return bind(ps, bindObjects).executeUpdate();
	}

	public boolean executeUpdateSuccess(PreparedStatement ps, final Object... bindObjects) throws SQLException {
		return this.executeUpdate(ps, bindObjects) >= 0;
	}

	public int executeUpdate(String sql, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.executeUpdate(ps, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public boolean executeUpdateSuccess(String sql, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.executeUpdateSuccess(ps, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	// these update the database using UpdateableDTOs

	public int updateRows(UpdateableDTO dto) throws SQLException {
		return dto.updateRow(this);
	}

	public int updateRows(Collection<UpdateableDTO> dtos) throws SQLException {
		int count = 0;
		if (dtos != null)
			for (UpdateableDTO dto : dtos)
				count += dto.updateRow(this);
		return count;
	}

	public int updateRows(UpdateableDTO[] dtos) throws SQLException {
		return updateRows(Arrays.asList(dtos));
	}

	public int insertRows(UpdateableDTO dto) throws SQLException {
		return dto.insertRow(this);
	}

	public int insertRows(Collection<UpdateableDTO> dtos) throws SQLException {
		int count = 0;
		if (dtos != null)
			for (UpdateableDTO dto : dtos)
				count += dto.insertRow(this);
		return count;
	}

	public int insertRows(UpdateableDTO[] dtos) throws SQLException {
		return insertRows(Arrays.asList(dtos));
	}

	// these grab ResultSets from the database

	public ResultSet toResultSet(PreparedStatement ps, final Object... bindObjects) throws SQLException {
		return toResultSet(ps, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, bindObjects);
	}
	
	public ResultSet toResultSet(PreparedStatement ps, int rsType, int rsConcurrency, final Object... bindObjects) throws SQLException {
		return bindExecute(ps, bindObjects);
	}

	public ResultSet toResultSet(String sql, final Object... bindObjects) throws SQLException {
		return toResultSet(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, bindObjects);
	}
	
	public ResultSet toResultSet(String sql, Integer rsType, Integer rsConcurrency, final Object... bindObjects) throws SQLException {
		//throw new UnsupportedOperationException("Can't return ResultSet from String because the PreparedStatement can't be closed before the ResultSet is, so CachingQueryMapper will work.");
		// works with StatementClosingResultSet
		boolean error = true;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql, rsType.intValue(), rsConcurrency.intValue());
			rs = this.toResultSet(ps, bindObjects);
			error = false;
			return new StatementClosingResultSet(rs, ps);
		} finally {
			if (error) {
				tryClose(rs);
				tryClose(ps);
			}
		}
	}

	// these are standard getters

	public ResultSetMapper getCustomResultSetMapper() {
		return cm;
	}

	public Connection getConnection() {
		return conn;
	}

	// DO NOT EDIT BELOW THIS LINE, OR CHANGE THIS COMMENT, CODE AUTOMATICALLY GENERATED BY genQueryMapper.sh

	public <T> T toObject(PreparedStatement ps, Class<T> componentType, final Object... bindObjects) throws SQLException {
		return cm.toObject(bindExecute(ps, bindObjects), componentType);
	}

	public <T> T toObject(String sql, Class<T> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toObject(ps, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<String, V>, V> Map<String, V> toSingleMap(PreparedStatement ps, Class<T> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toSingleMap(bindExecute(ps, bindObjects), componentType, mapValType);
	}

	public <T extends Map<String, V>, V> Map<String, V> toSingleMap(String sql, Class<T> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toSingleMap(ps, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <V> Map<String, V> toSingleMap(PreparedStatement ps, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toSingleMap(bindExecute(ps, bindObjects), mapValType);
	}

	public <V> Map<String, V> toSingleMap(String sql, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toSingleMap(ps, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Collection<E>, E> T toCollection(PreparedStatement ps, final Class<T> collectionType, Class<E> componentType, final Object... bindObjects) throws SQLException {
		return cm.toCollection(bindExecute(ps, bindObjects), collectionType, componentType);
	}

	public <T extends Collection<E>, E> T toCollection(String sql, final Class<T> collectionType, Class<E> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toCollection(ps, collectionType, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Collection<E>, E> T toCollection(PreparedStatement ps, T list, Class<E> componentType, final Object... bindObjects) throws SQLException {
		return cm.toCollection(bindExecute(ps, bindObjects), list, componentType);
	}

	public <T extends Collection<E>, E> T toCollection(String sql, T list, Class<E> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toCollection(ps, list, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<K, E>, K, E> T toMap(PreparedStatement ps, final Class<T> returnType, Class<K> mapKeyType, Class<E> componentType, final Object... bindObjects) throws SQLException {
		return cm.toMap(bindExecute(ps, bindObjects), returnType, mapKeyType, componentType);
	}

	public <T extends Map<K, E>, K, E> T toMap(String sql, final Class<T> returnType, Class<K> mapKeyType, Class<E> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMap(ps, returnType, mapKeyType, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<K, E>, K, E> T toMap(PreparedStatement ps, T map, Class<K> mapKeyType, Class<E> componentType, final Object... bindObjects) throws SQLException {
		return cm.toMap(bindExecute(ps, bindObjects), map, mapKeyType, componentType);
	}

	public <T extends Map<K, E>, K, E> T toMap(String sql, T map, Class<K> mapKeyType, Class<E> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMap(ps, map, mapKeyType, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<K, E>, K, E extends Collection<C>, C> T toMapCollection(PreparedStatement ps, final Class<T> returnType, Class<K> mapKeyType, Class<E> collectionType, Class<C> componentType, final Object... bindObjects) throws SQLException {
		return cm.toMapCollection(bindExecute(ps, bindObjects), returnType, mapKeyType, collectionType, componentType);
	}

	public <T extends Map<K, E>, K, E extends Collection<C>, C> T toMapCollection(String sql, final Class<T> returnType, Class<K> mapKeyType, Class<E> collectionType, Class<C> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapCollection(ps, returnType, mapKeyType, collectionType, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<K, E>, K, E extends Collection<C>, C> T toMapCollection(PreparedStatement ps, T map, Class<K> mapKeyType, Class<E> collectionType, Class<C> componentType, final Object... bindObjects) throws SQLException {
		return cm.toMapCollection(bindExecute(ps, bindObjects), map, mapKeyType, collectionType, componentType);
	}

	public <T extends Map<K, E>, K, E extends Collection<C>, C> T toMapCollection(String sql, T map, Class<K> mapKeyType, Class<E> collectionType, Class<C> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapCollection(ps, map, mapKeyType, collectionType, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T> ListIterator<T> toListIterator(PreparedStatement ps, final Class<T> type, final Object... bindObjects) throws SQLException {
		return cm.toListIterator(bindExecute(ps, bindObjects), type);
	}

	public <T> ListIterator<T> toListIterator(String sql, final Class<T> type, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toListIterator(ps, type, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T> Iterator<T> toIterator(PreparedStatement ps, final Class<T> type, final Object... bindObjects) throws SQLException {
		return cm.toIterator(bindExecute(ps, bindObjects), type);
	}

	public <T> Iterator<T> toIterator(String sql, final Class<T> type, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toIterator(ps, type, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T> T[] toArray(PreparedStatement ps, final Class<T> type, final Object... bindObjects) throws SQLException {
		return cm.toArray(bindExecute(ps, bindObjects), type);
	}

	public <T> T[] toArray(String sql, final Class<T> type, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toArray(ps, type, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <E> List<E> toList(PreparedStatement ps, Class<E> componentType, final Object... bindObjects) throws SQLException {
		return cm.toList(bindExecute(ps, bindObjects), componentType);
	}

	public <E> List<E> toList(String sql, Class<E> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toList(ps, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <K, E> Map<K, E> toMap(PreparedStatement ps, Class<K> mapKeyType, Class<E> componentType, final Object... bindObjects) throws SQLException {
		return cm.toMap(bindExecute(ps, bindObjects), mapKeyType, componentType);
	}

	public <K, E> Map<K, E> toMap(String sql, Class<K> mapKeyType, Class<E> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMap(ps, mapKeyType, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <K, E extends List<C>, C> Map<K, E> toMapList(PreparedStatement ps, Class<K> mapKeyType, Class<C> componentType, final Object... bindObjects) throws SQLException {
		return cm.toMapList(bindExecute(ps, bindObjects), mapKeyType, componentType);
	}

	public <K, E extends List<C>, C> Map<K, E> toMapList(String sql, Class<K> mapKeyType, Class<C> componentType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapList(ps, mapKeyType, componentType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Collection<E>, E extends Map<String, V>, V> T toCollectionMap(PreparedStatement ps, final Class<T> collectionType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toCollectionMap(bindExecute(ps, bindObjects), collectionType, componentType, mapValType);
	}

	public <T extends Collection<E>, E extends Map<String, V>, V> T toCollectionMap(String sql, final Class<T> collectionType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toCollectionMap(ps, collectionType, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Collection<E>, E extends Map<String, V>, V> T toCollectionMap(PreparedStatement ps, T list, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toCollectionMap(bindExecute(ps, bindObjects), list, componentType, mapValType);
	}

	public <T extends Collection<E>, E extends Map<String, V>, V> T toCollectionMap(String sql, T list, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toCollectionMap(ps, list, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<K, E>, K, E extends Map<String, V>, V> T toMapMap(PreparedStatement ps, final Class<T> returnType, Class<K> mapKeyType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toMapMap(bindExecute(ps, bindObjects), returnType, mapKeyType, componentType, mapValType);
	}

	public <T extends Map<K, E>, K, E extends Map<String, V>, V> T toMapMap(String sql, final Class<T> returnType, Class<K> mapKeyType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapMap(ps, returnType, mapKeyType, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<K, E>, K, E extends Map<String, V>, V> T toMapMap(PreparedStatement ps, T map, Class<K> mapKeyType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toMapMap(bindExecute(ps, bindObjects), map, mapKeyType, componentType, mapValType);
	}

	public <T extends Map<K, E>, K, E extends Map<String, V>, V> T toMapMap(String sql, T map, Class<K> mapKeyType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapMap(ps, map, mapKeyType, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<K, C>, K, C extends Collection<E>, E extends Map<String, V>, V> T toMapCollectionMap(PreparedStatement ps, final Class<T> returnType, Class<K> mapKeyType, Class<C> collectionType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toMapCollectionMap(bindExecute(ps, bindObjects), returnType, mapKeyType, collectionType, componentType, mapValType);
	}

	public <T extends Map<K, C>, K, C extends Collection<E>, E extends Map<String, V>, V> T toMapCollectionMap(String sql, final Class<T> returnType, Class<K> mapKeyType, Class<C> collectionType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapCollectionMap(ps, returnType, mapKeyType, collectionType, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<K, C>, K, C extends Collection<E>, E extends Map<String, V>, V> T toMapCollectionMap(PreparedStatement ps, T map, Class<K> mapKeyType, Class<C> collectionType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toMapCollectionMap(bindExecute(ps, bindObjects), map, mapKeyType, collectionType, componentType, mapValType);
	}

	public <T extends Map<K, C>, K, C extends Collection<E>, E extends Map<String, V>, V> T toMapCollectionMap(String sql, T map, Class<K> mapKeyType, Class<C> collectionType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapCollectionMap(ps, map, mapKeyType, collectionType, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<String, V>, V> ListIterator<Map<String, V>> toListIteratorMap(PreparedStatement ps, final Class<T> type, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toListIteratorMap(bindExecute(ps, bindObjects), type, mapValType);
	}

	public <T extends Map<String, V>, V> ListIterator<Map<String, V>> toListIteratorMap(String sql, final Class<T> type, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toListIteratorMap(ps, type, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<String, V>, V> Iterator<Map<String, V>> toIteratorMap(PreparedStatement ps, final Class<T> type, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toIteratorMap(bindExecute(ps, bindObjects), type, mapValType);
	}

	public <T extends Map<String, V>, V> Iterator<Map<String, V>> toIteratorMap(String sql, final Class<T> type, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toIteratorMap(ps, type, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <T extends Map<String, V>, V> Map<String, V>[] toArrayMap(PreparedStatement ps, final Class<T> type, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toArrayMap(bindExecute(ps, bindObjects), type, mapValType);
	}

	public <T extends Map<String, V>, V> Map<String, V>[] toArrayMap(String sql, final Class<T> type, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toArrayMap(ps, type, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <E extends Map<String, V>, V> List<Map<String, V>> toListMap(PreparedStatement ps, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toListMap(bindExecute(ps, bindObjects), componentType, mapValType);
	}

	public <E extends Map<String, V>, V> List<Map<String, V>> toListMap(String sql, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toListMap(ps, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <K, E extends Map<String, V>, V> Map<K, Map<String, V>> toMapMap(PreparedStatement ps, Class<K> mapKeyType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toMapMap(bindExecute(ps, bindObjects), mapKeyType, componentType, mapValType);
	}

	public <K, E extends Map<String, V>, V> Map<K, Map<String, V>> toMapMap(String sql, Class<K> mapKeyType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapMap(ps, mapKeyType, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <K, E extends Map<String, V>, V> Map<K, List<Map<String, V>>> toMapListMap(PreparedStatement ps, Class<K> mapKeyType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toMapListMap(bindExecute(ps, bindObjects), mapKeyType, componentType, mapValType);
	}

	public <K, E extends Map<String, V>, V> Map<K, List<Map<String, V>>> toMapListMap(String sql, Class<K> mapKeyType, Class<E> componentType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapListMap(ps, mapKeyType, componentType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <V> ListIterator<Map<String, V>> toListIteratorMap(PreparedStatement ps, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toListIteratorMap(bindExecute(ps, bindObjects), mapValType);
	}

	public <V> ListIterator<Map<String, V>> toListIteratorMap(String sql, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toListIteratorMap(ps, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <V> Iterator<Map<String, V>> toIteratorMap(PreparedStatement ps, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toIteratorMap(bindExecute(ps, bindObjects), mapValType);
	}

	public <V> Iterator<Map<String, V>> toIteratorMap(String sql, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toIteratorMap(ps, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <V> List<Map<String, V>> toListMap(PreparedStatement ps, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toListMap(bindExecute(ps, bindObjects), mapValType);
	}

	public <V> List<Map<String, V>> toListMap(String sql, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toListMap(ps, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <K, V> Map<K, Map<String, V>> toMapMap(PreparedStatement ps, Class<K> mapKeyType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toMapMap(bindExecute(ps, bindObjects), mapKeyType, mapValType);
	}

	public <K, V> Map<K, Map<String, V>> toMapMap(String sql, Class<K> mapKeyType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapMap(ps, mapKeyType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

	public <K, V> Map<K, List<Map<String, V>>> toMapListMap(PreparedStatement ps, Class<K> mapKeyType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		return cm.toMapListMap(bindExecute(ps, bindObjects), mapKeyType, mapValType);
	}

	public <K, V> Map<K, List<Map<String, V>>> toMapListMap(String sql, Class<K> mapKeyType, Class<V> mapValType, final Object... bindObjects) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			return this.toMapListMap(ps, mapKeyType, mapValType, bindObjects);
		} finally {
			tryClose(ps);
		}
	}

}
