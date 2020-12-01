package com.james.redis02springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author: JamesZhan
 * @create: 2020 - 12 - 01 23:41
 */

@Configuration
public class RedisConfig {

    // 定义自己的RedisTemplate

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
//        常见的String-> Object 类型
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
//        序列化配置
//        json的序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =  new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        jackson2JsonRedisSerializer.setObjectMapper(om);
//        string的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

//        key采用string的方式序列化
        template.setKeySerializer(stringRedisSerializer);
//        hash的key也采用string的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
//        value序列化方式采用Jackson2JsonRedisSerializer
        template.setValueSerializer(jackson2JsonRedisSerializer);
//        hash 的value也采用jackson2JsonRedisSerializer序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

//        将所有的properties set
        template.afterPropertiesSet();
        return template;
    }

}
