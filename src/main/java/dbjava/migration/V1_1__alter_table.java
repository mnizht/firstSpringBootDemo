package dbjava.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.PreparedStatement;

/**
 * @author zhuhaitao
 * @date 2019/5/23 18:01
 */
public class V1_1__alter_table extends BaseJavaMigration{

  @Override
  public void migrate(Context context) throws Exception {
    PreparedStatement statement = context.getConnection()
      .prepareStatement("CREATE TABLE `people2` (\n" +
        "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
        "  `name` varchar(20) NOT NULL COMMENT '名称',\n" +
        "  `age` int(5) DEFAULT NULL COMMENT '年龄',\n" +
        "  PRIMARY KEY (`id`)\n" +
        ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;");
    statement.execute();


  }

}
