package my.abu.pp.module.crm.dal.redis;

/**
 * CRM Redis Key 枚举类
 *
 * @author 阿布源码
 */
public interface RedisKeyConstants {

    /**
     * 序号的缓存
     *
     * KEY 格式：trade_no:{prefix}
     * VALUE 数据格式：编号自增
     */
    String NO = "crm:seq_no:";

}
