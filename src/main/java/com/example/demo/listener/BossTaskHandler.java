package com.example.demo.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author zhuhaitao
 * @date 2019/1/29 15:24
 */

public class BossTaskHandler implements TaskListener {

  private static final long serialVersionUID = -5301006670654063369L;

  @Override
  public void notify(DelegateTask delegateTask) {
    delegateTask.setAssignee("老板");
  }

}

