package com.qingqing.search.client.handler;

import com.qingqing.search.client.exception.EsHandlerException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yaoqijun on 2016/11/14.
 */
public class ShortTypeHandler extends BaseTypeHandler<Short>{
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Short parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public Short getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return shortTypConvert(rs.getObject(columnName));
    }

    @Override
    public Short getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return shortTypConvert(rs.getObject(columnIndex));
    }

    @Override
    public Short getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return shortTypConvert(cs.getObject(columnIndex));
    }

    private Short shortTypConvert(Object o){

        if(o instanceof Integer){
            Integer value = (Integer) o;
            if(value > Integer.MAX_VALUE){
                throw new EsHandlerException("short convert out of range error");
            }
            return Short.valueOf(value.toString());
        }
        return null;
    }
}
