package my.abu.pp.module.ai.dal.mysql.chat;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.framework.mybatis.core.mapper.BaseMapperX;
import my.abu.pp.framework.mybatis.core.query.LambdaQueryWrapperX;
import my.abu.pp.module.ai.controller.admin.chat.vo.conversation.AiChatConversationPageReqVO;
import my.abu.pp.module.ai.dal.dataobject.chat.AiChatConversationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AI 聊天对话 Mapper
 *
 * @author 阿布源码
 */
@Mapper
public interface AiChatConversationMapper extends BaseMapperX<AiChatConversationDO> {

    default List<AiChatConversationDO> selectListByUserId(Long userId) {
        return selectList(AiChatConversationDO::getUserId, userId);
    }

    default List<AiChatConversationDO> selectListByUserIdAndPinned(Long userId, boolean pinned) {
        return selectList(new LambdaQueryWrapperX<AiChatConversationDO>()
                .eq(AiChatConversationDO::getUserId, userId)
                .eq(AiChatConversationDO::getPinned, pinned));
    }

    default PageResult<AiChatConversationDO> selectChatConversationPage(AiChatConversationPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<AiChatConversationDO>()
                .eqIfPresent(AiChatConversationDO::getUserId, pageReqVO.getUserId())
                .likeIfPresent(AiChatConversationDO::getTitle, pageReqVO.getTitle())
                .betweenIfPresent(AiChatConversationDO::getCreateTime, pageReqVO.getCreateTime())
                .orderByDesc(AiChatConversationDO::getId));
    }

}
