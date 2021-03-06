package com.example.demo.typehandler;

import com.example.demo.enumeration.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author zhuhaitao
 * date 2019/3/7 15:31
 **/
//声明jdbcType为整形
@MappedJdbcTypes(JdbcType.INTEGER)
//声明jdbcType为SexEnum
@MappedTypes(value = SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, sexEnum.getId());
  }

  @Override
  public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
    int sex = resultSet.getInt(s);
    return getEnumById(sex);
  }

  @Override
  public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
    int sex = resultSet.getInt(i);
    return getEnumById(sex);
  }

  @Override
  public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    int sex = callableStatement.getInt(i);
    return getEnumById(sex);
  }

  private SexEnum getEnumById(int sex) {
    if (sex != 1 && sex != 2) {
      return null;
    }
    return SexEnum.getEnumById(sex);
  }
}
