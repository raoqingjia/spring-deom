package com.example.springdemo.config;
import javax.sql.DataSource;
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

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.example.springdemo.dao.shaprm",sqlSessionFactoryRef = "SqlSessionFactoryMysql")
public class DatasourceConfigMysql {
    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final String MAPPER_LOCATION_MYSQL = "classpath:mapper/shparm/*.xml";

    @Bean(name = "DataSourceMysql")
    @Primary
    @ConfigurationProperties("spring.mysqlshparm")
    public DataSource dataSourceMysql() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "SqlSessionFactoryMysql")
    @Primary
    public SqlSessionFactory sqlSessionFactoryMysql(@Qualifier("DataSourceMysql") DataSource dataSource) throws Exception {
        log.debug("try obtain SqlSessionFactoryMysql");
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION_MYSQL));
        sessionFactory.setDataSource(dataSource);
        SqlSessionFactory factory = sessionFactory.getObject();
        log.info("SqlSessionFactoryMysql obtained");
        return factory;
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
//    @Bean(name = "TransactionManagerMysql")
//    @Primary
//    public DataSourceTransactionManager transactionManagerMysql() {
//        log.trace("try create transactionManagerMysql");
//        DataSourceTransactionManager txMgr = new DataSourceTransactionManager(dataSourceMysql());
//        log.info("transactionManagerMysql obtained");
//        return txMgr;
//    }
//}
