package com.weimob.bs.multidb.service.impl;

import com.weimob.bs.multidb.dao.mysql.mapper.MessageMapper;
import com.weimob.bs.multidb.dao.mysql.model.Message;
import com.weimob.bs.multidb.mysql.mapper.BaseMapper;
import com.weimob.bs.multidb.mysql.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2016/4/25.
 */
@Service
public class MessageService extends BaseService<Message> {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public BaseMapper<Message> getMapper() {
        return this.messageMapper;
    }
}
