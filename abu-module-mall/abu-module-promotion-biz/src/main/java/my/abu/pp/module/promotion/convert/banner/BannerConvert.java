package my.abu.pp.module.promotion.convert.banner;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.promotion.controller.admin.banner.vo.BannerCreateReqVO;
import my.abu.pp.module.promotion.controller.admin.banner.vo.BannerRespVO;
import my.abu.pp.module.promotion.controller.admin.banner.vo.BannerUpdateReqVO;
import my.abu.pp.module.promotion.controller.app.banner.vo.AppBannerRespVO;
import my.abu.pp.module.promotion.dal.dataobject.banner.BannerDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    List<BannerRespVO> convertList(List<BannerDO> list);

    PageResult<BannerRespVO> convertPage(PageResult<BannerDO> pageResult);

    BannerRespVO convert(BannerDO banner);

    BannerDO convert(BannerCreateReqVO createReqVO);

    BannerDO convert(BannerUpdateReqVO updateReqVO);

    List<AppBannerRespVO> convertList01(List<BannerDO> bannerList);

}
