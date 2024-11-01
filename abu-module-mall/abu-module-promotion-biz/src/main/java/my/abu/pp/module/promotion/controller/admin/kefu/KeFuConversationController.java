package my.abu.pp.module.promotion.controller.admin.kefu;

import my.abu.pp.framework.common.pojo.CommonResult;
import my.abu.pp.framework.common.util.object.BeanUtils;
import my.abu.pp.module.member.api.user.MemberUserApi;
import my.abu.pp.module.member.api.user.dto.MemberUserRespDTO;
import my.abu.pp.module.promotion.controller.admin.kefu.vo.conversation.KeFuConversationRespVO;
import my.abu.pp.module.promotion.controller.admin.kefu.vo.conversation.KeFuConversationUpdatePinnedReqVO;
import my.abu.pp.module.promotion.service.kefu.KeFuConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static my.abu.pp.framework.common.pojo.CommonResult.success;
import static my.abu.pp.framework.common.util.collection.CollectionUtils.convertSet;
import static my.abu.pp.framework.common.util.collection.MapUtils.findAndThen;

@Tag(name = "管理后台 - 客服会话")
@RestController
@RequestMapping("/promotion/kefu-conversation")
@Validated
public class KeFuConversationController {

    @Resource
    private KeFuConversationService conversationService;
    @Resource
    private MemberUserApi memberUserApi;

    @PutMapping("/update-conversation-pinned")
    @Operation(summary = "置顶/取消置顶客服会话")
    @PreAuthorize("@ss.hasPermission('promotion:kefu-conversation:update')")
    public CommonResult<Boolean> updateConversationPinned(@Valid @RequestBody KeFuConversationUpdatePinnedReqVO updateReqVO) {
        conversationService.updateConversationPinnedByAdmin(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客服会话")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('promotion:kefu-conversation:delete')")
    public CommonResult<Boolean> deleteConversation(@RequestParam("id") Long id) {
        conversationService.deleteKefuConversation(id);
        return success(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获得客服会话列表")
    @PreAuthorize("@ss.hasPermission('promotion:kefu-conversation:query')")
    public CommonResult<List<KeFuConversationRespVO>> getConversationList() {
        // 查询会话列表
        List<KeFuConversationRespVO> respList = BeanUtils.toBean(conversationService.getKefuConversationList(),
                KeFuConversationRespVO.class);

        // 拼接数据
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(convertSet(respList, KeFuConversationRespVO::getUserId));
        respList.forEach(item-> findAndThen(userMap, item.getUserId(),
                memberUser-> item.setUserAvatar(memberUser.getAvatar()).setUserNickname(memberUser.getNickname())));
        return success(respList);
    }

}