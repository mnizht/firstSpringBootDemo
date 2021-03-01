package excel.custome;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Map;

/**
 * @author zhuht
 * @date 2021/2/27 15:02
 */
public class MyMergeStrategy extends AbstractMergeStrategy {
  private Map<Integer, Integer> ordersGroupCount;
  private Sheet sheet;

  public MyMergeStrategy(Map<Integer, Integer> ordersGroupCount) {
    this.ordersGroupCount = ordersGroupCount;
  }

  // 按照分组将各种类别分别合并成一个单元格
  private void mergeGroupColumn(Integer columnIndex) {

    ordersGroupCount.forEach((key, value) -> {
      CellRangeAddress cellRangeAddress = new CellRangeAddress(key, key + value - 1, columnIndex, columnIndex);
      sheet.addMergedRegionUnsafe(cellRangeAddress);
    });
  }


  @Override
  protected void merge(Sheet sheet, Cell cell, Head head, Integer integer) {
    this.sheet = sheet;
    int rowIndex = cell.getRowIndex();
    int columnIndex = cell.getColumnIndex();
    if (rowIndex == 3 && columnIndex < 16) {
      mergeGroupColumn(columnIndex);
    }
  }
}
