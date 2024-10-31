package my.abu.pp.module.system.dal.mysql.group;

import java.util.*;

import my.abu.pp.framework.common.pojo.PageResult;
import my.abu.pp.framework.mybatis.core.query.LambdaQueryWrapperX;
import my.abu.pp.framework.mybatis.core.mapper.BaseMapperX;
import my.abu.pp.module.system.dal.dataobject.group.GroupDO;
import org.apache.ibatis.annotations.Mapper;
import my.abu.pp.module.system.controller.admin.group.vo.*;

/**
 * 用户组 Mapper
 *
 * @author 阿布源码
 */
@Mapper
public interface GroupMapper extends BaseMapperX<GroupDO> {

    default PageResult<GroupDO> selectPage(GroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GroupDO>()
                .likeIfPresent(GroupDO::getName, reqVO.getName())
                .eqIfPresent(GroupDO::getDescription, reqVO.getDescription())
                .eqIfPresent(GroupDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(GroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GroupDO::getId));
    }

}
