package com.james;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author: JamesZhan
 * @create: 2020 - 12 - 01 22:14
 */
public class TestTransaction {

    public static void main(String[] args) {

        // 1. new jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "james");
        String result = jsonObject.toJSONString();

        // 开启事务
        Transaction tx = jedis.multi();

        try{

            tx.set("user1", result);
            tx.set("user2", result);
            // 执行事务
            tx.exec();
        }catch (Exception e){
            // 放弃事务
            tx.discard();
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));

            //关闭连接
            jedis.close();
        }
    }
}
