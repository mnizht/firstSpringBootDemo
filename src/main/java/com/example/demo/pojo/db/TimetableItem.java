package com.example.demo.pojo.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

/**
 * @author haitao zhu
 * @date 2020/3/7 15:21
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimetableItem {

    public TimetableItem(int sequence, LocalTime startTime, LocalTime endTime) {
        this.sequence = sequence;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    int sequence; //一天中的第几节课
    LocalTime startTime; //上课时间
    LocalTime endTime;  //下课时间

}
