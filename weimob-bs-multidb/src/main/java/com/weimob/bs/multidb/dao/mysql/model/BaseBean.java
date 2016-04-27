package com.weimob.bs.multidb.dao.mysql.model;

import java.util.Date;

/**
 * Created by Adam on 2016/4/22.
 */
public abstract class BaseBean {
    public abstract void setCreateTime(Date date);

    public abstract void setUpdateTime(Date date);
}
