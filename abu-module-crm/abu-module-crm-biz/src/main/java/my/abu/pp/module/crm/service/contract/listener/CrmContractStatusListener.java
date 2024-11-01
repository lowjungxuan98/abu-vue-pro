package my.abu.pp.module.crm.service.contract.listener;

import my.abu.pp.module.bpm.event.BpmProcessInstanceStatusEvent;
import my.abu.pp.module.bpm.event.BpmProcessInstanceStatusEventListener;
import my.abu.pp.module.crm.service.contract.CrmContractService;
import my.abu.pp.module.crm.service.contract.CrmContractServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 合同审批的结果的监听器实现类
 *
 * @author HUIHUI
 */
@Component
public class CrmContractStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private CrmContractService contractService;

    @Override
    public String getProcessDefinitionKey() {
        return CrmContractServiceImpl.BPM_PROCESS_DEFINITION_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        contractService.updateContractAuditStatus(Long.parseLong(event.getBusinessKey()), event.getStatus());
    }

}
