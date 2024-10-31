package my.abu.pp.module.bpm.framework.flowable.core.candidate.strategy;

import cn.hutool.core.convert.Convert;
import my.abu.pp.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import my.abu.pp.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import my.abu.pp.module.bpm.framework.flowable.core.util.FlowableUtils;
import my.abu.pp.module.system.api.user.AdminUserApi;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 流程表达式 {@link BpmTaskCandidateStrategy} 实现类
 *
 * @author 阿布源码
 */
@Component
public class BpmTaskCandidateExpressionStrategy extends BpmTaskCandidateAbstractStrategy {

    public BpmTaskCandidateExpressionStrategy(AdminUserApi adminUserApi) {
        super(adminUserApi);
    }

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.EXPRESSION;
    }

    @Override
    public void validateParam(String param) {
        // do nothing 因为它基本做不了校验
    }

    @Override
    public Set<Long> calculateUsers(DelegateExecution execution, String param) {
        Object result = FlowableUtils.getExpressionValue(execution, param);
        Set<Long> users = Convert.toSet(Long.class, result);
        removeDisableUsers(users);
        return users;
    }

}
