package com.weimob.bs.multidb.facade.qs;

import com.weimob.bs.multidb.dao.mysql.model.qs.QS;
import com.weimob.bs.multidb.dao.mysql.service.BaseServiceImpl;
import com.weimob.bs.multidb.dao.mysql.service.qs.QSServiceImpl;
import com.weimob.bs.multidb.facade.base.BaseFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Adam on 2016/4/26.
 */
@Service
@Transactional
@Lazy
public class QSFacadeService extends BaseFacadeService<QS> {
}
