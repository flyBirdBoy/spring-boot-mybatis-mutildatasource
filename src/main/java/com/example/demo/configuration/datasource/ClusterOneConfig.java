package com.example.demo.configuration.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 18:20 2018/5/3
 * @Modified By:
 */
@Configuration
@MapperScan(basePackages = ClusterOneConfig.PACKAGE, sqlSessionFactoryRef = "clusterOneSqlSessionFactory")
public class ClusterOneConfig {

    public static final String PACKAGE = "com.example.demo.dao.clusterone";

    public static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/cluster/*.xml";


    @Value("${spring.datasource.clusterone.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.clusterone.url}")
    private String url;

    @Value("${spring.datasource.clusterone.username}")
    private String username;

    @Value("${spring.datasource.clusterone.password}")
    private String password;

    //初始化数据库连接
    @Bean(name = "clusterOneDataSource")
    public DataSource clusterOneDataSource() {
        DruidDataSource clusterDataSource = new DruidDataSource();
        clusterDataSource.setDriverClassName(driverClassName);
        clusterDataSource.setUrl(url);
        clusterDataSource.setUsername(username);
        clusterDataSource.setPassword(password);
        return clusterDataSource;
    }

    //数据源事务管理器
    @Bean(name = "clusterOneDataSourceTransactionManager")
    public DataSourceTransactionManager clusterDataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(clusterOneDataSource());
        return dataSourceTransactionManager;
    }

    //创建Session
    @Bean(name = "clusterOneSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterOneDataSource") DataSource clusterDataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(clusterDataSource);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(ClusterOneConfig.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean.getObject();
    }

}
