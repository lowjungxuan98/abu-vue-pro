package my.abu.pp.module.pay.convert.wallet;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.pay.controller.admin.wallet.vo.wallet.PayWalletRespVO;
import my.abu.pp.module.pay.controller.app.wallet.vo.wallet.AppPayWalletRespVO;
import my.abu.pp.module.pay.dal.dataobject.wallet.PayWalletDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletConvert {

    PayWalletConvert INSTANCE = Mappers.getMapper(PayWalletConvert.class);

    AppPayWalletRespVO convert(PayWalletDO bean);

    PayWalletRespVO convert02(PayWalletDO bean);

    PageResult<PayWalletRespVO> convertPage(PageResult<PayWalletDO> page);

}
