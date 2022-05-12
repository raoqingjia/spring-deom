package com.example.springdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisSentinelConfig {
    @Value("${spring.redis.password:please-check-your-config}")
    private String password;
    @Value("${spring.redis.database:0}")
    private Integer database;
    @Value("${spring.redis.sentinel.master:please-check-your-config}")
    private String master;
    @Value("${spring.redis.sentinel.nodes:please-check-your-config}")
    private String nodes;

    //  https://www.cnblogs.com/sev-night/p/Redis.html  类似文章
    //  创建哨兵配置
    @Bean(name = "RedisConfigurationSentinel")
    @Primary
    public RedisSentinelConfiguration redisConfigurationSentinel() {
        if(!"please-check-your-config".equals(master)){
            // Sentinel 哨兵模式
            // 当一个主服务器不能正常工作时， Sentinel 会开始一次自动故障迁移操作， 它会将失效主服务器的其中一个从服务器升级为新的主服务器， 并让失效主服务器的其他从服务器改为复制新的主服务器
            RedisSentinelConfiguration config = new RedisSentinelConfiguration();
            config.setMaster(master);
            config.setPassword(RedisPassword.of(password));
            config.setDatabase(database);
            String[] array = nodes.split(",");
            for (String hp : array) {
                String[] hostAndPort = hp.split(":");
                config.sentinel(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
            }
            log.info("sentinel master: [{}]", config.getMaster().getName());
            config.getSentinels().forEach(r -> log.info("{}:{}", r.getHost(), r.getPort()));
            return config;
        }else{
            log.trace(" check your application.yml");
            return null;
        }
    }

    //配置Lettuce连接工厂
    @Bean(name = "LettuceConnectionFactorySentinel")
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactorySentinel() {
        if(!"please-check-your-config".equals(master)){
            // spring 2.x 默认使用的redisclient使用LettuceConnectionFactory，1.x使用的是JedisConnectionFactory。
            // Lettuce 和 jedis 的都是连接 Redis Server的客户端，Jedis 在实现上是直连 redis server，多线程环境下非线程安全，除非使用连接池，为每个 redis实例增加 物理连接。
            // Lettuce 是 一种可伸缩，线程安全，完全非阻塞的Redis客户端，多个线程可以共享一个RedisConnection,它利用Netty NIO 框架来高效地管理多个连接，从而提供了异步和同步数据访问方式，用于构建非阻塞的反应性应用程序
            LettuceConnectionFactory factory = new LettuceConnectionFactory(redisConfigurationSentinel());
            log.info("LettuceConnectionFactorySentinel bean created");
            return factory;
        }else{
            log.info(" check your application.yml");
            return null;
        }
    }


    // 配置StringRedisTemplateSentinel
    @Bean(name = "StringRedisTemplateSentinel")
    @Primary
    public StringRedisTemplate stringRedisTemplateSentinel() {
        if(!"please-check-your-config".equals(master)){
            // 1.创建 redisTemplate 模版
            StringRedisTemplate template = new StringRedisTemplate(lettuceConnectionFactorySentinel());
            log.info("StringRedisTemplateSentinel bean created");
            // 2.创建 序列化类
            GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
            // 3.序列化类，对象映射设置
            // 4.设置 value 的转化格式和 key 的转化格式
            template.setValueSerializer(genericToStringSerializer);
            template.setKeySerializer(new StringRedisSerializer());
            template.afterPropertiesSet();
            return template;
        }else{
            log.info(" check your application.yml");
            return null;
        }
    }
}
