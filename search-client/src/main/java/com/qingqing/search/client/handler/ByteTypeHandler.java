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
public class ByteTypeHandler extends BaseTypeHandler<Byte>{
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Byte parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public Byte getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return byteConvert(rs.getObject(columnName));
    }

    @Override
    public Byte getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return byteConvert(rs.getObject(columnIndex));
    }

    @Override
    public Byte getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return byteConvert(cs.getObject(columnIndex));
    }

    private Byte byteConvert(Object o){

        if(o instanceof Integer){
            Integer value = (Integer) o;
            if(value > Byte.MAX_VALUE){
                throw new EsHandlerException("byte out range exception");
            }
            return Byte.valueOf(o.toString());
        }
        return null;
    }
}
