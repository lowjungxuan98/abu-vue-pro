package my.abu.pp.module.member.convert.tag;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.member.controller.admin.tag.vo.MemberTagCreateReqVO;
import my.abu.pp.module.member.controller.admin.tag.vo.MemberTagRespVO;
import my.abu.pp.module.member.controller.admin.tag.vo.MemberTagUpdateReqVO;
import my.abu.pp.module.member.dal.dataobject.tag.MemberTagDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员标签 Convert
 *
 * @author 阿布源码
 */
@Mapper
public interface MemberTagConvert {

    MemberTagConvert INSTANCE = Mappers.getMapper(MemberTagConvert.class);

    MemberTagDO convert(MemberTagCreateReqVO bean);

    MemberTagDO convert(MemberTagUpdateReqVO bean);

    MemberTagRespVO convert(MemberTagDO bean);

    List<MemberTagRespVO> convertList(List<MemberTagDO> list);

    PageResult<MemberTagRespVO> convertPage(PageResult<MemberTagDO> page);

}
