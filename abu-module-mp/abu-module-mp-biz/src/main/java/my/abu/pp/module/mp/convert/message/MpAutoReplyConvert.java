package my.abu.pp.module.mp.convert.message;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyCreateReqVO;
import my.abu.pp.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyRespVO;
import my.abu.pp.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyUpdateReqVO;
import my.abu.pp.module.mp.dal.dataobject.message.MpAutoReplyDO;
import my.abu.pp.module.mp.service.message.bo.MpMessageSendOutReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MpAutoReplyConvert {

    MpAutoReplyConvert INSTANCE = Mappers.getMapper(MpAutoReplyConvert.class);

    @Mappings({
            @Mapping(source = "reply.appId", target = "appId"),
            @Mapping(source = "reply.responseMessageType", target = "type"),
            @Mapping(source = "reply.responseContent", target = "content"),
            @Mapping(source = "reply.responseMediaId", target = "mediaId"),
            @Mapping(source = "reply.responseTitle", target = "title"),
            @Mapping(source = "reply.responseDescription", target = "description"),
            @Mapping(source = "reply.responseArticles", target = "articles"),
    })
    MpMessageSendOutReqBO convert(String openid, MpAutoReplyDO reply);

    PageResult<MpAutoReplyRespVO> convertPage(PageResult<MpAutoReplyDO> page);

    MpAutoReplyRespVO convert(MpAutoReplyDO bean);

    MpAutoReplyDO convert(MpAutoReplyCreateReqVO bean);

    MpAutoReplyDO convert(MpAutoReplyUpdateReqVO bean);
}
