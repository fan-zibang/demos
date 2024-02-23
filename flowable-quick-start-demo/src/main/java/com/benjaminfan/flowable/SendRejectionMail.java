package com.benjaminfan.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendRejectionMail implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("发送拒绝请假邮件... 收信人：" + delegateExecution.getVariable("employee")
                + "，拒绝理由：" + delegateExecution.getVariable("rejectReason"));
    }
}
