package my.abu.pp.framework.idempotent.config;

import my.abu.pp.framework.idempotent.core.aop.IdempotentAspect;
import my.abu.pp.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import my.abu.pp.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import my.abu.pp.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import my.abu.pp.framework.idempotent.core.keyresolver.impl.UserIdempotentKeyResolver;
import my.abu.pp.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import my.abu.pp.framework.redis.config.AbuRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = AbuRedisAutoConfiguration.class)
public class AbuIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public UserIdempotentKeyResolver userIdempotentKeyResolver() {
        return new UserIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
