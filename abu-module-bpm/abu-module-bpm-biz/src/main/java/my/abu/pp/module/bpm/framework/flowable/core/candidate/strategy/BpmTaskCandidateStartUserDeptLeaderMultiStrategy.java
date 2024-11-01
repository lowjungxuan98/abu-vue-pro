package my.abu.pp.module.bpm.framework.flowable.core.candidate.strategy;

import cn.hutool.core.lang.Assert;
import my.abu.pp.framework.common.util.number.NumberUtils;
import my.abu.pp.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import my.abu.pp.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import my.abu.pp.module.bpm.service.task.BpmProcessInstanceService;
import my.abu.pp.module.system.api.dept.DeptApi;
import my.abu.pp.module.system.api.dept.dto.DeptRespDTO;
import my.abu.pp.module.system.api.user.AdminUserApi;
import my.abu.pp.module.system.api.user.dto.AdminUserRespDTO;
import jakarta.annotation.Resource;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static cn.hutool.core.collection.ListUtil.toList;

/**
 * 发起人连续多级部门的负责人 {@link BpmTaskCandidateStrategy} 实现类
 *
 * @author jason
 */
@Component
public class BpmTaskCandidateStartUserDeptLeaderMultiStrategy extends BpmTaskCandidateAbstractDeptLeaderStrategy {

    @Resource
    @Lazy
    private BpmProcessInstanceService processInstanceService;

    public BpmTaskCandidateStartUserDeptLeaderMultiStrategy(AdminUserApi adminUserApi, DeptApi deptApi) {
        super(adminUserApi, deptApi);
    }

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.START_USER_DEPT_LEADER_MULTI;
    }

    @Override
    public void validateParam(String param) {
        // 参数是部门的层级
        Assert.isTrue(Integer.parseInt(param) > 0, "部门的层级必须大于 0");
    }

    @Override
    public Set<Long> calculateUsers(DelegateExecution execution, String param) {
        // 获得流程发起人
        ProcessInstance processInstance = processInstanceService.getProcessInstance(execution.getProcessInstanceId());
        Long startUserId = NumberUtils.parseLong(processInstance.getStartUserId());
        // 获取发起人的 multi 部门负责人
        DeptRespDTO dept = getStartUserDept(startUserId);
        if (dept == null) {
            return new HashSet<>();
        }
        Set<Long> users = getMultiLevelDeptLeaderIds(toList(dept.getId()), Integer.valueOf(param)); // 参数是部门的层级
        // TODO @jason：这里 removeDisableUsers 的原因是啥呀？
        removeDisableUsers(users);
        return users;
    }

    @Override
    public Set<Long> calculateUsers(Long startUserId, ProcessInstance processInstance, String activityId, String param) {
        DeptRespDTO dept = getStartUserDept(startUserId);
        if (dept == null) {
            return new HashSet<>();
        }
        Set<Long> users =  getMultiLevelDeptLeaderIds(toList(dept.getId()), Integer.valueOf(param)); // 参数是部门的层级
        removeDisableUsers(users);
        return users;
    }

    /**
     * 获取发起人的部门
     *
     * @param startUserId 发起人 Id
     */
    protected DeptRespDTO getStartUserDept(Long startUserId) {
        AdminUserRespDTO startUser = adminUserApi.getUser(startUserId);
        if (startUser.getDeptId() == null) { // 找不到部门
            return null;
        }
        return deptApi.getDept(startUser.getDeptId());
    }

}
