package my.abu.pp.module.promotion.service.coupon;

import my.abu.pp.framework.common.enums.CommonStatusEnum;
import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.product.api.category.ProductCategoryApi;
import my.abu.pp.module.product.api.spu.ProductSpuApi;
import my.abu.pp.module.promotion.controller.admin.coupon.vo.template.CouponTemplateCreateReqVO;
import my.abu.pp.module.promotion.controller.admin.coupon.vo.template.CouponTemplatePageReqVO;
import my.abu.pp.module.promotion.controller.admin.coupon.vo.template.CouponTemplateUpdateReqVO;
import my.abu.pp.module.promotion.convert.coupon.CouponTemplateConvert;
import my.abu.pp.module.promotion.dal.dataobject.coupon.CouponTemplateDO;
import my.abu.pp.module.promotion.dal.mysql.coupon.CouponTemplateMapper;
import my.abu.pp.module.promotion.enums.common.PromotionProductScopeEnum;
import my.abu.pp.module.promotion.enums.coupon.CouponTakeTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static my.abu.pp.framework.common.exception.util.ServiceExceptionUtil.exception;
import static my.abu.pp.module.promotion.enums.ErrorCodeConstants.COUPON_TEMPLATE_NOT_EXISTS;
import static my.abu.pp.module.promotion.enums.ErrorCodeConstants.COUPON_TEMPLATE_TOTAL_COUNT_TOO_SMALL;

/**
 * 优惠劵模板 Service 实现类
 *
 * @author 阿布源码
 */
@Service
@Validated
public class CouponTemplateServiceImpl implements CouponTemplateService {

    @Resource
    private CouponTemplateMapper couponTemplateMapper;

    @Resource
    private ProductCategoryApi productCategoryApi;
    @Resource
    private ProductSpuApi productSpuApi;

    @Override
    public Long createCouponTemplate(CouponTemplateCreateReqVO createReqVO) {
        // 校验商品范围
        validateProductScope(createReqVO.getProductScope(), createReqVO.getProductScopeValues());
        // 插入
        CouponTemplateDO couponTemplate = CouponTemplateConvert.INSTANCE.convert(createReqVO)
                .setStatus(CommonStatusEnum.ENABLE.getStatus());
        couponTemplateMapper.insert(couponTemplate);
        // 返回
        return couponTemplate.getId();
    }

    @Override
    public void updateCouponTemplate(CouponTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        CouponTemplateDO couponTemplate = validateCouponTemplateExists(updateReqVO.getId());
        // 校验发放数量不能过小（仅在 CouponTakeTypeEnum.USER 用户领取时）
        if (CouponTakeTypeEnum.isUser(couponTemplate.getTakeType())
                && updateReqVO.getTotalCount() > 0 // 大于 0 的原因，是因为 -1 不限制
                && updateReqVO.getTotalCount() < couponTemplate.getTakeCount()) {
            throw exception(COUPON_TEMPLATE_TOTAL_COUNT_TOO_SMALL, couponTemplate.getTakeCount());
        }
        // 校验商品范围
        validateProductScope(updateReqVO.getProductScope(), updateReqVO.getProductScopeValues());

        // 更新
        CouponTemplateDO updateObj = CouponTemplateConvert.INSTANCE.convert(updateReqVO);
        couponTemplateMapper.updateById(updateObj);
    }

    @Override
    public void updateCouponTemplateStatus(Long id, Integer status) {
        // 校验存在
        validateCouponTemplateExists(id);
        // 更新
        couponTemplateMapper.updateById(new CouponTemplateDO().setId(id).setStatus(status));
    }

    @Override
    public void deleteCouponTemplate(Long id) {
        // 校验存在
        validateCouponTemplateExists(id);
        // 删除
        couponTemplateMapper.deleteById(id);
    }

    private CouponTemplateDO validateCouponTemplateExists(Long id) {
        CouponTemplateDO couponTemplate = couponTemplateMapper.selectById(id);
        if (couponTemplate == null) {
            throw exception(COUPON_TEMPLATE_NOT_EXISTS);
        }
        return couponTemplate;
    }

    private void validateProductScope(Integer productScope, List<Long> productScopeValues) {
        if (Objects.equals(PromotionProductScopeEnum.SPU.getScope(), productScope)) {
            productSpuApi.validateSpuList(productScopeValues);
        } else if (Objects.equals(PromotionProductScopeEnum.CATEGORY.getScope(), productScope)) {
            productCategoryApi.validateCategoryList(productScopeValues);
        }
    }

    @Override
    public CouponTemplateDO getCouponTemplate(Long id) {
        return couponTemplateMapper.selectById(id);
    }

    @Override
    public PageResult<CouponTemplateDO> getCouponTemplatePage(CouponTemplatePageReqVO pageReqVO) {
        return couponTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateCouponTemplateTakeCount(Long id, int incrCount) {
        couponTemplateMapper.updateTakeCount(id, incrCount);
    }

    @Override
    public List<CouponTemplateDO> getCouponTemplateListByTakeType(CouponTakeTypeEnum takeType) {
        return couponTemplateMapper.selectListByTakeType(takeType.getType());
    }

    @Override
    public List<CouponTemplateDO> getCouponTemplateList(List<Integer> canTakeTypes, Integer productScope,
                                                        Long productScopeValue, Integer count) {
        return couponTemplateMapper.selectList(canTakeTypes, productScope, productScopeValue, count);
    }

    @Override
    public List<CouponTemplateDO> getCouponTemplateList(Collection<Long> ids) {
        return couponTemplateMapper.selectBatchIds(ids);
    }

}
