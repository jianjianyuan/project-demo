package cn.project.demo.module.user.service;

import cn.project.demo.module.user.entity.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SystemUserService extends IService<SystemUser> {
    SystemUser getUserByUserCode(String userCode);

    SystemUser getUser(Long id);

    void updateUserLogin(Long id, String clientIp);
}