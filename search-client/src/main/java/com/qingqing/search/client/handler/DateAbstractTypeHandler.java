package com.qingqing.search.client.handler;

import com.google.common.base.Strings;
import com.qingqing.search.client.exception.EsHandlerException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yaoqijun on 2016/11/14.
 */
public abstract class DateAbstractTypeHandler extends BaseTypeHandler<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return stringToDate(rs.getString(columnName));
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return stringToDate(rs.getString(columnIndex));
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return stringToDate(cs.getString(columnIndex));
    }

    public Date stringToDate(String dateStr) {
        if(Strings.isNullOrEmpty(dateStr)){
            return null;
        }

        SimpleDateFormat shortDateFormat = new SimpleDateFormat(getDateFormat());
        try {
            return shortDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new EsHandlerException("es date convert fail");
        }
    }

    public abstract String getDateFormat();

}
