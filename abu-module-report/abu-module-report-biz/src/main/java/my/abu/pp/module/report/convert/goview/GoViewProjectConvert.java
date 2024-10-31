package my.abu.pp.module.report.convert.goview;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.module.report.controller.admin.goview.vo.project.GoViewProjectCreateReqVO;
import my.abu.pp.module.report.controller.admin.goview.vo.project.GoViewProjectRespVO;
import my.abu.pp.module.report.controller.admin.goview.vo.project.GoViewProjectUpdateReqVO;
import my.abu.pp.module.report.dal.dataobject.goview.GoViewProjectDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GoViewProjectConvert {

    GoViewProjectConvert INSTANCE = Mappers.getMapper(GoViewProjectConvert.class);

    GoViewProjectDO convert(GoViewProjectCreateReqVO bean);

    GoViewProjectDO convert(GoViewProjectUpdateReqVO bean);

    GoViewProjectRespVO convert(GoViewProjectDO bean);

    PageResult<GoViewProjectRespVO> convertPage(PageResult<GoViewProjectDO> page);

}
