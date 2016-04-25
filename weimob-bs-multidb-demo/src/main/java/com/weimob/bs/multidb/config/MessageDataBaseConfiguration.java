package com.weimob.bs.multidb.config;

import com.github.pagehelper.PageHelper;
import com.weimob.bs.multidb.config.algorithm.MessageDatabaseAlgorithm;
import com.weimob.bs.multidb.config.algorithm.MessageTableAlgorithm;
import com.weimob.bs.multidb.mysql.sjsupport.DataSourceFactory;
import com.weimob.bs.multidb.mysql.sjsupport.setting.DatabaseSetting;
import com.weimob.bs.multidb.mysql.sjsupport.setting.MybatisSetting;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Adam on 2016/3/18.
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MessageDatabaseConfiguration {

    @ConfigurationProperties(prefix = "database.message")
    @Bean
    public DatabaseSetting messageDataBaseSetting() {
        return new DatabaseSetting();
    }

    @ConfigurationProperties(prefix = "mybatis.message")
    @Bean
    public MybatisSetting messageMybatisSetting() {
        return new MybatisSetting();
    }


    @Bean(name = "messageDataSource")
    public DataSource getShardingDataSource(DatabaseSetting databaseSetting) {
        DataSourceFactory dataSourceFactory = new DataSourceFactory(databaseSetting, new MessageDatabaseAlgorithm(), new MessageTableAlgorithm());
        return dataSourceFactory.getDataSource();
    }

    @Bean(name = "messageSqlSessionFactory")
    public SqlSessionFactory messageSqlSessionFactory(@Qualifier("messageDataSource") DataSource dataSource, MybatisSetting mybatisSetting) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(mybatisSetting.getTypeAliasesPackage());
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.put("dialect", "mysql");
        properties.put("offsetAsPageNum", "true");
        properties.put("rowBoundsWithCount", "true");
        properties.put("pageSizeZero", "true");
        properties.put("reasonable", "true");
        properties.put("supportMethodsArguments", "false");
        properties.put("returnPageInfo", "none");
        pageHelper.setProperties(properties);
        Interceptor[] interceptors = new Interceptor[1];
        interceptors[0] = pageHelper;
        sessionFactory.setPlugins(interceptors);
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "messageTransactionManager")
    public DataSourceTransactionManager messageTransactionManager(@Qualifier("messageDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("messageSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.weimob.bs.multidb.dao.mysql.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.weimob.bs.multidb.mysql.mapper.BaseMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
