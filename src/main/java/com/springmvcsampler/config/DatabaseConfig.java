package com.springmvcsampler.config;

import com.springmvcsampler.Application;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Developer on 04/04/2015.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.springmvcsampler.repositories")
//@EnableJpaRepositories(basePackageClasses = Application.class)
public class DatabaseConfig {

    @Value("${dataSource.driverClassName}")
    private String driver;
    @Value("${dataSource.url}")
    private String url;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(configureDataSource());
        entityManagerFactoryBean.setPackagesToScan("com.springmvcsampler");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }


//        @Bean
//        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//            em.setDataSource(dataSource());
//            em.setPackagesToScan("com.springmvcsampler");
//            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//            em.setJpaVendorAdapter(vendorAdapter);
//            em.setJpaProperties(this.additionalProperties());
//            return em;
//        }

//        @Bean
//        public DataSource dataSource(){
//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName(MYSQL_DRIVER);
//            dataSource.setUrl(MYSQL_CONNECTION_STRING);
//            dataSource.setUsername(MYSQL_USER);
//            dataSource.setPassword(MYSQL_PASSWORD);
//            return dataSource;
//        }

    @Bean
    public DataSource configureDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");

        return new HikariDataSource(config);
    }


//    @Bean
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return new JpaTransactionManager();
//    }

        @Bean
        public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(emf);

            return transactionManager;
        }

        @Bean
        public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
            return new PersistenceExceptionTranslationPostProcessor();
        }

//        Properties additionalProperties() {
//            Properties properties = new Properties();
//            properties.setProperty("hibernate.hbm2ddl.auto", "update");
//            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//            return properties;
//        }


}
