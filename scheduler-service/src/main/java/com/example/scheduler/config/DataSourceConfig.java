package com.example.scheduler.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据源配置类
 * 从配置文件读取Oracle数据库连接信息
 * 启动时检查数据库连接
 */
@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * 创建数据源Bean
     * @return 数据源
     */
    @Bean
    public DataSource dataSource() {
        logger.info("正在初始化Oracle数据源...");
        logger.info("数据库URL: {}", url);
        logger.info("数据库用户名: {}", username);

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();

        // 测试数据库连接
        testConnection(dataSource);

        return dataSource;
    }

    /**
     * 测试数据库连接
     * @param dataSource 数据源
     */
    private void testConnection(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                logger.info("Oracle数据库连接测试成功！");
            } else {
                logger.error("Oracle数据库连接测试失败：连接为空或已关闭");
                throw new RuntimeException("Oracle数据库连接测试失败");
            }
        } catch (SQLException e) {
            logger.error("Oracle数据库连接测试失败：{}", e.getMessage(), e);
            throw new RuntimeException("Oracle数据库连接测试失败", e);
        }
    }

}