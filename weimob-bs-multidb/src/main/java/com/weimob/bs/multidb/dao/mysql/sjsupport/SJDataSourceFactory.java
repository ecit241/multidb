package com.weimob.bs.multidb.dao.mysql.sjsupport;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSource;
import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.weimob.bs.multidb.dao.mysql.sjsupport.algorith.BaseDatabaseAlgorith;
import com.weimob.bs.multidb.dao.mysql.sjsupport.algorith.BaseTableAlgorithm;
import com.weimob.bs.multidb.dao.mysql.sjsupport.setting.DatabaseSetting;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adam on 2016/4/25.
 */
public class SJDataSourceFactory {
    private ShardingRule shardingRule;


    public SJDataSourceFactory(DatabaseSetting databaseSetting, BaseDatabaseAlgorith baseDatabaseAlgorith, BaseTableAlgorithm baseTableAlgorithm) {
        Map<String, DataSource> dataSourceMap = getDataSourceMap(databaseSetting.getUrl(), databaseSetting.getDriver(), databaseSetting.getUsername(), databaseSetting.getPassword());
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);
        TableRule tableRule = new TableRule(databaseSetting.getTbName(), Arrays.asList(databaseSetting.getTb().split(",")), dataSourceRule);
        ShardingRule shardingRule = new ShardingRule(dataSourceRule, Arrays.asList(tableRule),
                Arrays.asList(new BindingTableRule(Arrays.asList(tableRule))),
                new DatabaseShardingStrategy(databaseSetting.getTbKey(), baseDatabaseAlgorith),
                new TableShardingStrategy(databaseSetting.getTbKey(), baseTableAlgorithm));
        this.shardingRule = shardingRule;
    }

    public ShardingDataSource getDataSource() {
        return new ShardingDataSource(this.shardingRule);
    }


    private Map<String, DataSource> getDataSourceMap(String urls, String driver, String username, String password) {
        Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
        String[] urlArr = urls.split(",");
        for (String url : urlArr) {
            dataSourceMap.put(getDBName(url), createDataSource(url, driver, username, password));
        }
        return dataSourceMap;
    }

    private DataSource createDataSource(String url, String driver, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

    private String getDBName(String url) {
        String[] urlFields = url.split("\\?")[0].split("/");
        return urlFields[urlFields.length - 1];
    }
}
