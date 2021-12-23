package cn.project.demo.framework.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "project.cache.redis")
@Data
public class CacheProperties implements Serializable {

    /**
     * redis缓存时间(分钟)
     */
    private Integer entryTtl;
}