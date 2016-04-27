package com.weimob.bs.multidb.dao.mysql.service.qs;

import com.weimob.bs.multidb.dao.mysql.mapper.BaseMapper;
import com.weimob.bs.multidb.dao.mysql.mapper.qs.QSMapper;
import com.weimob.bs.multidb.dao.mysql.model.qs.QS;
import com.weimob.bs.multidb.dao.mysql.annotation.TargetDataSource;
import com.weimob.bs.multidb.dao.mysql.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2016/4/26.
 */
@Service
@TargetDataSource(name = "qsDataSource")
@Lazy
public class QSServiceImpl extends BaseServiceImpl<QS> {
}
