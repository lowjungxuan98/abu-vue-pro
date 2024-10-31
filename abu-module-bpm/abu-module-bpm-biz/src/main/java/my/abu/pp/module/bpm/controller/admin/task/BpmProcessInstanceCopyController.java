package my.abu.pp.module.bpm.controller.admin.task;

import cn.hutool.core.collection.CollUtil;
import my.abu.pp.framework.common.pojo.CommonResult;
import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.framework.common.util.collection.MapUtils;
import my.abu.pp.framework.common.util.date.DateUtils;
import my.abu.pp.framework.common.util.object.BeanUtils;
import my.abu.pp.module.bpm.controller.admin.task.vo.cc.BpmProcessInstanceCopyRespVO;
import my.abu.pp.module.bpm.controller.admin.task.vo.instance.BpmProcessInstanceCopyPageReqVO;
import my.abu.pp.module.bpm.dal.dataobject.task.BpmProcessInstanceCopyDO;
import my.abu.pp.module.bpm.service.task.BpmProcessInstanceCopyService;
import my.abu.pp.module.bpm.service.task.BpmProcessInstanceService;
import my.abu.pp.module.system.api.user.AdminUserApi;
import my.abu.pp.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.flowable.engine.history.HistoricProcessInstance;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Stream;

import static my.abu.pp.framework.common.pojo.CommonResult.success;
import static my.abu.pp.framework.common.util.collection.CollectionUtils.convertListByFlatMap;
import static my.abu.pp.framework.common.util.collection.CollectionUtils.convertSet;
import static my.abu.pp.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 流程实例抄送")
@RestController
@RequestMapping("/bpm/process-instance/copy")
@Validated
public class BpmProcessInstanceCopyController {

    @Resource
    private BpmProcessInstanceCopyService processInstanceCopyService;
    @Resource
    private BpmProcessInstanceService processInstanceService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得抄送流程分页列表")
    @PreAuthorize("@ss.hasPermission('bpm:process-instance-cc:query')")
    public CommonResult<PageResult<BpmProcessInstanceCopyRespVO>> getProcessInstanceCopyPage(
            @Valid BpmProcessInstanceCopyPageReqVO pageReqVO) {
        PageResult<BpmProcessInstanceCopyDO> pageResult = processInstanceCopyService.getProcessInstanceCopyPage(
                getLoginUserId(), pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }

        // 拼接返回
        Map<String, HistoricProcessInstance> processInstanceMap = processInstanceService.getHistoricProcessInstanceMap(
                convertSet(pageResult.getList(), BpmProcessInstanceCopyDO::getProcessInstanceId));
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertListByFlatMap(pageResult.getList(),
                copy -> Stream.of(copy.getStartUserId(), Long.parseLong(copy.getCreator()))));
        return success(BeanUtils.toBean(pageResult, BpmProcessInstanceCopyRespVO.class, copyVO -> {
            MapUtils.findAndThen(userMap, Long.valueOf(copyVO.getCreator()), user -> copyVO.setCreatorName(user.getNickname()));
            MapUtils.findAndThen(userMap, copyVO.getStartUserId(), user -> copyVO.setStartUserName(user.getNickname()));
            MapUtils.findAndThen(processInstanceMap, copyVO.getProcessInstanceId(),
                    processInstance -> copyVO.setProcessInstanceStartTime(DateUtils.of(processInstance.getStartTime())));
        }));
    }

}
