package my.abu.pp.module.statistics.dal.mysql.trade;

import my.abu.pp.framework.mybatis.core.mapper.BaseMapperX;
import my.abu.pp.framework.mybatis.core.query.LambdaQueryWrapperX;
import my.abu.pp.module.statistics.controller.admin.trade.vo.TradeTrendSummaryRespVO;
import my.abu.pp.module.statistics.dal.dataobject.trade.TradeStatisticsDO;
import my.abu.pp.module.statistics.service.trade.bo.TradeSummaryRespBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 交易统计 Mapper
 *
 * @author owen
 */
@Mapper
public interface TradeStatisticsMapper extends BaseMapperX<TradeStatisticsDO> {

    TradeSummaryRespBO selectOrderCreateCountSumAndOrderPayPriceSumByTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                                                 @Param("endTime") LocalDateTime endTime);

    TradeTrendSummaryRespVO selectVoByTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                  @Param("endTime") LocalDateTime endTime);

    default List<TradeStatisticsDO> selectListByTimeBetween(LocalDateTime beginTime, LocalDateTime endTime) {
        return selectList(new LambdaQueryWrapperX<TradeStatisticsDO>()
                .between(TradeStatisticsDO::getTime, beginTime, endTime));
    }

    Integer selectExpensePriceByTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                            @Param("endTime") LocalDateTime endTime);

    default TradeStatisticsDO selectByTimeBetween(LocalDateTime beginTime, LocalDateTime endTime) {
        return selectOne(new LambdaQueryWrapperX<TradeStatisticsDO>()
                .between(TradeStatisticsDO::getTime, beginTime, endTime));
    }

}
