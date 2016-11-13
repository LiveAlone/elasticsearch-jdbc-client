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

    public ElasticSearchDruidPreparedStatement(String sql, Client client, Connection connection) {
        super(connection);
        this.client = client;
        this.sql = sql;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {

        checkOpen();

        try{
            ObjectResult extractor = getObjectResult(true, sql , false, false);
            List<String> headers = extractor.getHeaders();
            List<List<Object>> lines = extractor.getLines();

            ResultSet rs = new ElasticSearchResultSet(this, headers, lines);

            return rs;
        }catch (Exception e){
            throw new SQLException("execute query error");
        }
    }

    private ObjectResult getObjectResult(boolean flat, String query, boolean includeScore, boolean includeType) throws SqlParseException, SQLFeatureNotSupportedException, Exception, CsvExtractorException {
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

            ObjectResult extractor = getObjectResult(true, sql , false, false);
            List<String> headers = extractor.getHeaders();
            List<List<Object>> lines = extractor.getLines();

            this.resultSet = new ElasticSearchResultSet(this, headers, lines);

            return true;
        }catch (Exception e){
            throw new SQLException("execute() method error");
        }

//        throw new SQLException("un support execute update");
    }
}
