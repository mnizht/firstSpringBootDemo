package dbjava.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.PreparedStatement;

/**
 * @author zhuhaitao
 * @date 2019/5/23 17:57
 * <p>
 * 由源码可看到 BaseJavaMigration 是一个抽象类，它实现了 JavaMigration 接口的几乎所有方法，
 * 所以这里只要 重载 migrate 方法即可。
 */
public class V1_6__add_new_table extends BaseJavaMigration {

  @Override
  public void migrate(Context context) throws Exception {
    new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(),true))
      .execute("CREATE TABLE `people3` (\n" +
        "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
        "  `name` varchar(20) NOT NULL COMMENT '名称',\n" +
        "  `age` int(5) DEFAULT NULL COMMENT '年龄',\n" +
        "  PRIMARY KEY (`id`)\n" +
        ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;");

//    PreparedStatement statement = context.getConnection()
//      .prepareStatement("CREATE TABLE `people2` (\n" +
//        "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
//        "  `name` varchar(20) NOT NULL COMMENT '名称',\n" +
//        "  `age` int(5) DEFAULT NULL COMMENT '年龄',\n" +
//        "  PRIMARY KEY (`id`)\n" +
//        ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;");
//    statement.execute();
  }
}
