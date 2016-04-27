package com.weimob.bs.multidb.dao.mysql.service.message;

import com.weimob.bs.multidb.dao.mysql.mapper.BaseMapper;
import com.weimob.bs.multidb.dao.mysql.mapper.message.MessageMapper;
import com.weimob.bs.multidb.dao.mysql.model.message.Message;
import com.weimob.bs.multidb.dao.mysql.annotation.TargetDataSource;
import com.weimob.bs.multidb.dao.mysql.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2016/4/25.
 */
@Service
@TargetDataSource(name = "messageDataSource")
@Lazy
public class MessageServiceImpl extends BaseServiceImpl<Message> {
}
