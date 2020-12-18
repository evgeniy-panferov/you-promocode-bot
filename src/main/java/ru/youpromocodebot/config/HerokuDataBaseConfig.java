package ru.youpromocodebot.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.cache.jcache.internal.JCacheRegionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Profile("heroku")
@Configuration
@EnableJpaRepositories("ru.youpromocodebot.dao")
public class HerokuDataBaseConfig{

    @Bean
    public DataSource dataSource() throws URISyntaxException {

        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setMaxActive(10);
        dataSource.setMinIdle(2);
        dataSource.setMaxWait(20000);
        dataSource.setInitialSize(2);
        dataSource.setMaxIdle(5);
        dataSource.setTestOnBorrow(true);
        dataSource.setRemoveAbandoned(true);
        dataSource.setTestOnConnect(true);
        dataSource.setTestWhileIdle(true);
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
        mapSetting.put(AvailableSettings.USE_SECOND_LEVEL_CACHE, true);
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

