package excel.custome;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author zhuht
 * @date 2021/2/26 10:25
 */
public class HeadStyleWriteHandler extends AbstractCellStyleStrategy {

  /**
   * 复杂表头自定义样式队列，先进先出，方便存储
   */
  private ArrayBlockingQueue<ComplexHeadStyles> headStylesQueue;
  /**
   * WorkBoot
   */
  private Workbook workbook;

  /**
   * 构造方法，创建对象时传入需要定制的表头信息队列
   */
  public HeadStyleWriteHandler(ArrayBlockingQueue<ComplexHeadStyles> headStylesQueue) {
    this.headStylesQueue = headStylesQueue;
  }

  @Override
  protected void initCellStyle(Workbook workbook) {
    // 初始化信息时，保存Workbook对象，转换时需要使用
    this.workbook = workbook;
  }

  @Override
  protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
    WriteCellStyle writeCellStyle = new WriteCellStyle();
    // 表格默认为白色
    writeCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
    if (CollectionUtils.isNotEmpty(headStylesQueue)) {

      ComplexHeadStyles complexHeadStyles = headStylesQueue.peek();
      // 取出当前队列中的自定义表头信息，与当前坐标比较，判断是否相符
      assert complexHeadStyles != null;
      if (cell.getColumnIndex() == complexHeadStyles.getY() && relativeRowIndex.equals(complexHeadStyles.getX())) {
        if (Objects.nonNull(complexHeadStyles.getCellHeight())){
          cell.getRow().setHeightInPoints(complexHeadStyles.getCellHeight());
        }
        // 设置自定义表头样式
        writeCellStyle = complexHeadStyles.getWriteCellStyle();
        // 样式出队
        headStylesQueue.poll();
      }
    }

    // WriteCellStyle 转换为 CellStyle
    CellStyle headCellStyle = StyleUtil.buildHeadCellStyle(workbook, writeCellStyle);
    // 设置表头样式
    cell.setCellStyle(headCellStyle);
  }

  @Override
  protected void setContentCellStyle(Cell cell, Head head, Integer integer) {

  }
}
