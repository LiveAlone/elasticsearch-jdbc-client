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
public class FloatTypeHandler extends BaseTypeHandler<Float>{
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Float parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public Float getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return floatTypeConvert(rs.getObject(columnName));
    }

    @Override
    public Float getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return floatTypeConvert(rs.getObject(columnIndex));
    }

    @Override
    public Float getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return floatTypeConvert(cs.getObject(columnIndex));
    }

    private Float floatTypeConvert(Object o){

        if( o instanceof Double){

            Double value = (Double) o;
            if(value > Float.MAX_VALUE){
                throw new EsHandlerException("float handler convert out of range error");
            }

            return Float.valueOf(value.toString());
        }
        return null;
    }
}
