package com.magneton.we.api.starter;

import com.magneton.we.api.WeApiConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 微信公众号的API配置
 *
 * @author zhangmingshuang
 * @see WeApiConfig
 * @since 2019/9/3
 */
@Setter
@Getter
@Component
@PropertySource(value = "classpath:we-dispatcher.properties")
@ConfigurationProperties(prefix = "we.dispatcher")
public class DispatcherWeProperties extends WeApiConfig {

}
