package com.weimob.bs.multidb.controller;

import com.weimob.bs.multidb.dao.mysql.model.Message;
import com.weimob.bs.multidb.facade.MessageFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by Adam on 2016/4/25.
 */
@RestController
@RequestMapping("/")
public class DBTestController {
    @Autowired
    MessageFacadeService messageService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test(@RequestParam(value = "name", required = true) String name) {
        List<Message> messages = messageService.selectAll();
        return messages;
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public Object test1(@RequestParam(value = "name", required = true) String name) {
        Message message = new Message();
        message.setContent("hahah");
        message.setReceiveDate(20160425);
        message.setMessageType(1);
        message.setCreateBy("dexin.su");
        Integer integer = messageService.insert(message);
        return integer;
    }
}
