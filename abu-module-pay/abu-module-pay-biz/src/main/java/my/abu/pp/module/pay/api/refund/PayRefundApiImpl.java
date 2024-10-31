package my.abu.pp.module.pay.api.refund;

import my.abu.pp.module.pay.api.refund.dto.PayRefundCreateReqDTO;
import my.abu.pp.module.pay.api.refund.dto.PayRefundRespDTO;
import my.abu.pp.module.pay.convert.refund.PayRefundConvert;
import my.abu.pp.module.pay.service.refund.PayRefundService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 退款单 API 实现类
 *
 * @author 阿布源码
 */
@Service
@Validated
public class PayRefundApiImpl implements PayRefundApi {

    @Resource
    private PayRefundService payRefundService;

    @Override
    public Long createRefund(PayRefundCreateReqDTO reqDTO) {
        return payRefundService.createPayRefund(reqDTO);
    }

    @Override
    public PayRefundRespDTO getRefund(Long id) {
        return PayRefundConvert.INSTANCE.convert02(payRefundService.getRefund(id));
    }

}
