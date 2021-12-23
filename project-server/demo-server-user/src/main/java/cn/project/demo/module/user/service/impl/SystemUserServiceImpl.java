package cn.project.demo.module.user.service.impl;

import cn.project.demo.module.user.dao.SystemUserMapper;
import cn.project.demo.module.user.entity.SystemUser;
import cn.project.demo.module.user.service.SystemUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public SystemUser getUserByUserCode(String userCode) {
        return systemUserMapper.selectOne(new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getUserCode, userCode));
    }

    @Override
    public SystemUser getUser(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void updateUserLogin(Long id, String clientIp) {
        systemUserMapper.updateById(
                SystemUser.builder()
                        .id(id)
                        .loginIp(clientIp)
                        .loginDate(new Date())
                        .build());
    }
}