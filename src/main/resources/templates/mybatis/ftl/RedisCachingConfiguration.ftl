package ${packageName}.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;


@EnableCaching
@Configuration("redisCachingConfiguration")
//@EnableConfigurationProperties(RedisProperties.class)
public class RedisCachingConfiguration extends CachingConfigurerSupport {

//    @Autowired
//    private RedisProperties redisProperties;

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(".").append(method.getName()).append("#");
            for (Object obj : params) {
                if (Objects.nonNull(obj)) {
                    sb.append("null");
                } else {
                    sb.append(obj.toString());
                }
            }
            return sb.toString();
        };
    }

//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(redisProperties.getHost());
//        jedisConnectionFactory.setDatabase(redisProperties.getDatabase());
//        jedisConnectionFactory.setPort(redisProperties.getPort());
//        jedisConnectionFactory.setPassword(redisProperties.getPassword());
//        jedisConnectionFactory.setUseSsl(redisProperties.isSsl());
//        jedisConnectionFactory.setTimeout(redisProperties.getTimeout());
//        jedisConnectionFactory.setUsePool(false);
//        JedisPoolConfig poolConfig = jedisPoolConfig();
//        if (Objects.nonNull(poolConfig)) {
//            jedisConnectionFactory.setPoolConfig(poolConfig);
//            jedisConnectionFactory.setUsePool(true);
//        }
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        if (Objects.nonNull(redisProperties.getPool())) {
//            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//            jedisPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
//            jedisPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
//            jedisPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
//            jedisPoolConfig.setMaxTotal(redisProperties.getPool().getMaxActive());
//            return jedisPoolConfig;
//        }
//        return null;
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
//        redisTemplate.setValueSerializer(new OxmSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(5 * 60);
        return cacheManager;
    }

//    /*********=======================================*************/
//
//    @Bean("addressRedisTemplate")
//    public RedisTemplate<String, UserAddress> redisTemplateAddress(RedisConnectionFactory cf) {
//        RedisTemplate<String, UserAddress> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
////        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserAddress.class));
////        redisTemplate.setValueSerializer(new OxmSerializer());
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
//
//    @Primary
//    @Bean("addressCacheManager")
//    public CacheManager cacheManagerAddress(@Qualifier("addressRedisTemplate") RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(5 * 60);
//        return cacheManager;
//    }
//
//    @Bean("userRedisTemplate")
//    public RedisTemplate<String, User> redisTemplateUser(RedisConnectionFactory cf) {
//        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
////        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
////        redisTemplate.setValueSerializer(new OxmSerializer());
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
//
//    @Bean("userCacheManager")
//    public CacheManager cacheManagerUser(@Qualifier("userRedisTemplate") RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(5 * 60);
//        return cacheManager;
//    }


}
