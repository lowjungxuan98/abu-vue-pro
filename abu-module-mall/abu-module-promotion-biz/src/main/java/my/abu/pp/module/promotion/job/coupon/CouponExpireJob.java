package my.abu.pp.module.promotion.job.coupon;

import cn.hutool.core.util.StrUtil;
import my.abu.pp.framework.quartz.core.handler.JobHandler;
import my.abu.pp.framework.tenant.core.job.TenantJob;
import my.abu.pp.module.promotion.service.coupon.CouponService;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 优惠券过期 Job
 *
 * @author owen
 */
@Component
public class CouponExpireJob implements JobHandler {

    @Resource
    private CouponService couponService;

    @Override
    @TenantJob
    public String execute(String param) {
        int count = couponService.expireCoupon();
        return StrUtil.format("过期优惠券 {} 个", count);
    }

}
