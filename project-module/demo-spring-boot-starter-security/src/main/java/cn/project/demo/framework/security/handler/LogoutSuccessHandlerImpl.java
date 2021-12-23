package cn.project.demo.framework.security.handler;

import cn.hutool.core.util.StrUtil;
import cn.project.demo.framework.common.pojo.CommonResult;
import cn.project.demo.framework.common.util.servlet.ServletUtils;
import cn.project.demo.framework.security.conf.SecurityProperties;
import cn.project.demo.framework.security.service.SecurityAuthFrameworkService;
import cn.project.demo.framework.security.util.SecurityFrameworkUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义退出处理器
 */
@AllArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final SecurityProperties securityProperties;

    private final SecurityAuthFrameworkService securityFrameworkService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 执行退出
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            securityFrameworkService.logout(token);
        }
        // 返回成功
        ServletUtils.writeJSON(response, CommonResult.success(null));
    }

}
