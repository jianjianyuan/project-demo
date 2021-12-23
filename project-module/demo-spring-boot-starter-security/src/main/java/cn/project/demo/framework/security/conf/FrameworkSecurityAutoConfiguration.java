package cn.project.demo.framework.security.conf;

import cn.project.demo.framework.security.filter.JWTAuthenticationTokenFilter;
import cn.project.demo.framework.security.handler.AuthenticationEntryPointImpl;
import cn.project.demo.framework.security.handler.LogoutSuccessHandlerImpl;
import cn.project.demo.framework.security.service.SecurityAuthFrameworkService;
import cn.project.demo.framework.web.handler.GlobalExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;

/**
 * Spring Security 自动配置类
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class FrameworkSecurityAutoConfiguration {

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 处理用户未登录拦截的切面的 Bean
     */
    /*@Bean
    public PreAuthenticatedAspect preAuthenticatedAspect() {
        return new PreAuthenticatedAspect();
    }*/

    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    /**
     * 退出处理类 Bean
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(SecurityAuthFrameworkService securityFrameworkService) {
        return new LogoutSuccessHandlerImpl(securityProperties, securityFrameworkService);
    }

    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     *
     * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding with Spring Security</a>
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Token 认证过滤器 Bean
     */
    @Bean
    public JWTAuthenticationTokenFilter authenticationTokenFilter(SecurityAuthFrameworkService securityFrameworkService,
                                                                  GlobalExceptionHandler globalExceptionHandler) {
        return new JWTAuthenticationTokenFilter(securityProperties, securityFrameworkService, globalExceptionHandler);
    }

}
