package cn.project.demo.module.user.service.impl;

import cn.project.demo.module.user.dao.SystemUserMapper;
import cn.project.demo.module.user.entity.SystemUser;
import cn.project.demo.module.user.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public SystemUser getUserByMobile(String mobile) {
        return null;
    }

    @Override
    public SystemUser getUser(Long id) {
        return null;
    }

    @Override
    public void updateUserLogin(Long id, String clientIP) {

    }
}