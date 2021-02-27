package excel.custome;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhuht
 * @date 2021/2/26 10:17
 */
@Data
@AllArgsConstructor
public class ComplexHeadStyles {
  /**
   * 表头横坐标 - 行
   */
  private Integer x;
  /**
   * 表头纵坐标 - 列
   */
  private Integer y;

  /**
   * 填充内容样式
   */
  private WriteCellStyle writeCellStyle;

  /**
   * 单元格行高
   */
  private Integer cellHeight;

  public ComplexHeadStyles(Integer x, Integer y, WriteCellStyle writeCellStyle) {
    this.x = x;
    this.y = y;
    this.writeCellStyle = writeCellStyle;
  }
}
