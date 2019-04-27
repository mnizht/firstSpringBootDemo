package com.example.demo.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author zhuhaitao
 * @date 2019/1/29 15:23
 */

public class ManagerTaskHandler implements TaskListener {

  private static final long serialVersionUID = 35351128264745697L;

  @Override
  public void notify(DelegateTask delegateTask) {
    delegateTask.setAssignee("经理");
  }

}
