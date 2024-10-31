package my.abu.pp.module.infra.dal.mysql.db;

import my.abu.pp.framework.mybatis.core.mapper.BaseMapperX;
import my.abu.pp.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author 阿布源码
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
