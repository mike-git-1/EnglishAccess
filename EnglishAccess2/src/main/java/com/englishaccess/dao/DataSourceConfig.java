package com.englishaccess.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//This class is responsible for defining and configuring beans that Spring should manage.
@Configuration
public class DataSourceConfig {
	
	//bean definition method for `DataSource`obj. It tells Spring how to create an instance of DataSource (specifically HikariDataSource) configured with settings from HikariConfig.
    @Bean
    public DataSource dataSource() {
    	// holds configuration settings for HikariCP connection pool
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/users?useSSL=false");
        hikariConfig.setUsername("demo_user");
        hikariConfig.setPassword("rootpassword");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(600000);
        hikariConfig.setMaxLifetime(1800000);
        
        // The DataSource bean. Other components (e.g. UserDao) can now inject this `DataSource` bean to perform db operations. 
        return new HikariDataSource(hikariConfig);
        
    }
    
    //bean definition method for `UserDao`obj. It tells Spring how to create an instance of UserDao and inject the DataSource dependency.
    @Bean 
    public UserDao userDao(DataSource dataSource) {
    	return new UserDao(dataSource);
    }
    
    //bean definition method for `NotesDao`obj. It tells Spring how to create an instance of NotesDao and inject the DataSource dependency.
    @Bean 
    public NotesDao notesDao(DataSource dataSource) {
    	return new NotesDao(dataSource);
    }
}