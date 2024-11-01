package my.abu.pp.module.promotion.service.banner;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.promotion.controller.admin.banner.vo.BannerCreateReqVO;
import my.abu.pp.module.promotion.controller.admin.banner.vo.BannerPageReqVO;
import my.abu.pp.module.promotion.controller.admin.banner.vo.BannerUpdateReqVO;
import my.abu.pp.module.promotion.convert.banner.BannerConvert;
import my.abu.pp.module.promotion.dal.dataobject.banner.BannerDO;
import my.abu.pp.module.promotion.dal.mysql.banner.BannerMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

import static my.abu.pp.framework.common.exception.util.ServiceExceptionUtil.exception;
import static my.abu.pp.module.promotion.enums.ErrorCodeConstants.BANNER_NOT_EXISTS;

/**
 * 首页 banner 实现类
 *
 * @author xia
 */
@Service
@Validated
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public Long createBanner(BannerCreateReqVO createReqVO) {
        // 插入
        BannerDO banner = BannerConvert.INSTANCE.convert(createReqVO);
        bannerMapper.insert(banner);
        // 返回
        return banner.getId();
    }

    @Override
    public void updateBanner(BannerUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBannerExists(updateReqVO.getId());
        // 更新
        BannerDO updateObj = BannerConvert.INSTANCE.convert(updateReqVO);
        bannerMapper.updateById(updateObj);
    }

    @Override
    public void deleteBanner(Long id) {
        // 校验存在
        this.validateBannerExists(id);
        // 删除
        bannerMapper.deleteById(id);
    }

    private void validateBannerExists(Long id) {
        if (bannerMapper.selectById(id) == null) {
            throw exception(BANNER_NOT_EXISTS);
        }
    }

    @Override
    public BannerDO getBanner(Long id) {
        return bannerMapper.selectById(id);
    }

    @Override
    public PageResult<BannerDO> getBannerPage(BannerPageReqVO pageReqVO) {
        return bannerMapper.selectPage(pageReqVO);
    }

    @Override
    public void addBannerBrowseCount(Long id) {
        // 校验 Banner 是否存在
        validateBannerExists(id);
        // 增加点击次数
        bannerMapper.updateBrowseCount(id);
    }

    @Override
    public List<BannerDO> getBannerListByPosition(Integer position) {
        return bannerMapper.selectBannerListByPosition(position);
    }

}
