package dbjava.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

/**
 * @author zhuhaitao
 * @date 2019/5/23 17:57
 * <p>
 * 由源码可看到 BaseJavaMigration 是一个抽象类，它实现了 JavaMigration 接口的几乎所有方法，
 * 所以这里只要 重载 migrate 方法即可。
 */
public class V1_7__insert extends BaseJavaMigration {
  public static final String sql = "insert into people2 (name,age) values ('聂小倩',18)";

  @Override
  public void migrate(Context context) throws Exception {
    new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
      .execute(sql);
  }

  @Override
  public Integer getChecksum() {
    return sql.hashCode();
  }
}
