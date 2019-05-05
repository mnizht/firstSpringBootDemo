package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhuhaitao
 * @date 2019/4/28 11:14
 */
@Configuration
public class RedisConfig {
  private RedisConnectionFactory factory = null;

  public RedisConnectionFactory initRedisConnectionFactory(){
    if(this.factory!=null){
      return this.factory;
    }
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    //最大空闲数
    poolConfig.setMaxIdle(30);
    //最大连接数
    poolConfig.setMaxTotal(50);
    //最大等待毫秒数
    poolConfig.setMaxWaitMillis(2000);
    //创建jedis连接工厂
    JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
    //创建单机的redis配置
    RedisStandaloneConfiguration rsConfig = factory.getStandaloneConfiguration();
    rsConfig.setHostName("");
    rsConfig.setPort(2222);
    rsConfig.setPassword(RedisPassword.of("pppp"));
    this.factory = factory;
    return factory;
  }

  @Bean(name="redisTemplate")
  public RedisTemplate<Object, Object> initRedisTemplate() {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(initRedisConnectionFactory());
    RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(stringRedisSerializer);
    return redisTemplate;
  }
}
