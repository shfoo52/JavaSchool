package com.JavaSchool.config;

import com.JavaSchool.dao.CardDAO;
import com.JavaSchool.dao.SeqNumDAO;
import com.JavaSchool.dao.AccountDAO;
import com.JavaSchool.dao.AuthorizationDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DAOConfig {

    private final DataSource dataSource;

    public DAOConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public CardDAO cardDAO() throws SQLException {
        return new CardDAO(dataSource);
    }

    @Bean
    public AccountDAO accountDAO() throws SQLException {
        return new AccountDAO(dataSource);
    }

    @Bean
    public AuthorizationDAO authorizationDAO() throws SQLException {
        return new AuthorizationDAO(dataSource);
    }

    @Bean
    public SeqNumDAO seqnumDAO() throws SQLException {
        return new SeqNumDAO(dataSource);
    }
}