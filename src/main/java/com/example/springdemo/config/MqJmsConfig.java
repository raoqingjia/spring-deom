package com.example.springdemo.config;

import com.example.springdemo.utils.StringUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MqJmsConfig {

    @Value("${spring.activemq.user}")
    private String user;
    @Value("${spring.activemq.password}")
    private String password;
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    @Value("${spring.activemq.threadPoolSize}")
    private String threadPoolSize;
    @Value("${spring.activemq.pooled}")
    private String pooled;
    @Value("${spring.activemq.max-connections}")
    private String maxConnections;
    @Value("${spring.activemq.idle-timeout}")
    private String idleTimeout;
    @Value("${spring.activemq.expiry-timeout}")
    private String expiryTimeout;
    @Value("${spring.activemq.concurrency}")
    private String concurrency;

    private final boolean isAutoStartup = true;
    private final PlatformTransactionManager transactionManager = null;

    @Bean(name = "connectionFactory")    //  activemq的连接工厂
    @Primary
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = null;
        if (StringUtils.isBlank(user) || StringUtils.isBlank(password)) {  //  没有密码就直连 tcp地址
            activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        } else {
            //  说明有用户密码
            activeMQConnectionFactory = new ActiveMQConnectionFactory(user, password, brokerUrl);
        }
        activeMQConnectionFactory.setMaxThreadPoolSize(Integer.parseInt(threadPoolSize)); // // session缓存线程池
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        ActiveMQPrefetchPolicy p = new ActiveMQPrefetchPolicy();
        p.setQueuePrefetch(1);  //  信息每次处理1条消息，处理完再去取，防止开启多个消费者事，只有一个消费者干活，设置prefetch 值(多个消费者有用)
        activeMQConnectionFactory.setPrefetchPolicy(p);
        return activeMQConnectionFactory;
    }

//    ActiveMQ原生的连接工程：ActiveMQConnectionFactory
//    默认的maxThreadPoolSize=1000，也就是每个connection的session线程池最大值为1000，可以根据自己应用定制。
//    我们一般不直接用这个连接工厂，原因是：这个connectionFactory不会复用connection、session、producer、consumer，每次连接都需要重新创建connection，再创建session，然后调用session的创建新的producer或者consumer的方法，然后用完之后依次关闭，比较浪费资源。
//    我们一般用这个连接工厂作为其他拥有更高级功能（缓存）的连接工厂的参数
//    二、PooledConnectionFactory
//　　PooledConnectionFactory会缓存connection，session，和producer，不会缓存consumer，更适合于发送者。
//   maxConnections为最大连接数；
//   maximumActiveSessionPerConnection为每个连接最大的会话数量。
//   可以自行设置。
//   三、SingleConnectionFactory
//　 SingleConnectionFactory：对于建立JMS服务器链接的请求会一直返回同一个链接，并且会忽略Connection的close方法调用。
//   四、CachingConnectionFactory
//　 CachingConnectionFactory继承了SingleConnectionFactory(仅有一个Connection)，所以它拥有SingleConnectionFactory的所有功能，同时它还新增了缓存功能，它可以缓存Session、MessageProducer和MessageConsumer。spring2.5.3之后推出的首选方案。
//   默认情况下，cachingConnectionFactory默认只缓存一个session，针对低并发足够。sessionCacheSize =1. 默认缓存producer、consumer。
//   五、JMSTemplate
//　　Spring提供的JMS模板是JMSTemplate，封装了发送和接收消息的方法。可以分别设置queue或者topic的模板。
//    六、JMS Listener container
//    1、DefaultMessageListenerContainer负责将messageListener注册到connectionFactory的destination，一旦destination中有消息，就会将消息推送给messageListener。
//    2、一个DefaultMessageListenerContainer消费一个队列。可以缓存connection/session/consumer，CacheLevel默认是Auto=4，没有配置TransactionManager，相当于CacheConsumer级别=3，有的话为NONE级别；（后三种一个container一个connection，所有AsyncMessageListnerInvoker共享这个connection，每个Invoker保持自己的session和consumer）
//    3、maxMessagesPerTask<0，递增到线程为maxCurrentConsumers不变；
//    maxMessagesPerTask >0,每个AsyncMessageListenerInvoker在执行了maxMessagePerTask轮后结束该线程，然后交给container确认是否调度该线程。（峰值过后会从maxConcurrentConsumers将到cocurrentConsumers）。
//    4、默认：SimpleAsyncTaskExecutor——连接不能复用；创建new Thread。建议用线程池。
//    可以配置maxCurrentConsumers/concurentConsumers，相当于一个consumer的多个副本。
//    5、 maxThreadPoolSize 最好设置的与 DefaultMessageListenerContainer.maxConcurrency 相同。
//    6、jms:listener-container：spring注解形式的DefaultMessageListenerContainer。

    @Bean(name = "containerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(@Qualifier("connectionFactory") ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory(); //   可以开启多个(concurrent)AsyncMessageListenerInvoker并发收消息
        factory.setConnectionFactory(activeMQConnectionFactory);
        factory.setPubSubDomain(false);
        if (this.transactionManager != null) {
            factory.setTransactionManager(transactionManager);
        } else {
            factory.setSessionTransacted(Boolean.valueOf(true));
        }
        factory.setAutoStartup(isAutoStartup);
        factory.setSessionAcknowledgeMode(1);
        if (concurrency != null) {
            factory.setConcurrency(concurrency);
        }

        return factory;
    }
    @Bean(name = "jmsTemplate")
    @Primary
    public JmsTemplate jmsTemplate(@Qualifier("connectionFactory") ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        return jmsTemplate;
    }



}
