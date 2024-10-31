package my.abu.pp.module.bpm.framework.flowable.core.candidate.strategy;

import my.abu.pp.framework.test.core.ut.BaseMockitoUnitTest;
import my.abu.pp.module.system.api.dept.DeptApi;
import my.abu.pp.module.system.api.dept.dto.DeptRespDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Set;

import static my.abu.pp.framework.common.util.collection.SetUtils.asSet;
import static my.abu.pp.framework.test.core.util.RandomUtils.randomPojo;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Disabled // TODO 芋艿：临时注释
public class BpmTaskCandidateDeptLeaderStrategyTest extends BaseMockitoUnitTest {

    @InjectMocks
    private BpmTaskCandidateDeptLeaderStrategy strategy;

    @Mock
    private DeptApi deptApi;

    @Test
    public void testCalculateUsers() {
        // 准备参数
        String param = "1,2";
        // mock 方法
        DeptRespDTO dept1 = randomPojo(DeptRespDTO.class, o -> o.setLeaderUserId(11L));
        DeptRespDTO dept2 = randomPojo(DeptRespDTO.class, o -> o.setLeaderUserId(22L));
        when(deptApi.getDeptList(eq(asSet(1L, 2L)))).thenReturn(asList(dept1, dept2));

        // 调用
        Set<Long> results = strategy.calculateUsers(null, param);
        // 断言
        assertEquals(asSet(11L, 22L), results);
    }

}
