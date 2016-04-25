package com.weimob.bs.multidb.facade;

import com.weimob.bs.multidb.mysql.facade.BaseFacadeService;
import com.weimob.bs.multidb.mysql.service.BaseService;
import com.weimob.bs.multidb.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2016/4/25.
 */
@Service
public class MessageFacadeService extends BaseFacadeService {
    @Autowired
    MessageService messageService;

    @Override
    public BaseService getService() {
        return this.messageService;
    }
}
