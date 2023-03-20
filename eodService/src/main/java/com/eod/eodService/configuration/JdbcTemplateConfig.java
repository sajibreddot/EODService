package com.eod.eodService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {
    @Bean
    public JdbcTemplate mfsCoreJdbcTemplate(DataSource mfsCoreDataSource) {
        return new JdbcTemplate(mfsCoreDataSource);
    }

    @Bean
    public JdbcTemplate backOfficeJdbcTemplate(DataSource backOfficeDataSource) {
        return new JdbcTemplate(backOfficeDataSource);
    }

    @Bean
    public JdbcTemplate myLocalJdbcTemplate(DataSource myLocalDataSource) {
        return new JdbcTemplate(myLocalDataSource);
    }

}