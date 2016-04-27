package com.weimob.bs.multidb.facade.message;

import com.weimob.bs.multidb.dao.mysql.model.message.Message;
import com.weimob.bs.multidb.dao.mysql.service.BaseServiceImpl;
import com.weimob.bs.multidb.dao.mysql.service.message.MessageServiceImpl;
import com.weimob.bs.multidb.facade.base.BaseFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Adam on 2016/4/25.
 */
@Service
@Transactional
@Lazy
public class MessageFacadeService extends BaseFacadeService<Message> {
}
