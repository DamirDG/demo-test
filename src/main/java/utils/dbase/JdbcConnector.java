package utils.dbase;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import utils.ConfProperties;

import javax.sql.DataSource;

public class JdbcConnector {
    private static JdbcTemplate jdbcTemplate;

    private static DataSource posgresqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(ConfProperties.getProperty("db_url"));
        dataSource.setUsername(ConfProperties.getProperty("db_username"));
        dataSource.setPassword(ConfProperties.getProperty("db_password"));
        return dataSource;
    }
    public static JdbcTemplate getJdbcTemplate() {
        try {
            jdbcTemplate = new JdbcTemplate(posgresqlDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jdbcTemplate;
    }

}
