package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author zhuhaitao
 * @date 2019/5/10 15:46
 * 测试定时任务
 */
@Service
public class ScheduleService {
  // 计数器
  int count1 = 1;
  int count2 = 1;

  //每隔一秒执行一次
  @Scheduled(fixedRate = 1000)
  //使用异步执行
  @Async
  public void job1() {
    System.out.println("【" + Thread.currentThread().getName() + "】" + "【job1】每秒执行一次，执行第【" + count1 + "】次");
    count1++;
  }

  //每隔一秒执行一次
  @Scheduled(fixedRate = 1000)
  //使用异步执行
  @Async
  public void job2() {
    System.out.println("【" + Thread.currentThread().getName() + "】" + "【job2】每秒执行一次，执行第【" + count2 + "】次");
    count2++;
  }
}
