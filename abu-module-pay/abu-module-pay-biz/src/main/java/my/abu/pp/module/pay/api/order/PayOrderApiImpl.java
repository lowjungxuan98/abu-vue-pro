package my.abu.pp.module.pay.api.order;

import my.abu.pp.module.pay.api.order.dto.PayOrderCreateReqDTO;
import my.abu.pp.module.pay.api.order.dto.PayOrderRespDTO;
import my.abu.pp.module.pay.convert.order.PayOrderConvert;
import my.abu.pp.module.pay.dal.dataobject.order.PayOrderDO;
import my.abu.pp.module.pay.service.order.PayOrderService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 支付单 API 实现类
 *
 * @author 阿布源码
 */
@Service
public class PayOrderApiImpl implements PayOrderApi {

    @Resource
    private PayOrderService payOrderService;

    @Override
    public Long createOrder(PayOrderCreateReqDTO reqDTO) {
        return payOrderService.createOrder(reqDTO);
    }

    @Override
    public PayOrderRespDTO getOrder(Long id) {
        PayOrderDO order = payOrderService.getOrder(id);
        return PayOrderConvert.INSTANCE.convert2(order);
    }

    @Override
    public void updatePayOrderPrice(Long id, Integer payPrice) {
        payOrderService.updatePayOrderPrice(id, payPrice);
    }

}
