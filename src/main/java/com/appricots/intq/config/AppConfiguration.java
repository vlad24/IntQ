package com.appricots.intq.config;

import com.appricots.intq.NameOf;
import net.tanesha.recaptcha.ReCaptchaImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableWebSecurity
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan(basePackages = "com.appricots.intq")
public class AppConfiguration {

    @Autowired
    private Environment environment;


    @Bean(name = "recaptcha")
    ReCaptchaImpl initRecaptchaBean() {
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey(environment.getProperty("recaptcha.private_key"));
        return reCaptcha;
    }


    @Bean(name = "fileResolver")
    CommonsMultipartResolver getFileResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        int maxMegabytes = 1;
        resolver.setMaxUploadSize(maxMegabytes * 1024 * 1024);
        return resolver;
    }


    @Bean(name = "dataSource")
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }


    @Bean(name = "sessionFactory")
    SessionFactory getSessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("com.appricots.intq.model");
        Properties hibernateProps = new Properties();
        hibernateProps.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        hibernateProps.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        hibernateProps.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        hibernateProps.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        sessionFactoryBean.setHibernateProperties(hibernateProps);
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }


    @Autowired
    @Bean(name = "txManager")
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }


    /*
        TEST PROFILE RELATED BEANS
     */

    @Value("classpath:/test-data.sql")
    Resource testProfileDataScript;


    @Bean
    @Profile(NameOf.Profile.TEST)
    DatabasePopulator testDatabasePopulator() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(testProfileDataScript);
        return resourceDatabasePopulator;
    }


    @Bean
    @Profile(NameOf.Profile.TEST)
    DataSourceInitializer testDataSourceInitializer(DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(testDatabasePopulator());
        return initializer;
    }


}

