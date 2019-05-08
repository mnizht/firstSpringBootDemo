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
    rsConfig.setHostName("2.57.254.24");
    rsConfig.setPort(6379);
    rsConfig.setPassword(RedisPassword.of("zhuhaitao")); //
    this.factory = factory;
    return factory;
  }

  /**
   * 字符串序列化器
   * Redis是基于字符串存储的Nosql，而java是基于对象的语言，对象是无法存储到Redis中的，
   * Java在向Redis中写数据时，需要先将数据序列化成二进制字符串才能保存。取数据时也要反序列化后才能正常显示。
   * @return
   */
  @Bean(name="redisTemplate")
  public RedisTemplate<Object, Object> initRedisTemplate() {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(initRedisConnectionFactory());
    //RedisTemplate 会自动初始化 StringRedisSerializer,所以这里直接获取
    RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
    //设置字符串序列化器，这样Spring就会把Redis的key当做字符串处理了
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(stringRedisSerializer);
    return redisTemplate;
  }
}
