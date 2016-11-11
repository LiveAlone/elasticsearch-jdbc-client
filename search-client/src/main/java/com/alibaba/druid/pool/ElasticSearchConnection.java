package com.alibaba.druid.pool;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by yaoqijun on 2016/11/10.
 */
public class ElasticSearchConnection implements Connection{

    private Client client;

    public ElasticSearchConnection(String jdbcUrl) {
        String hostAndPort = jdbcUrl.split("/")[2];
        String host = hostAndPort.split(":")[0];
        String port = hostAndPort.split(":")[1];
//        Settings settings = Settings.builder().put("client.transport.ignore_cluster_name", true).build();

        Settings settings = ImmutableSettings.settingsBuilder().put("client.transport.ignore_cluster_name", true)
                .put("client.transport.sniff", true).build();

        Connection conn = null;
        try {
//            client = TransportClient.builder().settings(settings).build().
//                    addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), Integer.parseInt(port)));

            this.client = new TransportClient(settings).addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(host), Integer.parseInt(port)));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }

    public Statement createStatement() throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new PreparedStatement() {
            public ResultSet executeQuery() throws SQLException {
                return null;
            }

            public int executeUpdate() throws SQLException {
                return 0;
            }

            public void setNull(int parameterIndex, int sqlType) throws SQLException {

            }

            public void setBoolean(int parameterIndex, boolean x) throws SQLException {

            }

            public void setByte(int parameterIndex, byte x) throws SQLException {

            }

            public void setShort(int parameterIndex, short x) throws SQLException {

            }

            public void setInt(int parameterIndex, int x) throws SQLException {

            }

            public void setLong(int parameterIndex, long x) throws SQLException {

            }

            public void setFloat(int parameterIndex, float x) throws SQLException {

            }

            public void setDouble(int parameterIndex, double x) throws SQLException {

            }

            public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {

            }

            public void setString(int parameterIndex, String x) throws SQLException {

            }

            public void setBytes(int parameterIndex, byte[] x) throws SQLException {

            }

            public void setDate(int parameterIndex, Date x) throws SQLException {

            }

            public void setTime(int parameterIndex, Time x) throws SQLException {

            }

            public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {

            }

            public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {

            }

            public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {

            }

            public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {

            }

            public void clearParameters() throws SQLException {

            }

            public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {

            }

            public void setObject(int parameterIndex, Object x) throws SQLException {

            }

            public boolean execute() throws SQLException {
                return false;
            }

            public void addBatch() throws SQLException {

            }

            public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {

            }

            public void setRef(int parameterIndex, Ref x) throws SQLException {

            }

            public void setBlob(int parameterIndex, Blob x) throws SQLException {

            }

            public void setClob(int parameterIndex, Clob x) throws SQLException {

            }

            public void setArray(int parameterIndex, Array x) throws SQLException {

            }

            public ResultSetMetaData getMetaData() throws SQLException {
                return null;
            }

            public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {

            }

            public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {

            }

            public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {

            }

            public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {

            }

            public void setURL(int parameterIndex, URL x) throws SQLException {

            }

            public ParameterMetaData getParameterMetaData() throws SQLException {
                return null;
            }

            public void setRowId(int parameterIndex, RowId x) throws SQLException {

            }

            public void setNString(int parameterIndex, String value) throws SQLException {

            }

            public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {

            }

            public void setNClob(int parameterIndex, NClob value) throws SQLException {

            }

            public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {

            }

            public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {

            }

            public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {

            }

            public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {

            }

            public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {

            }

            public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {

            }

            public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {

            }

            public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {

            }

            public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {

            }

            public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {

            }

            public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {

            }

            public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {

            }

            public void setClob(int parameterIndex, Reader reader) throws SQLException {

            }

            public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {

            }

            public void setNClob(int parameterIndex, Reader reader) throws SQLException {

            }

            public ResultSet executeQuery(String sql) throws SQLException {
                return null;
            }

            public int executeUpdate(String sql) throws SQLException {
                return 0;
            }

            public void close() throws SQLException {

            }

            public int getMaxFieldSize() throws SQLException {
                return 0;
            }

            public void setMaxFieldSize(int max) throws SQLException {

            }

            public int getMaxRows() throws SQLException {
                return 0;
            }

            public void setMaxRows(int max) throws SQLException {

            }

            public void setEscapeProcessing(boolean enable) throws SQLException {

            }

            public int getQueryTimeout() throws SQLException {
                return 0;
            }

            public void setQueryTimeout(int seconds) throws SQLException {

            }

            public void cancel() throws SQLException {

            }

            public SQLWarning getWarnings() throws SQLException {
                return null;
            }

            public void clearWarnings() throws SQLException {

            }

            public void setCursorName(String name) throws SQLException {

            }

            public boolean execute(String sql) throws SQLException {
                return false;
            }

            public ResultSet getResultSet() throws SQLException {
                return null;
            }

            public int getUpdateCount() throws SQLException {
                return 0;
            }

            public boolean getMoreResults() throws SQLException {
                return false;
            }

            public void setFetchDirection(int direction) throws SQLException {

            }

            public int getFetchDirection() throws SQLException {
                return 0;
            }

            public void setFetchSize(int rows) throws SQLException {

            }

            public int getFetchSize() throws SQLException {
                return 0;
            }

            public int getResultSetConcurrency() throws SQLException {
                return 0;
            }

            public int getResultSetType() throws SQLException {
                return 0;
            }

            public void addBatch(String sql) throws SQLException {

            }

            public void clearBatch() throws SQLException {

            }

            public int[] executeBatch() throws SQLException {
                return new int[0];
            }

            public Connection getConnection() throws SQLException {
                return null;
            }

            public boolean getMoreResults(int current) throws SQLException {
                return false;
            }

            public ResultSet getGeneratedKeys() throws SQLException {
                return null;
            }

            public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
                return 0;
            }

            public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
                return 0;
            }

            public int executeUpdate(String sql, String[] columnNames) throws SQLException {
                return 0;
            }

            public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
                return false;
            }

            public boolean execute(String sql, int[] columnIndexes) throws SQLException {
                return false;
            }

            public boolean execute(String sql, String[] columnNames) throws SQLException {
                return false;
            }

            public int getResultSetHoldability() throws SQLException {
                return 0;
            }

            public boolean isClosed() throws SQLException {
                return false;
            }

            public void setPoolable(boolean poolable) throws SQLException {

            }

            public boolean isPoolable() throws SQLException {
                return false;
            }

            public void closeOnCompletion() throws SQLException {

            }

            public boolean isCloseOnCompletion() throws SQLException {
                return false;
            }

            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;
            }

            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }
        };
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return null;
    }

    public String nativeSQL(String sql) throws SQLException {
        return null;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {

    }

    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    public void commit() throws SQLException {

    }

    public void rollback() throws SQLException {

    }

    public void close() throws SQLException {

    }

    public boolean isClosed() throws SQLException {
        return false;
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return new DatabaseMetaData() {
            @Override
            public boolean allProceduresAreCallable() throws SQLException {
                return false;
            }

            @Override
            public boolean allTablesAreSelectable() throws SQLException {
                return false;
            }

            @Override
            public String getURL() throws SQLException {
                return null;
            }

            @Override
            public String getUserName() throws SQLException {
                return null;
            }

            @Override
            public boolean isReadOnly() throws SQLException {
                return false;
            }

            @Override
            public boolean nullsAreSortedHigh() throws SQLException {
                return false;
            }

            @Override
            public boolean nullsAreSortedLow() throws SQLException {
                return false;
            }

            @Override
            public boolean nullsAreSortedAtStart() throws SQLException {
                return false;
            }

            @Override
            public boolean nullsAreSortedAtEnd() throws SQLException {
                return false;
            }

            @Override
            public String getDatabaseProductName() throws SQLException {
                return "elasticsearch";
            }

            @Override
            public String getDatabaseProductVersion() throws SQLException {
                return null;
            }

            @Override
            public String getDriverName() throws SQLException {
                return null;
            }

            @Override
            public String getDriverVersion() throws SQLException {
                return null;
            }

            @Override
            public int getDriverMajorVersion() {
                return 0;
            }

            @Override
            public int getDriverMinorVersion() {
                return 0;
            }

            @Override
            public boolean usesLocalFiles() throws SQLException {
                return false;
            }

            @Override
            public boolean usesLocalFilePerTable() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsMixedCaseIdentifiers() throws SQLException {
                return false;
            }

            @Override
            public boolean storesUpperCaseIdentifiers() throws SQLException {
                return false;
            }

            @Override
            public boolean storesLowerCaseIdentifiers() throws SQLException {
                return false;
            }

            @Override
            public boolean storesMixedCaseIdentifiers() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
                return false;
            }

            @Override
            public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
                return false;
            }

            @Override
            public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
                return false;
            }

            @Override
            public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
                return false;
            }

            @Override
            public String getIdentifierQuoteString() throws SQLException {
                return null;
            }

            @Override
            public String getSQLKeywords() throws SQLException {
                return null;
            }

            @Override
            public String getNumericFunctions() throws SQLException {
                return null;
            }

            @Override
            public String getStringFunctions() throws SQLException {
                return null;
            }

            @Override
            public String getSystemFunctions() throws SQLException {
                return null;
            }

            @Override
            public String getTimeDateFunctions() throws SQLException {
                return null;
            }

            @Override
            public String getSearchStringEscape() throws SQLException {
                return null;
            }

            @Override
            public String getExtraNameCharacters() throws SQLException {
                return null;
            }

            @Override
            public boolean supportsAlterTableWithAddColumn() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsAlterTableWithDropColumn() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsColumnAliasing() throws SQLException {
                return false;
            }

            @Override
            public boolean nullPlusNonNullIsNull() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsConvert() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsConvert(int fromType, int toType) throws SQLException {
                return false;
            }

            @Override
            public boolean supportsTableCorrelationNames() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsDifferentTableCorrelationNames() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsExpressionsInOrderBy() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsOrderByUnrelated() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsGroupBy() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsGroupByUnrelated() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsGroupByBeyondSelect() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsLikeEscapeClause() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsMultipleResultSets() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsMultipleTransactions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsNonNullableColumns() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsMinimumSQLGrammar() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsCoreSQLGrammar() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsExtendedSQLGrammar() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsANSI92EntryLevelSQL() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsANSI92IntermediateSQL() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsANSI92FullSQL() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsIntegrityEnhancementFacility() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsOuterJoins() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsFullOuterJoins() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsLimitedOuterJoins() throws SQLException {
                return false;
            }

            @Override
            public String getSchemaTerm() throws SQLException {
                return null;
            }

            @Override
            public String getProcedureTerm() throws SQLException {
                return null;
            }

            @Override
            public String getCatalogTerm() throws SQLException {
                return null;
            }

            @Override
            public boolean isCatalogAtStart() throws SQLException {
                return false;
            }

            @Override
            public String getCatalogSeparator() throws SQLException {
                return null;
            }

            @Override
            public boolean supportsSchemasInDataManipulation() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSchemasInProcedureCalls() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSchemasInTableDefinitions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSchemasInIndexDefinitions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsCatalogsInDataManipulation() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsCatalogsInProcedureCalls() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsCatalogsInTableDefinitions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsPositionedDelete() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsPositionedUpdate() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSelectForUpdate() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsStoredProcedures() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSubqueriesInComparisons() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSubqueriesInExists() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSubqueriesInIns() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsSubqueriesInQuantifieds() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsCorrelatedSubqueries() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsUnion() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsUnionAll() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
                return false;
            }

            @Override
            public int getMaxBinaryLiteralLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxCharLiteralLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxColumnNameLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxColumnsInGroupBy() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxColumnsInIndex() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxColumnsInOrderBy() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxColumnsInSelect() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxColumnsInTable() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxConnections() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxCursorNameLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxIndexLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxSchemaNameLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxProcedureNameLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxCatalogNameLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxRowSize() throws SQLException {
                return 0;
            }

            @Override
            public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
                return false;
            }

            @Override
            public int getMaxStatementLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxStatements() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxTableNameLength() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxTablesInSelect() throws SQLException {
                return 0;
            }

            @Override
            public int getMaxUserNameLength() throws SQLException {
                return 0;
            }

            @Override
            public int getDefaultTransactionIsolation() throws SQLException {
                return 0;
            }

            @Override
            public boolean supportsTransactions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
                return false;
            }

            @Override
            public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
                return false;
            }

            @Override
            public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
                return false;
            }

            @Override
            public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
                return false;
            }

            @Override
            public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getSchemas() throws SQLException {
                return null;
            }

            @Override
            public ResultSet getCatalogs() throws SQLException {
                return null;
            }

            @Override
            public ResultSet getTableTypes() throws SQLException {
                return null;
            }

            @Override
            public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getTypeInfo() throws SQLException {
                return null;
            }

            @Override
            public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
                return null;
            }

            @Override
            public boolean supportsResultSetType(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
                return false;
            }

            @Override
            public boolean ownUpdatesAreVisible(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean ownDeletesAreVisible(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean ownInsertsAreVisible(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean othersUpdatesAreVisible(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean othersDeletesAreVisible(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean othersInsertsAreVisible(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean updatesAreDetected(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean deletesAreDetected(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean insertsAreDetected(int type) throws SQLException {
                return false;
            }

            @Override
            public boolean supportsBatchUpdates() throws SQLException {
                return false;
            }

            @Override
            public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
                return null;
            }

            @Override
            public Connection getConnection() throws SQLException {
                return null;
            }

            @Override
            public boolean supportsSavepoints() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsNamedParameters() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsMultipleOpenResults() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsGetGeneratedKeys() throws SQLException {
                return false;
            }

            @Override
            public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
                return null;
            }

            @Override
            public boolean supportsResultSetHoldability(int holdability) throws SQLException {
                return false;
            }

            @Override
            public int getResultSetHoldability() throws SQLException {
                return 0;
            }

            @Override
            public int getDatabaseMajorVersion() throws SQLException {
                return 0;
            }

            @Override
            public int getDatabaseMinorVersion() throws SQLException {
                return 0;
            }

            @Override
            public int getJDBCMajorVersion() throws SQLException {
                return 0;
            }

            @Override
            public int getJDBCMinorVersion() throws SQLException {
                return 0;
            }

            @Override
            public int getSQLStateType() throws SQLException {
                return 0;
            }

            @Override
            public boolean locatorsUpdateCopy() throws SQLException {
                return false;
            }

            @Override
            public boolean supportsStatementPooling() throws SQLException {
                return false;
            }

            @Override
            public RowIdLifetime getRowIdLifetime() throws SQLException {
                return null;
            }

            @Override
            public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
                return null;
            }

            @Override
            public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
                return false;
            }

            @Override
            public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
                return false;
            }

            @Override
            public ResultSet getClientInfoProperties() throws SQLException {
                return null;
            }

            @Override
            public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
                return null;
            }

            @Override
            public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
                return null;
            }

            @Override
            public boolean generatedKeyAlwaysReturned() throws SQLException {
                return false;
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }
        };
    }

    public void setReadOnly(boolean readOnly) throws SQLException {

    }

    public boolean isReadOnly() throws SQLException {
        return false;
    }

    public void setCatalog(String catalog) throws SQLException {

    }

    public String getCatalog() throws SQLException {
        return null;
    }

    public void setTransactionIsolation(int level) throws SQLException {

    }

    public int getTransactionIsolation() throws SQLException {
        return 0;
    }

    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    public void clearWarnings() throws SQLException {

    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return null;
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

    }

    public void setHoldability(int holdability) throws SQLException {

    }

    public int getHoldability() throws SQLException {
        return 0;
    }

    public Savepoint setSavepoint() throws SQLException {
        return null;
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return null;
    }

    public void rollback(Savepoint savepoint) throws SQLException {

    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {

    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return null;
    }

    public Clob createClob() throws SQLException {
        return null;
    }

    public Blob createBlob() throws SQLException {
        return null;
    }

    public NClob createNClob() throws SQLException {
        return null;
    }

    public SQLXML createSQLXML() throws SQLException {
        return null;
    }

    public boolean isValid(int timeout) throws SQLException {
        return false;
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {

    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {

    }

    public String getClientInfo(String name) throws SQLException {
        return null;
    }

    public Properties getClientInfo() throws SQLException {
        return null;
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return null;
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return null;
    }

    public void setSchema(String schema) throws SQLException {

    }

    public String getSchema() throws SQLException {
        return null;
    }

    public void abort(Executor executor) throws SQLException {

    }

    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

    }

    public int getNetworkTimeout() throws SQLException {
        return 0;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
