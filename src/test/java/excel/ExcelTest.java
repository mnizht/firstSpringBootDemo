package excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.example.demo.utils.TestFileUtil;
import excel.custome.ComplexHeadStyles;
import excel.custome.HeadStyleWriteHandler;
import excel.custome.MyMergeStrategy;
import excel.data.ExportOrdersHead;
import excel.data.HeadEntity;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author zhuht
 * @date 2021/2/26 10:20
 */
public class ExcelTest {

  @Test
  public void writeTest() {
    String fileName = TestFileUtil.getPath() + "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
    EasyExcel.write(fileName, HeadEntity.class).sheet("模板").doWrite(Collections.emptyList());
  }

  @Test
  public void complexHeadWriteTest() {

    String fileName = TestFileUtil.getPath() + "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
    // 设置表头样式队列【先进先出】
    ArrayBlockingQueue<ComplexHeadStyles> complexHeadStylesArrayBlockingQueue = getExportOrdersHeadStyleQueue();
    // 自定义表头写策略
    HeadStyleWriteHandler headStyleWriteHandler = new HeadStyleWriteHandler(complexHeadStylesArrayBlockingQueue);
    //自定义数据写策略
    MyMergeStrategy mergeStrategy = new MyMergeStrategy(getGroup(data()));
    // 写excel
    EasyExcelFactory.write(fileName, ExportOrdersHead.class)
      .registerWriteHandler(headStyleWriteHandler)
      .registerWriteHandler(mergeStrategy)
      .sheet("sheet1").doWrite(data());

//    ExcelWriterBuilder write = EasyExcelFactory.write(fileName, ExportOrdersHead.class);
//    ExcelWriterBuilder excelWriterBuilder = write.registerWriteHandler(headStyleWriteHandler);
//    ExcelWriterSheetBuilder sheet1 = excelWriterBuilder.sheet("sheet1");
//    sheet1.doWrite(data());

  }

  private Map<Integer, Integer> getGroup(List<ExportOrdersHead> data) {
    List<Integer> collect = data.stream().collect(Collectors.groupingBy(ExportOrdersHead::getOrdersSn, Collectors.counting()))
      .values().stream().map(Long::intValue).collect(Collectors.toList());
    Map<Integer, Integer> map = new HashMap<>();
    int row = 3;
    for (int num : collect) {
      if (num > 1) {
        map.put(row, num);
      }
      row += num;
    }
    return map;

  }

