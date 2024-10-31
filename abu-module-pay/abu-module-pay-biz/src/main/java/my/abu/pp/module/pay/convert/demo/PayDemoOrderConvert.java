package my.abu.pp.module.pay.convert.demo;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.pay.controller.admin.demo.vo.order.PayDemoOrderCreateReqVO;
import my.abu.pp.module.pay.controller.admin.demo.vo.order.PayDemoOrderRespVO;
import my.abu.pp.module.pay.dal.dataobject.demo.PayDemoOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 示例订单 Convert
 *
 * @author 阿布源码
 */
@Mapper
public interface PayDemoOrderConvert {

    PayDemoOrderConvert INSTANCE = Mappers.getMapper(PayDemoOrderConvert.class);

    PayDemoOrderDO convert(PayDemoOrderCreateReqVO bean);

    PayDemoOrderRespVO convert(PayDemoOrderDO bean);

    PageResult<PayDemoOrderRespVO> convertPage(PageResult<PayDemoOrderDO> page);

}
