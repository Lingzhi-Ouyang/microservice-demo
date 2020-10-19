package com.njoy.springcloud.controller;

import com.njoy.springcloud.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ouyanglingzhi
 */
@RestController
public class MessageProviderController {

    @Resource
    private MessageProvider messageProvider;

    @GetMapping(value = "/send")
    public String send(){
        return messageProvider.send();
    }
}
