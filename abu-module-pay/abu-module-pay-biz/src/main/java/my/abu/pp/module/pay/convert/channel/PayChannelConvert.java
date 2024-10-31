package my.abu.pp.module.pay.convert.channel;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.pay.controller.admin.channel.vo.PayChannelCreateReqVO;
import my.abu.pp.module.pay.controller.admin.channel.vo.PayChannelRespVO;
import my.abu.pp.module.pay.controller.admin.channel.vo.PayChannelUpdateReqVO;
import my.abu.pp.module.pay.dal.dataobject.channel.PayChannelDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayChannelConvert {

    PayChannelConvert INSTANCE = Mappers.getMapper(PayChannelConvert.class);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelCreateReqVO bean);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelUpdateReqVO bean);

    @Mapping(target = "config",expression = "java(my.abu.pp.framework.common.util.json.JsonUtils.toJsonString(bean.getConfig()))")
    PayChannelRespVO convert(PayChannelDO bean);

    PageResult<PayChannelRespVO> convertPage(PageResult<PayChannelDO> page);

}
