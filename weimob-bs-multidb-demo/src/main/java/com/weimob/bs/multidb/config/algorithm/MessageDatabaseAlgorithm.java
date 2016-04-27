package com.weimob.bs.multidb.config.algorithm;

import com.google.common.collect.Range;
import com.weimob.bs.multidb.dao.mysql.sjsupport.algorith.BaseDatabaseAlgorith;
import com.weimob.bs.multidb.utils.CommonUtil;

/**
 * Created by Adam on 2016/4/1.
 */
public class MessageDatabaseAlgorithm extends BaseDatabaseAlgorith<Integer> {

    @Override
    public boolean checkValue(String name, Integer value) {
        return name.endsWith("_" + value.toString().substring(4, 6));
    }

    @Override
    public boolean checkRange(String name, Range<Integer> range) {
        String[] names = name.split("_");
        return CommonUtil.strToInteger(names[names.length - 1], 0).compareTo(range.lowerEndpoint()) >= 0 && CommonUtil.strToInteger(names[names.length - 1], 0).compareTo(range.upperEndpoint()) <= 0;
    }
}
