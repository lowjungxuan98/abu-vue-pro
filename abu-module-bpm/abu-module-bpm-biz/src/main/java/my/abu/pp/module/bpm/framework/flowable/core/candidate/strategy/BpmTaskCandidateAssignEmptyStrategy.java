package my.abu.pp.module.bpm.framework.flowable.core.candidate.strategy;

import cn.hutool.core.lang.Assert;
import my.abu.pp.module.bpm.dal.dataobject.definition.BpmProcessDefinitionInfoDO;
import my.abu.pp.module.bpm.enums.definition.BpmUserTaskAssignEmptyHandlerTypeEnum;
import my.abu.pp.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import my.abu.pp.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import my.abu.pp.module.bpm.framework.flowable.core.util.BpmnModelUtils;
import my.abu.pp.module.bpm.service.definition.BpmProcessDefinitionService;
import my.abu.pp.module.system.api.user.AdminUserApi;
import jakarta.annotation.Resource;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 审批人为空 {@link BpmTaskCandidateStrategy} 实现类
 *
 * @author kyle
 */
@Component
public class BpmTaskCandidateAssignEmptyStrategy extends BpmTaskCandidateAbstractStrategy {

    @Resource
    @Lazy // 延迟加载，避免循环依赖
    private BpmProcessDefinitionService processDefinitionService;

    public BpmTaskCandidateAssignEmptyStrategy(AdminUserApi adminUserApi) {
        super(adminUserApi);
    }

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.ASSIGN_EMPTY;
    }

    @Override
    public void validateParam(String param) {
    }

    @Override
    public Set<Long> calculateUsers(DelegateExecution execution, String param) {
        // 情况一：指定人员审批
        Integer assignEmptyHandlerType = BpmnModelUtils.parseAssignEmptyHandlerType(execution.getCurrentFlowElement());
        if (Objects.equals(assignEmptyHandlerType, BpmUserTaskAssignEmptyHandlerTypeEnum.ASSIGN_USER.getType())) {
            Set<Long> users = new HashSet<>(BpmnModelUtils.parseAssignEmptyHandlerUserIds(execution.getCurrentFlowElement()));
            removeDisableUsers(users);
            return users;
        }

        // 情况二：流程管理员
        if (Objects.equals(assignEmptyHandlerType, BpmUserTaskAssignEmptyHandlerTypeEnum.ASSIGN_ADMIN.getType())) {
            BpmProcessDefinitionInfoDO processDefinition = processDefinitionService.getProcessDefinitionInfo(execution.getProcessDefinitionId());
            Assert.notNull(processDefinition, "流程定义({})不存在", execution.getProcessDefinitionId());
            return new HashSet<>(processDefinition.getManagerUserIds());
        }

        // 都不满足，还是返回空
        return new HashSet<>();
    }

}