package excel.data;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * @author zhuht
 * @date 2021/2/25 18:31
 */
public class HeadEntity {
  @ExcelProperty({"主标题", "字符串标题"})
  private String string;
  @ExcelProperty({"主标题", "日期标题"})
  private Date date;
  @ExcelProperty({"主标题", "数字标题"})
  private Double doubleData;
  @ExcelProperty({"主标题", "二级标题", "三一"})
  private String threeOne;
  @ExcelProperty({"主标题", "二级标题", "三二"})
  private String threeTwo;
  @ExcelProperty({"主标题", "二级标题", "三三"})
  private String threeThree;
}
