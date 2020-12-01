package com.james.redis02springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.james.redis02springboot.pojo.User;
import com.james.redis02springboot.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void testRedisUtils(){
        redisUtils.set("name", "JamesZhan");
        System.out.println(redisUtils.get("name"));

    }

    @Test
    void contextLoads() {

//        redisTemplate
//        opsForValue 字符串操作
//        opsForList
//        opsForHash
//        opsForSet
//        opsForZSet
//        opsForGeo


//        redis 的连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();

        redisTemplate.opsForValue().set("mykey", "JamesTestKey");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    public void test() throws JsonProcessingException {
        User user = new User("詹姆斯", 4);
//        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
