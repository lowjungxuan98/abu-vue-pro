package my.abu.pp.module.bpm.service.oa.listener;

import my.abu.pp.module.bpm.event.BpmProcessInstanceStatusEvent;
import my.abu.pp.module.bpm.event.BpmProcessInstanceStatusEventListener;
import my.abu.pp.module.bpm.service.oa.BpmOALeaveService;
import my.abu.pp.module.bpm.service.oa.BpmOALeaveServiceImpl;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 阿布源码
 */
@Component
public class BpmOALeaveStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private BpmOALeaveService leaveService;

    @Override
    protected String getProcessDefinitionKey() {
        return BpmOALeaveServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        leaveService.updateLeaveStatus(Long.parseLong(event.getBusinessKey()), event.getStatus());
    }

}
