package com.james;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author: JamesZhan
 * @create: 2020 - 12 - 01 21:47
 */
public class TestPing {

    public static void main(String[] args) {
        // 1. new jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        System.out.println(jedis.ping());

        System.out.println("清空数据:" + jedis.flushDB());
        System.out.println("判断某个键是否存在:" + jedis.exists("username"));
        System.out.println("新增username:" + jedis.set("username", "James"));
        System.out.println("新增password:" + jedis.set("password", "123456"));
        System.out.println("系统中所有的键如下:");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("删除password:" + jedis.del("password"));
        System.out.println("判断是否存在password:" + jedis.exists("password"));
        System.out.println("查看username:" + jedis.get("username"));
        System.out.println("查看username的类型:" + jedis.type("username"));
        System.out.println("随机返回一个key：" + jedis.randomKey());
        System.out.println("重命名:" + jedis.rename("username", "name"));
        System.out.println("取出name的值:" + jedis.get("name"));
        System.out.println("切换数据库:" + jedis.select(0));
        System.out.println("删除所有:" + jedis.flushDB());
        System.out.println("返回当前数据中的所有key:" + jedis.dbSize());
        System.out.println("删除整个redis中的内容:" + jedis.flushAll());
    }
}
