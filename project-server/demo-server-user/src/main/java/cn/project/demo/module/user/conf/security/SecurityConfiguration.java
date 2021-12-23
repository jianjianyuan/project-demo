package cn.project.demo.module.user.conf.security;

import cn.project.demo.framework.security.conf.FrameworkWebSecurityConfigurerAdapter;
import cn.project.demo.framework.web.conf.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import javax.annotation.Resource;

/**
 * 项目请求拦截规则 {@link FrameworkWebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)}
 */
@Configuration
public class SecurityConfiguration {

    @Resource
    private WebProperties webProperties;

    @Bean
    public Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry> authorizeRequestsCustomizer() {
        return registry -> {
            registry.antMatchers(api("/**")).permitAll(); // 默认 API 都是用户可访问
        };
    }

    private String api(String url) {
        return webProperties.getApiPrefix() + url;
    }
}