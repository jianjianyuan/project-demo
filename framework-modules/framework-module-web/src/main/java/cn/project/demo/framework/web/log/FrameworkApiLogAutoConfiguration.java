package cn.project.demo.framework.web.log;

import cn.project.demo.framework.web.core.FrameworkWebAutoConfiguration;
import cn.project.demo.framework.web.core.WebProperties;
import cn.project.demo.framework.web.core.constants.enums.WebFilterOrderEnum;
import cn.project.demo.framework.web.log.aop.OperateLogAspect;
import cn.project.demo.framework.web.log.filter.ApiAccessLogFilter;
import cn.project.demo.framework.web.log.service.ApiAccessLogFrameworkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@AutoConfigureAfter(FrameworkWebAutoConfiguration.class)
public class FrameworkApiLogAutoConfiguration {

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }
}