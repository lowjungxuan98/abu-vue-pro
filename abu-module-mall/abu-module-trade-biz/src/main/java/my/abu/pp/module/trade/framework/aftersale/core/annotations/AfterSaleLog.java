package my.abu.pp.module.trade.framework.aftersale.core.annotations;

import my.abu.pp.module.trade.enums.aftersale.AfterSaleOperateTypeEnum;
import my.abu.pp.module.trade.framework.aftersale.core.aop.AfterSaleLogAspect;

import java.lang.annotation.*;

/**
 * 售后日志的注解
 *
 * 写在方法上时，会自动记录售后日志
 *
 * @author 陈賝
 * @since 2023/6/8 17:04
 * @see AfterSaleLogAspect
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AfterSaleLog {

    /**
     * 操作类型
     */
    AfterSaleOperateTypeEnum operateType();

}
