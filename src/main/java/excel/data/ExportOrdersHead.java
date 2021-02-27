package excel.data;

import com.alibaba.excel.annotation.ExcelProperty;
import excel.custome.HeadStyleWriteHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * @author zhuht
 * @date 2021/2/26 10:40
 */
@Data
@Accessors(chain = true)
public class ExportOrdersHead {

  private static final String EXPLAIN_TEXT = "说明：\n" +
    "只付了定金但未付尾款的订单收款记录，备注中标注有“定金”字样；备注为空表示该订单已收全款";

  @ExcelProperty(value = {EXPLAIN_TEXT, "订单编号"})
  private String ordersSn;
  @ExcelProperty(value = {EXPLAIN_TEXT, "订单名称"})
  private String ordersName;
  @ExcelProperty(value = {EXPLAIN_TEXT, "收款时间"})
  private String receiveAt;
  @ExcelProperty(value = {EXPLAIN_TEXT, "学员编号"})
  private String studentSn;
  @ExcelProperty(value = {EXPLAIN_TEXT, "学员姓名"})
  private String studentName;
  @ExcelProperty(value = {EXPLAIN_TEXT, "签约年级"})
  private String signedGrade;
  @ExcelProperty(value = {EXPLAIN_TEXT, "购买课时"})
  private Integer buyPeriod;
  @ExcelProperty(value = {EXPLAIN_TEXT, "现金"})
  private BigDecimal payWayCash;
  @ExcelProperty(value = {EXPLAIN_TEXT, "刷卡"})
  private BigDecimal payWayCard;
  @ExcelProperty(value = {EXPLAIN_TEXT, "扫码"})
  private BigDecimal payWayQRCode;
  @ExcelProperty(value = {EXPLAIN_TEXT, "其它"})
  private BigDecimal payWayOther;
  @ExcelProperty(value = {EXPLAIN_TEXT, "订单总金额"})
  private BigDecimal amount;
  @ExcelProperty(value = {EXPLAIN_TEXT, "实收金额"})
  private BigDecimal payed;
  @ExcelProperty(value = {EXPLAIN_TEXT, "备注"})
  private String remark;
  @ExcelProperty(value = {EXPLAIN_TEXT, "签约类型"})
  private String signedType;
  @ExcelProperty(value = {EXPLAIN_TEXT, "推荐单"})
  private String recommend;

  /**
   * 员工业绩分配信息
   * 复杂表头的合并可以使用注解 @ExcelProperty
   * 复杂表头的自定义颜色无法使用注解 @HeadStyle ,因为没法单独设定合并单元格的颜色
   * 使用自定义的表头格式设置 {@link HeadStyleWriteHandler}
   */
  @ExcelProperty(value = {EXPLAIN_TEXT, "业绩分配", "员工"})
  private String userName;
  @ExcelProperty(value = {EXPLAIN_TEXT, "业绩分配", "角色"})
  private String userRole;
  @ExcelProperty(value = {EXPLAIN_TEXT, "业绩分配", "部门"})
  private String userDep;
  @ExcelProperty(value = {EXPLAIN_TEXT, "业绩分配", "分配课时"})
  private Integer distributePeriod;

}
