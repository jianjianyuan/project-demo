package cn.project.demo.framework.cache.conf;

import cn.project.demo.framework.cache.CacheProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.annotation.Resource;
import java.time.Duration;

@Slf4j
@Configuration
@EnableCaching
@EnableConfigurationProperties({CacheProperties.class})
public class FrameworkRedisCacheAutoConfiguration {

    @Resource
    private CacheProperties cacheProperties;

    @Bean
    @ConditionalOnProperty(value = "project.cache.redis.entry-ttl", havingValue = "true")
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate, @Value(value = "project.cache.redis.entryTtl:10") long entryTtl) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(entryTtl)) //缓存时间, 默认值10分钟兜底
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getKeySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }
}