package com.example.demo.service;

import com.example.demo.pojo.db.TeacherCourse;
import com.example.demo.pojo.db.TimetableItem;
import com.example.demo.utils.Java8DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author haitao zhu
 * @date 2020/3/6 22:38
 * 通过 Apache POI 导出 excel文件
 */
public class ExportExcelByPOI {

    private static String[] weeks = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    /**
     * 导出机构课表
     *
     * @param courses   查询出的教师课程信息
     * @param startDate 课表查询时间范围：开始日期
     * @param endDate   课表查询时间范围：结束日期
     */
    public String exportCourse(Collection<TeacherCourse> courses, LocalDate startDate, LocalDate endDate) {
        String url = "xiazaidizhi";
        // 将教师课程按教师分组
        Map<String, List<TeacherCourse>> teacherCourseMap = courses.stream().collect(Collectors.groupingBy(TeacherCourse::getTeacherId, Collectors.toList()));
        // 查询课表时段
        List<TimetableItem> timetableTemplate = getTimetableTemplate();
        // 创建文件
        try (Workbook wb = new XSSFWorkbook()) {
            // 创建单元格样式
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER); //水平居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
            // 创建文件的一个工作区
            Sheet sheet1 = wb.createSheet("机构课表导出数据");
            addHeader(sheet1, startDate, endDate, cellStyle);

            // 分组处理数据
            int teacherNum = 0;
            for (Map.Entry<String, List<TeacherCourse>> entry : teacherCourseMap.entrySet()) {
                String key = entry.getKey();
                List<TeacherCourse> value = entry.getValue();
                addCourse(sheet1, cellStyle, startDate, key, value, teacherNum++, timetableTemplate);

            }


            // 加载一个本地文件，不存在的话会创建一个
            File file = new File("e://test.xlsx");
            FileOutputStream fileOut = null;

            fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * 添加文件头，格式固定，列数由日期范围确定
     */
    private void addHeader(Sheet sheet, LocalDate startDate, LocalDate endDate, CellStyle cellStyle) {
        // 创建一行的工作区，指定对应文件中的第一行（下标从0开始）
        Row row0 = sheet.createRow(0);
        // 创建一个单元格的工作区，指定为第一行的第一格，（下标从0开始）
        Cell teacherName = row0.createCell(0);
        teacherName.setCellValue("教师姓名");
        teacherName.setCellStyle(cellStyle);
        // 添加合并区域，合并第一列前两行
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        Cell time = row0.createCell(1);
        time.setCellValue("时段");
        // 添加合并区域，合并第二列前两行
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
        Row row1 = sheet.createRow(1);
        LocalDate currentDate = startDate;
        int weekNum = currentDate.getDayOfWeek().getValue() - 1;
        for (int i = 0; i < (int) ChronoUnit.DAYS.between(startDate, endDate); i++) {
            // 设置表头日期
            Cell cellDate = row0.createCell(2 + i);
            cellDate.setCellStyle(cellStyle);
            cellDate.setCellValue(Java8DateUtils.toString(currentDate));

            // 设置表头星期
            Cell cellWeek = row1.createCell(2 + i);
            cellWeek.setCellStyle(cellStyle);
            cellWeek.setCellValue(weeks[weekNum]);
            // 日期加一
            currentDate = currentDate.plusDays(1);
            weekNum = currentDate.getDayOfWeek().getValue() - 1;

        }

    }

    /**
     * 处理课程数据
     */
    private void addCourse(Sheet sheet, CellStyle cellStyle, LocalDate startDate, String teacherName, List<TeacherCourse> courses, int teacherNum, List<TimetableItem> timetableTemplate) {
        // 数据开始行下标
        int rowStart = teacherNum * timetableTemplate.size() + 2;
        // 数据结束行下标
        int rowEnd = rowStart + timetableTemplate.size() - 1;
        List<Row> rows = new ArrayList<>();
        for (int i = rowStart; i <= rowEnd; i++) {
            rows.add(sheet.createRow(i));
        }

        // 设置教师姓名
        Row row1 = rows.get(0);
        Cell cell0 = row1.createCell(0);
        cell0.setCellStyle(cellStyle);
        cell0.setCellValue(teacherName);
        sheet.addMergedRegion(new CellRangeAddress(rowStart, rowEnd, 0, 0));

        // 设置时段
        for (TimetableItem item : timetableTemplate) {
            String timeInterval = Java8DateUtils.toRemoveSecond(item.getStartTime(), item.getEndTime());
            Row row = rows.get(item.getSequence() - 1);
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(timeInterval);
        }

        // 一列列赋值数据
        Map<LocalDate, List<TeacherCourse>> dayCourseMap = courses.stream().sorted(Comparator.comparing(TeacherCourse::getDate))
            .collect(Collectors.groupingBy(TeacherCourse::getDate, Collectors.toList()));
        for (Map.Entry<LocalDate, List<TeacherCourse>> day_entry : dayCourseMap.entrySet()) {
            // 课程所属列下标
            int col = (int) ChronoUnit.DAYS.between(startDate, day_entry.getKey()) + 2;
            Map<String, List<TeacherCourse>> scheduleCourseMap = day_entry.getValue().stream().sorted(Comparator.comparingInt(TeacherCourse::getSequence))
                .collect(Collectors.groupingBy(TeacherCourse::getScheduleId, Collectors.toList()));
            for (Map.Entry<String, List<TeacherCourse>> schedule_entry : scheduleCourseMap.entrySet()) {
                int sequence = schedule_entry.getValue().get(0).getSequence();
                String studentGroupName = schedule_entry.getValue().get(0).getStudentGroupName();
                int size = schedule_entry.getValue().size();
                Row row = rows.get(sequence - 1);
                Cell cell = row.createCell(col);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(studentGroupName);
                if (size > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(rowStart + sequence - 1,
                        rowStart + sequence + size - 2, col, col));
                }

            }
        }


    }


    public List<TimetableItem> getTimetableTemplate() {
        List<TimetableItem> list = new ArrayList<>();
        list.add(new TimetableItem(1, Java8DateUtils.toLocalTime("08:00:00"), Java8DateUtils.toLocalTime("09:00:00")));
        list.add(new TimetableItem(2, Java8DateUtils.toLocalTime("09:00:00"), Java8DateUtils.toLocalTime("10:00:00")));
        list.add(new TimetableItem(3, Java8DateUtils.toLocalTime("10:00:00"), Java8DateUtils.toLocalTime("11:00:00")));
        list.add(new TimetableItem(4, Java8DateUtils.toLocalTime("11:00:00"), Java8DateUtils.toLocalTime("12:00:00")));
        list.add(new TimetableItem(5, Java8DateUtils.toLocalTime("14:00:00"), Java8DateUtils.toLocalTime("15:00:00")));
        list.add(new TimetableItem(6, Java8DateUtils.toLocalTime("15:00:00"), Java8DateUtils.toLocalTime("16:00:00")));
        list.add(new TimetableItem(7, Java8DateUtils.toLocalTime("16:00:00"), Java8DateUtils.toLocalTime("17:00:00")));
        list.add(new TimetableItem(8, Java8DateUtils.toLocalTime("17:00:00"), Java8DateUtils.toLocalTime("18:00:00")));
        list.add(new TimetableItem(9, Java8DateUtils.toLocalTime("19:00:00"), Java8DateUtils.toLocalTime("20:00:00")));
        list.add(new TimetableItem(10, Java8DateUtils.toLocalTime("20:00:00"), Java8DateUtils.toLocalTime("21:00:00")));

        return list;
    }
}
