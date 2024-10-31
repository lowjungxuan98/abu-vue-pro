package my.abu.pp.framework.pay.config;

import my.abu.pp.framework.pay.core.client.PayClientFactory;
import my.abu.pp.framework.pay.core.client.impl.PayClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 支付配置类
 *
 * @author 阿布源码
 */
@AutoConfiguration
public class AbuPayAutoConfiguration {

    @Bean
    public PayClientFactory payClientFactory() {
        return new PayClientFactoryImpl();
    }

}
