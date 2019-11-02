package websocket.websocket.Database.Mysql;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class DataSourceConfig {


    @Bean
    public DataSource datasource()
    {
        DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.driverClassName("");
        dataSource.url("jdbc:mysql//localhost:3306/database");
        dataSource.username("lukasz");
        dataSource.password("lukasz");

        return dataSource.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate()
    {
        return new JdbcTemplate(datasource());
    }
}
