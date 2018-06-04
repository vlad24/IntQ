package com.appricots.intq.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.appricots.intq.security.CustomAuthenticationProvider;
import net.tanesha.recaptcha.ReCaptchaImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan(basePackages = "com.appricots.intq")
public class AppConfiguration {

	@Autowired
	private Environment environment;

	@Bean(name="recaptcha")
	ReCaptchaImpl initRecaptchaBean(){
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey(environment.getProperty("recaptcha.private_key"));
		return reCaptcha;
	}

	@Bean(name="fileResolver")
	CommonsMultipartResolver getFileResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		int maxMegabytes = 1; 
		resolver.setMaxUploadSize(maxMegabytes * 1024 * 1024);
		return resolver;
	}


	@Bean(name="dataSource")
	public DataSource getDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory() throws IOException{
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("com.appricots.intq.model");
		Properties hibernateProps = new Properties();
		hibernateProps.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		hibernateProps.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		hibernateProps.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		hibernateProps.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		sessionFactoryBean.setHibernateProperties(hibernateProps);
		sessionFactoryBean.setDataSource(getDatasource());
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Autowired
	@Bean(name="txManager")
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}


	@Bean(name="authenticationProvider")
	public AuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}

}