  private List<ExportOrdersHead> data() {
    List<ExportOrdersHead> data = new ArrayList<>();
    ExportOrdersHead data1 = new ExportOrdersHead()
      .setOrdersSn("123456").setOrdersName("订单1").setReceiveAt("2021/02/26 10:30").setStudentSn("123").setStudentName("张三")
      .setSignedGrade("一年级").setBuyPeriod(300).setPayWayCash(new BigDecimal(100)).setPayWayCard(new BigDecimal(120.50))
      .setPayWayQRCode(new BigDecimal(200.50)).setPayWayOther(new BigDecimal(0)).setAmount(new BigDecimal(421))
      .setPayed(new BigDecimal(421)).setRemark(null).setSignedType("新签").setRecommend("已关联")
      .setUserName("张三").setUserRole("咨询师").setUserDep("咨询部").setDistributePeriod(100);
    ExportOrdersHead data2 = new ExportOrdersHead()
      .setOrdersSn("123456").setOrdersName("订单1").setReceiveAt("2021/02/26 10:30").setStudentSn("123").setStudentName("张三")
      .setSignedGrade("一年级").setBuyPeriod(300).setPayWayCash(new BigDecimal(100)).setPayWayCard(new BigDecimal(120.50))
      .setPayWayQRCode(new BigDecimal(200.50)).setPayWayOther(new BigDecimal(0)).setAmount(new BigDecimal(421))
      .setPayed(new BigDecimal(421)).setRemark(null).setSignedType("新签").setRecommend("已关联")
      .setUserName("李四").setUserRole("咨询师").setUserDep("咨询部").setDistributePeriod(100);
    ExportOrdersHead data3 = new ExportOrdersHead()
      .setOrdersSn("123456").setOrdersName("订单1").setReceiveAt("2021/02/26 10:30").setStudentSn("123").setStudentName("张三")
      .setSignedGrade("一年级").setBuyPeriod(300).setPayWayCash(new BigDecimal(100)).setPayWayCard(new BigDecimal(120.50))
      .setPayWayQRCode(new BigDecimal(200.50)).setPayWayOther(new BigDecimal(0)).setAmount(new BigDecimal(421))
      .setPayed(new BigDecimal(421)).setRemark(null).setSignedType("新签").setRecommend("已关联")
      .setUserName("王五").setUserRole("班主任").setUserDep("教学部").setDistributePeriod(200);

    ExportOrdersHead data4 = new ExportOrdersHead()
      .setOrdersSn("12345678").setOrdersName("订单2").setReceiveAt("2021/02/26 10:30").setStudentSn("123").setStudentName("张三")
      .setSignedGrade("一年级").setBuyPeriod(300).setPayWayCash(new BigDecimal(100)).setPayWayCard(new BigDecimal(120.50))
      .setPayWayQRCode(new BigDecimal(200.50)).setPayWayOther(new BigDecimal(0)).setAmount(new BigDecimal(421))
      .setPayed(new BigDecimal(421)).setRemark("定金").setSignedType("续费").setRecommend("未关联");

    data.add(data1);
    data.add(data2);
    data.add(data3);
    data.add(data4);
    return data;
  }

  private ArrayBlockingQueue<ComplexHeadStyles> getExportOrdersHeadStyleQueue() {
    ArrayBlockingQueue<ComplexHeadStyles> queue = new ArrayBlockingQueue<>(36);
    // 说明的单元格样式
    WriteCellStyle explainTextStyle = new WriteCellStyle();
    explainTextStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
    explainTextStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
    explainTextStyle.setVerticalAlignment(VerticalAlignment.JUSTIFY);
    // 褐色单元格
    WriteCellStyle tanStyle = new WriteCellStyle();
    tanStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());

    // 黄色单元格
    WriteCellStyle yellowStyle = new WriteCellStyle();
    yellowStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    // 写 excel是一行一行写的

    queue.add(new ComplexHeadStyles(0, 0, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 1, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 2, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 3, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 4, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 5, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 6, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 7, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 8, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 9, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 10, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 11, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 12, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 13, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 14, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 15, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 16, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 17, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 18, explainTextStyle, 50));
    queue.add(new ComplexHeadStyles(0, 19, explainTextStyle, 50));

    queue.add(new ComplexHeadStyles(1, 5, tanStyle));
    queue.add(new ComplexHeadStyles(1, 12, tanStyle));
    queue.add(new ComplexHeadStyles(1, 14, tanStyle));
    queue.add(new ComplexHeadStyles(1, 15, tanStyle));
    queue.add(new ComplexHeadStyles(1, 16, tanStyle));
    queue.add(new ComplexHeadStyles(1, 17, tanStyle));
    queue.add(new ComplexHeadStyles(1, 18, tanStyle));
    queue.add(new ComplexHeadStyles(1, 19, tanStyle));

    queue.add(new ComplexHeadStyles(2, 5, tanStyle));
    queue.add(new ComplexHeadStyles(2, 12, tanStyle));
    queue.add(new ComplexHeadStyles(2, 14, tanStyle));
    queue.add(new ComplexHeadStyles(2, 15, tanStyle));
    queue.add(new ComplexHeadStyles(2, 16, yellowStyle));
    queue.add(new ComplexHeadStyles(2, 17, yellowStyle));
    queue.add(new ComplexHeadStyles(2, 18, yellowStyle));
    queue.add(new ComplexHeadStyles(2, 19, yellowStyle));

    return queue;
  }
}
