package my.abu.pp.module.pay.convert.demo;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import my.abu.pp.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferRespVO;
import my.abu.pp.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jason
 */
@Mapper
public interface PayDemoTransferConvert {

    PayDemoTransferConvert INSTANCE = Mappers.getMapper(PayDemoTransferConvert.class);

    PayDemoTransferDO convert(PayDemoTransferCreateReqVO bean);

    PageResult<PayDemoTransferRespVO> convertPage(PageResult<PayDemoTransferDO> pageResult);
}
