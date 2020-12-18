package ru.youpromocodebot.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.cache.jcache.internal.JCacheRegionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:properties/hsqldb.yml")
@EnableJpaRepositories("ru.youpromocodebot.dao")
@Profile("test")
public class TestDataBaseConfig {

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("ru.youpromocodebot.**.model");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setDataSource(dataSource);

        Map<String, Object> mapSetting = new HashMap<>();
        mapSetting.put(AvailableSettings.USE_SQL_COMMENTS, true);
        mapSetting.put(AvailableSettings.FORMAT_SQL, true);
        mapSetting.put(AvailableSettings.SHOW_SQL, true);
        mapSetting.put(AvailableSettings.JPA_PROXY_COMPLIANCE, false);
        mapSetting.put(AvailableSettings.CACHE_REGION_FACTORY, new JCacheRegionFactory());
        mapSetting.put("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
        mapSetting.put(AvailableSettings.USE_SECOND_LEVEL_CACHE, false);
        mapSetting.put(AvailableSettings.USE_QUERY_CACHE, false);
        mapSetting.put("javax.persistence.validation.group.pre-persist", "javax.validation.groups.Default");
        mapSetting.put("javax.persistence.validation.group.pre-update", "javax.validation.groups.Default");
        entityManagerFactory.setJpaPropertyMap(mapSetting);

        return entityManagerFactory;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return new JpaTransactionManager();
    }
}