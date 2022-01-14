package com.zikozee.flywayjdbctemplate.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author : Ezekiel Eromosei
 * @created : 14 Jan, 2022
 */

@Configuration
public class DatasourceConfig {

    // here we can create multiple hikaridatasouce giving it a unique bean name
    // then create a separate jdbctemplate with a unique bean name for connection

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

//    @Bean(name = "hikari2")
//    @ConfigurationProperties("app.datasource2.bam")
//    public HikariDataSource hikariDataSource2(){
//        return DataSourceBuilder
//                .create()
//                .type(HikariDataSource.class)
//                .build();
//    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource){
        return new JdbcTemplate(hikariDataSource);
    }

//    @Bean(name = "jdbctemplate2")
//    public JdbcTemplate jdbcTemplate2(@Qualifier(value = "hikari2") HikariDataSource hikariDataSource){
//        return new JdbcTemplate(hikariDataSource);
//    }
}
