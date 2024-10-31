package my.abu.pp.module.system.controller.admin.group;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import my.abu.pp.framework.common.pojo.PageParam;
import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.framework.common.pojo.CommonResult;
import my.abu.pp.framework.common.util.object.BeanUtils;
import static my.abu.pp.framework.common.pojo.CommonResult.success;

import my.abu.pp.framework.excel.core.util.ExcelUtils;

import my.abu.pp.framework.apilog.core.annotation.ApiAccessLog;
import static my.abu.pp.framework.apilog.core.enums.OperateTypeEnum.*;

import my.abu.pp.module.system.controller.admin.group.vo.*;
import my.abu.pp.module.system.dal.dataobject.group.GroupDO;
import my.abu.pp.module.system.service.group.GroupService;

@Tag(name = "管理后台 - 用户组")
@RestController
@RequestMapping("/system/group")
@Validated
public class GroupController {

    @Resource
    private GroupService groupService;

    @PostMapping("/create")
    @Operation(summary = "创建用户组")
    @PreAuthorize("@ss.hasPermission('system:group:create')")
    public CommonResult<Long> createGroup(@Valid @RequestBody GroupSaveReqVO createReqVO) {
        return success(groupService.createGroup(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户组")
    @PreAuthorize("@ss.hasPermission('system:group:update')")
    public CommonResult<Boolean> updateGroup(@Valid @RequestBody GroupSaveReqVO updateReqVO) {
        groupService.updateGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:group:delete')")
    public CommonResult<Boolean> deleteGroup(@RequestParam("id") Long id) {
        groupService.deleteGroup(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:group:query')")
    public CommonResult<GroupRespVO> getGroup(@RequestParam("id") Long id) {
        GroupDO group = groupService.getGroup(id);
        return success(BeanUtils.toBean(group, GroupRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户组分页")
    @PreAuthorize("@ss.hasPermission('system:group:query')")
    public CommonResult<PageResult<GroupRespVO>> getGroupPage(@Valid GroupPageReqVO pageReqVO) {
        PageResult<GroupDO> pageResult = groupService.getGroupPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GroupRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户组 Excel")
    @PreAuthorize("@ss.hasPermission('system:group:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGroupExcel(@Valid GroupPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GroupDO> list = groupService.getGroupPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户组.xls", "数据", GroupRespVO.class,
                        BeanUtils.toBean(list, GroupRespVO.class));
    }

}