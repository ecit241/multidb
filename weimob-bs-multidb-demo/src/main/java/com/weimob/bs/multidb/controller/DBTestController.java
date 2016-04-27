package com.weimob.bs.multidb.controller;

import com.weimob.bs.multidb.dao.mysql.model.message.Message;
import com.weimob.bs.multidb.dao.mysql.model.qs.QS;
import com.weimob.bs.multidb.facade.message.MessageFacadeService;
import com.weimob.bs.multidb.facade.qs.QSFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Adam on 2016/4/25.
 */
@RestController
@RequestMapping("/")
public class DBTestController {
    @Autowired
    MessageFacadeService messageService;

    @Autowired
    QSFacadeService qsFacadeService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() {
        List<Message> messages = messageService.selectAll();
        return messages;
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public Object test1() {
        List<QS> messages = qsFacadeService.selectAll();
        return messages;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public Object test2() {
        Message message = new Message();
        Integer integer = messageService.insert(message);

        return integer;
    }
}
