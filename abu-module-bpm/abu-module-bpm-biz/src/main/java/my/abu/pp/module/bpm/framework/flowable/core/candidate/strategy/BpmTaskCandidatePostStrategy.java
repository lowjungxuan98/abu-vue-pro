package my.abu.pp.module.bpm.framework.flowable.core.candidate.strategy;

import my.abu.pp.framework.common.util.string.StrUtils;
import my.abu.pp.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import my.abu.pp.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import my.abu.pp.module.system.api.dept.PostApi;
import my.abu.pp.module.system.api.user.AdminUserApi;
import my.abu.pp.module.system.api.user.dto.AdminUserRespDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static my.abu.pp.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * 岗位 {@link BpmTaskCandidateStrategy} 实现类
 *
 * @author kyle
 */
@Component
public class BpmTaskCandidatePostStrategy extends BpmTaskCandidateAbstractStrategy {

    private final PostApi postApi;

    public BpmTaskCandidatePostStrategy(AdminUserApi adminUserApi, PostApi postApi) {
        super(adminUserApi);
        this.postApi = postApi;
    }

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.POST;
    }

    @Override
    public void validateParam(String param) {
        Set<Long> postIds = StrUtils.splitToLongSet(param);
        postApi.validPostList(postIds);
    }

    @Override
    public Set<Long> calculateUsers(String param) {
        Set<Long> postIds = StrUtils.splitToLongSet(param);
        List<AdminUserRespDTO> users = adminUserApi.getUserListByPostIds(postIds);
        return convertSet(users, AdminUserRespDTO::getId);
    }

}