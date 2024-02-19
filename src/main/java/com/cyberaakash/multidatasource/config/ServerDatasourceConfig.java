package com.cyberaakash.multidatasource.config;

import com.cyberaakash.multidatasource.server.entity.Server;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.cyberaakash.multidatasource.server",
        entityManagerFactoryRef = "serverEntityManagerFactory",
        transactionManagerRef = "serverTransactionManager"
)
public class ServerDatasourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.server")
    public DataSourceProperties serverDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.server.configuration")
    public DataSource serverDatasource() {
        return serverDatasourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name="serverEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    serverEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(serverDatasource()).packages(Server.class).build();
    }

    @Bean(name = "serverTransactionManager")
    public PlatformTransactionManager serverTransactionManager(
            final @Qualifier("serverEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean serverEntityManagerFactory
    ) {
        return new JpaTransactionManager(serverEntityManagerFactory.getObject());
    }


}
