package my.abu.pp.module.pay.convert.wallet;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionRespVO;
import my.abu.pp.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionRespVO;
import my.abu.pp.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import my.abu.pp.module.pay.service.wallet.bo.WalletTransactionCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletTransactionConvert {

    PayWalletTransactionConvert INSTANCE = Mappers.getMapper(PayWalletTransactionConvert.class);

    PageResult<PayWalletTransactionRespVO> convertPage2(PageResult<PayWalletTransactionDO> page);

    PayWalletTransactionDO convert(WalletTransactionCreateReqBO bean);

}
