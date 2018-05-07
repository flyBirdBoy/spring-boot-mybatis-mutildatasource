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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 17:23 2018/5/3
 * @Modified By:
 */
@Configuration
@MapperScan(basePackages = MasterConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterConfig {

    //master dao 所在的包
    public static final String PACKAGE = "com.example.demo.dao.master";
    //mapper所在目录
    private static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/master/*.xml";

    @Value("${spring.datasource.master.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.master.url}")
    private String url;

    @Value("${spring.datasource.master.username}")
    private String username;

    @Value("${spring.datasource.master.password}")
    private String password;

    //初始化数据库连接
    @Bean(name="masterDataSource")
    @Primary
    public DataSource masterDataSource(){
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setDriverClassName(driverClassName);
        masterDataSource.setUrl(url);
        masterDataSource.setUsername(username);
        masterDataSource.setPassword(password);
        return masterDataSource;
    }

    //数据源事务管理器
    @Bean(name="masterDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager masterDataSourceTransactionManager(){
        return new DataSourceTransactionManager(masterDataSource());
    }

    //创建Session
    @Bean(name="masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(masterDataSource);
        //MapperLocations(Resource[] mapperLocations)
        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources(MasterConfig.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        return sqlSessionFactoryBean.getObject();
    }

}
