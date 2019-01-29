package com.example.demo.listener;

/**
 * @author zhuhaitao
 * @date 2019/1/29 15:23
 */

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class ManagerTaskHandler implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    delegateTask.setAssignee("经理");
  }

}
