package com.example.demo;

import com.example.demo.pojo.db.TeacherCourse;
import com.example.demo.service.ExportExcelByPOI;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haitao zhu
 * @date 2020/3/7 17:53
 */

public class ExportTest {
    @Test
    public static void main(String[] args) {
        ExportExcelByPOI eep = new ExportExcelByPOI();
        String url = eep.exportCourse(getData(), LocalDate.parse("2020-02-02"), LocalDate.parse("2020-03-31"));
        System.out.println(url);
    }

    public static List<TeacherCourse> getData() {
        List<TeacherCourse> list = new ArrayList<>();
        // String teacherId, LocalDate date, Integer sequence, String studentGroupName, String scheduleId
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-02"), 1, "张三课程一", "zs-0202-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-02"), 2, "张三课程一", "zs-0202-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-02"), 3, "张三课程一", "zs-0202-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-02"), 7, "张三课程二", "zs-0202-2"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-02"), 8, "张三课程二", "zs-0202-2"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-03"), 5, "张三课程三", "zs-0203-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-03"), 6, "张三课程三", "zs-0203-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-03"), 7, "张三课程三", "zs-0203-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-03"), 9, "张三课程四", "zs-0203-2"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-03"), 10, "张三课程四", "zs-0203-2"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-05"), 3, "张三课程五", "zs-0205-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-05"), 4, "张三课程五", "zs-0205-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-06"), 2, "张三课程六", "zs-0206-1"));
        list.add(new TeacherCourse("张三", LocalDate.parse("2020-02-07"), 2, "张三课程七", "zs-0207-1"));

        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-02"), 1, "李四课程一", "ls-0202-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-02"), 2, "李四课程一", "ls-0202-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-02"), 3, "李四课程一", "ls-0202-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-02"), 7, "李四课程二", "ls-0202-2"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-02"), 8, "李四课程二", "ls-0202-2"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-03"), 5, "李四课程三", "ls-0203-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-03"), 6, "李四课程三", "ls-0203-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-03"), 7, "李四课程三", "ls-0203-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-03"), 9, "李四课程四", "ls-0203-2"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-03"), 10, "李四课程四", "ls-0203-2"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-05"), 3, "李四课程五", "ls-0205-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-05"), 4, "李四课程五", "ls-0205-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-06"), 2, "李四课程六", "ls-0206-1"));
        list.add(new TeacherCourse("李四", LocalDate.parse("2020-02-07"), 2, "李四课程七", "ls-0207-1"));


        return list;
    }
}
