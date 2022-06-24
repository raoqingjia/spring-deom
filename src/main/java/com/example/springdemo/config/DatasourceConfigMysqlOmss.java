package com.example.springdemo.config;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.example.springdemo.dao.omss",sqlSessionFactoryRef = "SqlSessionFactoryMysqlOmss")
public class DatasourceConfigMysqlOmss {
    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final String MAPPER_LOCATION_MYSQL = "classpath:mapper/omss/*.xml";

    @Bean(name = "DataSourceMysqlOmss")
    @ConfigurationProperties("spring.mysql.omss")
    public DataSource dataSourceMysql() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //spring通过SqlSessionTemplate对象去操作sqlsession语句
    @Bean(name = "SqlSessionFactoryMysqlOmss")
    public SqlSessionFactory sqlSessionFactoryMysql(@Qualifier("DataSourceMysqlOmss") DataSource dataSource) throws Exception {
        log.debug("try obtain SqlSessionFactoryMysql");
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION_MYSQL));
        sessionFactory.setDataSource(dataSource);
        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[] {interceptor});
        SqlSessionFactory factory = sessionFactory.getObject();
        log.info("SqlSessionFactoryMysql obtained");
        return factory;
    }
    // 配置事务管理器
    @Bean(name = "TransactionManagerMysql")
    @Primary
    public DataSourceTransactionManager transactionManagerMysql() {
        log.trace("try create transactionManagerMysql");
        DataSourceTransactionManager txMgr = new DataSourceTransactionManager(dataSourceMysql());
        log.info("transactionManagerMysql obtained");
        return txMgr;
    }
}



//@Slf4j
//@Configuration
//@ComponentScan
//@MapperScan(basePackages = "com.example.springdemo.dao.interfaces", sqlSessionFactoryRef = "SqlSessionFactoryMysql")
//public class DatasourceConfigMysql {
//    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
//    private static final String MAPPER_LOCATION_MYSQL = "com/example/springdemo/urbac/dao/impl/*.xml";
//
//    @Bean(name = "DsMysqlProperties")
//    @Primary
//    @Qualifier("DsMysqlProperties")
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSourceProperties dsMysqlProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "DataSourceMysql")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSource dataSourceMysql() {
//        return dsMysqlProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Bean(name = "SqlSessionFactoryMysql")
//    @Primary
//    public SqlSessionFactory sqlSessionFactoryMysql(@Qualifier("DataSourceMysql") DataSource dataSource)
//            throws Exception {
//        log.debug("try obtain SqlSessionFactoryMysql");
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION_MYSQL));
//        sessionFactory.setDataSource(dataSource);
//        SqlSessionFactory factory = sessionFactory.getObject();
//        log.info("SqlSessionFactoryMysql obtained");
//        return factory;
//    }
//
//      配置事务管理器
//    @Bean(name = "TransactionManagerMysql")
//    @Primary
//    public DataSourceTransactionManager transactionManagerMysql() {
//        log.trace("try create transactionManagerMysql");
//        DataSourceTransactionManager txMgr = new DataSourceTransactionManager(dataSourceMysql());
//        log.info("transactionManagerMysql obtained");
//        return txMgr;
//    }
//}
