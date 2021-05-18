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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class ColleasyDBConfig {



    @Bean(name="secondDataSource")

    @ConfigurationProperties(prefix="spring.second.datasource")

    public DataSource secondDataScource() {

        return DataSourceBuilder.create().build();

    }





    @Bean(name="secondSqlSessionFactory")

    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //sessionFactory.setMapperLocations(resolver.getResources("classpath:com/example/backend/mapper/colleasy/*Mapper.class"));

        return sessionFactory.getObject();

    }



    @Bean(name = "secondSqlSessionTemplate")

    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {

        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);

        return sqlSessionTemplate;

    }

    @Bean(name = "secondMapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {

        final MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        mapperScannerConfigurer.setBasePackage("com.example.backend.mapper.colleasy");

        mapperScannerConfigurer.setAnnotationClass(com.example.backend.config.database.EasyMapper.class);

        mapperScannerConfigurer.setSqlSessionFactoryBeanName("secondSqlSessionFactory");


        return mapperScannerConfigurer;
    }




}


