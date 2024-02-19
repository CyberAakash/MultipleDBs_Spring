package com.cyberaakash.multidatasource.config;

import com.cyberaakash.multidatasource.client.entity.Client;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.cyberaakash.multidatasource.client",
        entityManagerFactoryRef = "clientEntityManagerFactory",
        transactionManagerRef = "clientTransactionManager"
)
public class ClientDatasourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.client")
    public DataSourceProperties clientDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.client.configuration")
    public DataSource clientDatasource() {
        return clientDatasourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        return new EntityManagerFactoryBuilder(vendorAdapter, new HashMap<>(), null);
    }

    @Bean(name="clientEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    clientEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(clientDatasource()).packages(Client.class).build();
    }

    @Bean(name = "clientTransactionManager")
    public PlatformTransactionManager clientTransactionManager(
            final @Qualifier("clientEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean clientEntityManagerFactory
    ) {
        return new JpaTransactionManager(clientEntityManagerFactory.getObject());
    }


}
