package my.abu.pp.module.trade.dal.mysql.config;

import my.abu.pp.framework.mybatis.core.mapper.BaseMapperX;
import my.abu.pp.module.trade.dal.dataobject.config.TradeConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 交易中心配置 Mapper
 *
 * @author owen
 */
@Mapper
public interface TradeConfigMapper extends BaseMapperX<TradeConfigDO> {

}
