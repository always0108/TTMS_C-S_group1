package TTMS_Server.config.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ResourceBundle;

@Configuration
public class DbConfig {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DriverClassName;      // 声明数据源

    // 加载配置文件, 其内容是上面的URL/USERNAME/PASSWORD/Driver
    private static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("db-config");

    // 静态代码块在加载类时只执行一次
    static {
        URL = resourceBundle.getString("jdbc.url");
        USERNAME = resourceBundle.getString("jdbc.username");
        PASSWORD = resourceBundle.getString("jdbc.password");
        DriverClassName = resourceBundle.getString("jdbc.driverClassName");
    }

    // 使用dbcp数据库连接池做为数据源(需要从maven下载两个jar包:commons-dbcp2/commons-pool2)
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DriverClassName);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(5);
        dataSource.setMaxIdle(10);
        return dataSource;
    }

    // 使用jdbcTemplate来声明JDBC模板
    @Bean
    public JdbcOperations jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
