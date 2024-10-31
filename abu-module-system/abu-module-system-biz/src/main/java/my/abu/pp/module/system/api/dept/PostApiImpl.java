package my.abu.pp.module.system.api.dept;

import my.abu.pp.framework.common.util.object.BeanUtils;
import my.abu.pp.module.system.api.dept.dto.PostRespDTO;
import my.abu.pp.module.system.dal.dataobject.dept.PostDO;
import my.abu.pp.module.system.service.dept.PostService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 岗位 API 实现类
 *
 * @author 阿布源码
 */
@Service
public class PostApiImpl implements PostApi {

    @Resource
    private PostService postService;

    @Override
    public void validPostList(Collection<Long> ids) {
        postService.validatePostList(ids);
    }

    @Override
    public List<PostRespDTO> getPostList(Collection<Long> ids) {
        List<PostDO> list = postService.getPostList(ids);
        return BeanUtils.toBean(list, PostRespDTO.class);
    }

}
