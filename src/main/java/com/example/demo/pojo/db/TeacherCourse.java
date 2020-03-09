package com.example.demo.pojo.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author haitao zhu
 * @date 2020/3/6 22:29
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherCourse {

    public TeacherCourse(String teacherId, LocalDate date, Integer sequence, String studentGroupName, String scheduleId) {
        this.teacherId = teacherId;
        this.date = date;
        this.sequence = sequence;
        this.studentGroupName = studentGroupName;
        this.scheduleId = scheduleId;
    }

    String teacherId; //教师id
    LocalDate date; //上课日期
    Integer sequence; //每日课程节数序号

    String studentGroupName; //班组名称

    /**
     * 该节时段开始时间(大于等于零点),左闭右开
     */
    LocalTime startTime;

    /**
     * 该节时段节数时间(小于次日零点),左闭右开
     */
    LocalTime endTime;

    String scheduleId; //排课组id，标识一次排课组合的id
}
