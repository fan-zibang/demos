package com.fanzibang.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class AddLeaveRecordDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("新增一条请假记录，请假人："
                + delegateExecution.getVariable("employee"));
    }

}
