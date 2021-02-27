package excel.service;

import com.alibaba.excel.EasyExcel;
import com.example.demo.utils.TestFileUtil;
import excel.data.HeadEntity;

/**
 * @author zhuht
 * @date 2021/2/25 18:38
 */
public class ExcelWriteService {

  public void complexHeadWrite() {
    String fileName = TestFileUtil.getPath() + "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
    EasyExcel.write(fileName, HeadEntity.class).sheet("模板");
  }
}
