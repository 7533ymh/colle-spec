package com.example.backend.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class CollspecDBConfig {


    @Bean(name = "firstDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.first.datasource")
    public DataSource firstDataScource() {
        return DataSourceBuilder.create().build();

    }


    @Bean(name = "firstSqlSessionFactory")
    @Primary
    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource) throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //sessionFactory.setMapperLocations(resolver.getResources("classpath:com/example/backend/mapper/*Mapper.class"));

        return sessionFactory.getObject();

    }


    @Bean(name = "firstSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {

        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);

        return sqlSessionTemplate;

    }

    @Bean(name = "firstMapperScannerConfigurer")
    @Primary
    public MapperScannerConfigurer mapperScannerConfigurer() {

        final MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        mapperScannerConfigurer.setBasePackage("com.example.backend.mapper.collspec");

        mapperScannerConfigurer.setAnnotationClass(org.apache.ibatis.annotations.Mapper.class);

        mapperScannerConfigurer.setSqlSessionFactoryBeanName("firstSqlSessionFactory");

        return mapperScannerConfigurer;
    }


}