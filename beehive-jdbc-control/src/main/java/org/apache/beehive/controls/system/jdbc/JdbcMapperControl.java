package org.apache.beehive.controls.system.jdbc;

import com.moparisthebest.jdbc.codegen.JdbcMapper;

/**
 * This is just a utility class to make it easier to convert your JdbcControls to JdbcMapper
 */
public interface JdbcMapperControl extends JdbcMapper, CloseableControl {
}
