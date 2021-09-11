package com.agent.product.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ConditionalOnProperty(
        value = "STAGE",
        havingValue = "PROD"
)
public class HerokuPostgresConfiguration {

    @Value("${DATABASE_URL}")
    private String databaseUrl;

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(databaseUrl);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);
        /*Resolve: Caused by: org.postgresql.util.PSQLException: FATAL: too many connections
        You need to restrict the number of PostgreSQL database connections.
        Limit maximum pool size (Limit Database connection pooling).
        The FREE hobby tier, which includes the hobby-dev and hobby-basic plans,
        has the following limitations: Maximum of 20 connections.
        In other words, the Heroku Postgres FREE tier is limited to 20 connections.
        The default pool size for the Spring Boot application is 10
        so we need to decrease the pool size to 5.*/
        config.setMaximumPoolSize(5);

        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}