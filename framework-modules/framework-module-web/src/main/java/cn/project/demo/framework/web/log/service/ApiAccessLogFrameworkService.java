package cn.project.demo.framework.web.log.service;


import cn.project.demo.framework.web.log.dto.ApiAccessLogCreateReqDTO;

import javax.validation.Valid;

/**
 * API 访问日志 Framework Service 接口
 */
public interface ApiAccessLogFrameworkService {

    /**
     * 创建 API 访问日志
     *
     * @param createDTO 创建信息
     */
    void createApiAccessLogAsync(@Valid ApiAccessLogCreateReqDTO createDTO);

}
