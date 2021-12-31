package cn.project.demo.module.auth.dao;

import cn.project.demo.framework.common.util.json.JsonUtils;
import cn.project.demo.framework.security.conf.SecurityProperties;
import cn.project.demo.framework.security.pojo.LoginUser;
import cn.project.demo.module.auth.constants.SysRedisKeyCoreConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


/**
 * {@link LoginUser} 的 RedisDAO
 */
@Repository
public class LoginUserRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SecurityProperties securityProperties;

    public LoginUser getBySessionId(String sessionId) {
        String redisKey = formatKey(sessionId);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), LoginUser.class);
    }

    public void save(String sessionId, LoginUser loginUser) {
        String redisKey = formatKey(sessionId);
        stringRedisTemplate.opsForValue().set(
                redisKey,
                JsonUtils.toJsonString(loginUser),
                securityProperties.getSessionTimeout());
    }

    public void delete(String sessionId) {
        String redisKey = formatKey(sessionId);
        stringRedisTemplate.delete(redisKey);
    }

    private static String formatKey(String sessionId) {
        return String.format(SysRedisKeyCoreConstants.LOGIN_USER.getKeyTemplate(), sessionId);
    }

}
