package com.weimob.bs.multidb.config;

import com.github.pagehelper.PageHelper;
import com.weimob.bs.multidb.config.algorithm.MessageDatabaseAlgorithm;
import com.weimob.bs.multidb.config.algorithm.MessageTableAlgorithm;
import com.weimob.bs.multidb.config.setting.QSDataBaseSetting;
import com.weimob.bs.multidb.dao.mysql.datasource.MultiDataSource;
import com.weimob.bs.multidb.dao.mysql.sjsupport.SJDataSourceFactory;
import com.weimob.bs.multidb.dao.mysql.sjsupport.setting.DatabaseSetting;
import com.weimob.bs.multidb.dao.mysql.sjsupport.setting.MybatisSetting;
import com.weimob.bs.multidb.dao.mysql.spring.EnableMultiDB;
import com.weimob.bs.multidb.dao.utils.SpringBeanUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Adam on 2016/3/18.
 */
@Configuration
//@AutoConfigureAfter({EnableMultiDB.class})
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

    @ConfigurationProperties(prefix = "database.qs")
    @Bean
    public QSDataBaseSetting qsDataBaseSetting() {
        return new QSDataBaseSetting();
    }


    @Bean(name = "messageDataSource")
    public DataSource getShardingDataSource(DatabaseSetting databaseSetting) {
        SJDataSourceFactory sjDataSourceFactory = new SJDataSourceFactory(databaseSetting, new MessageDatabaseAlgorithm(), new MessageTableAlgorithm());
        return sjDataSourceFactory.getDataSource();
    }

    @Bean(name = "qsDataSource")
    public DataSource getQSDataSource(QSDataBaseSetting qsDataBaseSetting) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(qsDataBaseSetting.getDriver());
        config.setJdbcUrl(qsDataBaseSetting.getUrl());
        config.setUsername(qsDataBaseSetting.getUsername());
        config.setPassword(qsDataBaseSetting.getPassword());
        return new HikariDataSource(config);
    }

    @Bean(name = "multiDataSource")
    public DataSource getMultiDataSource(@Qualifier("messageDataSource") DataSource messageDataSource,
                                         @Qualifier("qsDataSource") DataSource qsDataSource) {
        MultiDataSource multiDataSource = new MultiDataSource();
        multiDataSource.setDefaultTargetDataSource(messageDataSource);
        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("messageDataSource", messageDataSource);
        hashMap.put("qsDataSource", qsDataSource);
        multiDataSource.setTargetDataSources(hashMap);
        return multiDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory messageSqlSessionFactory(@Qualifier("multiDataSource") DataSource dataSource, MybatisSetting mybatisSetting) throws Exception {
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
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager messageTransactionManager(@Qualifier("multiDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.weimob.bs.multidb.dao.mysql.mapper.*");
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.weimob.bs.multidb.dao.mysql.mapper.BaseMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
