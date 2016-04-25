package com.weimob.bs.multidb.mysql.sjsupport.algorith;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by Adam on 2016/4/25.
 */
public abstract class BaseDatabaseAlgorith<T extends Comparable<?>> implements SingleKeyDatabaseShardingAlgorithm<T> {
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<T> shardingValue) {
        for (String each : collection) {
            if (checkValue(each, shardingValue.getValue())) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<T> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        for (T value : shardingValue.getValues()) {
            for (String each : collection) {
                if (checkValue(each, value)) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<T> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        for (String each : collection) {
            if (checkRange(each, shardingValue.getValueRange())) {
                result.add(each);
            }
        }
        return result;
    }

    public abstract boolean checkValue(String name, T value);

    public abstract boolean checkRange(String name, Range<T> range);
}
