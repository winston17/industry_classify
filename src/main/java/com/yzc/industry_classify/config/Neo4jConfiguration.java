package com.yzc.industry_classify.config;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.annotation.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Classname Neo4jConfiguration
 * @Author lizonghuan
 * @Description neo4j配置类
 * @Date-Time 2019/5/28-16:14
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableNeo4jAuditing
@EnableNeo4jRepositories(basePackages="com.yzc.industry_classify.repository")
public class Neo4jConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(), "com.yzc.industry_classify.entity");
    }

//    @Bean
//    public org.neo4j.ogm.config.Configuration configuration() {
//        ConfigurationSource properties = new ClasspathConfigurationSource("ogm.properties");
//        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder(properties).build();
//        return configuration;
//    }

//    @Bean
//    public SessionFactory sessionFactory() {
//        return new SessionFactory(configuration(), <packages> );
//    }


    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri("bolt://192.168.18.229")
                .credentials("neo4j", "123456")
                .build();
        return configuration;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }


}
