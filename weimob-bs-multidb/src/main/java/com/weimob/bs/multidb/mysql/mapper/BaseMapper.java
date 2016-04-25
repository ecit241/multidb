package com.weimob.bs.multidb.mysql.mapper;

import com.weimob.bs.multidb.mysql.model.BaseBean;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Adam on 2016/4/22.
 */
public interface BaseMapper<T extends BaseBean> extends Mapper<T>, MySqlMapper<T> {
}
