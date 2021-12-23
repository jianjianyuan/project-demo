package cn.project.demo.module.auth.constants;

import cn.project.demo.framework.redis.RedisKeyDefine;
import cn.project.demo.framework.security.pojo.LoginUser;
import me.zhyd.oauth.model.AuthUser;

import java.time.Duration;


/**
 * System Redis Key 枚举类
 */
public interface SysRedisKeyCoreConstants {

    RedisKeyDefine LOGIN_USER = new RedisKeyDefine("登录用户的缓存",
            "login_user:%s", // 参数为 sessionId
            RedisKeyDefine.KeyTypeEnum.STRING, LoginUser.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine SOCIAL_AUTH_USER = new RedisKeyDefine("社交登陆的授权用户",
            "social_auth_user:%d:%s", // 参数为 type，code
            RedisKeyDefine.KeyTypeEnum.STRING, AuthUser.class, Duration.ofDays(1));

    RedisKeyDefine SOCIAL_AUTH_STATE = new RedisKeyDefine("社交登陆的 state",
            "social_auth_state:%s", // 参数为 state
            RedisKeyDefine.KeyTypeEnum.STRING, String.class, Duration.ofHours(24)); // 值为 state
}
