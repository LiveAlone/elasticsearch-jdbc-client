package com.alibaba.druid.pool;

import com.alibaba.druid.pool.result.ObjectResult;
import com.alibaba.druid.pool.result.ObjectResultsExtractor;
import com.alibaba.druid.util.jdbc.PreparedStatementBase;
import com.alibaba.druid.wall.violation.IllegalSQLObjectViolation;
import org.elasticsearch.client.Client;
import org.elasticsearch.plugin.nlpcn.QueryActionElasticExecutor;
import org.elasticsearch.plugin.nlpcn.executors.CsvExtractorException;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.QueryAction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;

/**
 * Created by yaoqijun.
 * Date:2016-11-13
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class ElasticSearchDruidPreparedStatement extends PreparedStatementBase{

    private Client client;

    private String sql;

    private static final String PREPARE_STATEMENT_STRING = "\\?";

    public ElasticSearchDruidPreparedStatement(String sql, Client client, Connection connection) {
        super(connection);
        this.client = client;
        this.sql = sql;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {

        checkOpen();

        try{
            ObjectResult extractor = getObjectResult(false, convertParamToString(sql) , false, false);
            List<String> headers = extractor.getHeaders();
            List<List<Object>> lines = extractor.getLines();

            ResultSet rs = new ElasticSearchResultSet(this, headers, lines);

            return rs;
        }catch (Exception e){
            throw new SQLException("execute query error");
        }
    }

    private ObjectResult getObjectResult(boolean flat, String query, boolean includeScore, boolean includeType) throws Exception {
        SearchDao searchDao = new SearchDao(client);

        //String rewriteSQL = searchDao.explain(getSql()).explain().explain();

        QueryAction queryAction = searchDao.explain(query);
        Object execution = QueryActionElasticExecutor.executeAnyAction(searchDao.getClient(), queryAction);


        return new ObjectResultsExtractor(includeScore, includeType).extractResults(execution, flat, ",");
    }

    @Override
    public int executeUpdate() throws SQLException {
        throw new SQLException("un support execute update");
    }

    @Override
    public boolean execute() throws SQLException {
        checkOpen();

        try{

            ObjectResult extractor = getObjectResult(false, convertParamToString(sql) , false, false);
            List<String> headers = extractor.getHeaders();
            List<List<Object>> lines = extractor.getLines();

            this.resultSet = new ElasticSearchResultSet(this, headers, lines);

            return true;
        }catch (Exception e){
            throw new SQLException("execute() method error");
        }
    }


    /**
     * 添加parameters 到 ？ 中
     * @param sql
     * @return
     */
    private String convertParamToString(String sql){
        // 简单的 string or number 类型的判断
        List<Object> params = this.getParameters();

        if(null == params || params.isEmpty()){
            return sql;
        }

        for (Object param : params) {
            sql = sql.replaceFirst(PREPARE_STATEMENT_STRING, objectToString(param));
        }

        return sql;
    }

    private String objectToString(Object o){
        if (o instanceof String || o instanceof Boolean){
            return "'" + String.valueOf(o) + "'";
        }
        return String.valueOf(o);
    }
}
