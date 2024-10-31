package my.abu.pp.module.system.convert.user;

import my.abu.pp.framework.common.util.collection.CollectionUtils;
import my.abu.pp.framework.common.util.collection.MapUtils;
import my.abu.pp.framework.common.util.object.BeanUtils;
import my.abu.pp.module.system.controller.admin.dept.vo.dept.DeptSimpleRespVO;
import my.abu.pp.module.system.controller.admin.dept.vo.post.PostSimpleRespVO;
import my.abu.pp.module.system.controller.admin.permission.vo.role.RoleSimpleRespVO;
import my.abu.pp.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import my.abu.pp.module.system.controller.admin.user.vo.user.UserRespVO;
import my.abu.pp.module.system.controller.admin.user.vo.user.UserSimpleRespVO;
import my.abu.pp.module.system.dal.dataobject.dept.DeptDO;
import my.abu.pp.module.system.dal.dataobject.dept.PostDO;
import my.abu.pp.module.system.dal.dataobject.permission.RoleDO;
import my.abu.pp.module.system.dal.dataobject.social.SocialUserDO;
import my.abu.pp.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    default List<UserRespVO> convertList(List<AdminUserDO> list, Map<Long, DeptDO> deptMap) {
        return CollectionUtils.convertList(list, user -> convert(user, deptMap.get(user.getDeptId())));
    }

    default UserRespVO convert(AdminUserDO user, DeptDO dept) {
        UserRespVO userVO = BeanUtils.toBean(user, UserRespVO.class);
        if (dept != null) {
            userVO.setDeptName(dept.getName());
        }
        return userVO;
    }

    default List<UserSimpleRespVO> convertSimpleList(List<AdminUserDO> list, Map<Long, DeptDO> deptMap) {
        return CollectionUtils.convertList(list, user -> {
            UserSimpleRespVO userVO = BeanUtils.toBean(user, UserSimpleRespVO.class);
            MapUtils.findAndThen(deptMap, user.getDeptId(), dept -> userVO.setDeptName(dept.getName()));
            return userVO;
        });
    }

    default UserProfileRespVO convert(AdminUserDO user, List<RoleDO> userRoles,
                                      DeptDO dept, List<PostDO> posts, List<SocialUserDO> socialUsers) {
        UserProfileRespVO userVO = BeanUtils.toBean(user, UserProfileRespVO.class);
        userVO.setRoles(BeanUtils.toBean(userRoles, RoleSimpleRespVO.class));
        userVO.setDept(BeanUtils.toBean(dept, DeptSimpleRespVO.class));
        userVO.setPosts(BeanUtils.toBean(posts, PostSimpleRespVO.class));
        userVO.setSocialUsers(BeanUtils.toBean(socialUsers, UserProfileRespVO.SocialUser.class));
        return userVO;
    }

}
