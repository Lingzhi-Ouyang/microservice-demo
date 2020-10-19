package com.njoy.springcloud.service.impl;

import com.njoy.springcloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author ouyanglingzhi
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("****** Sending uuid : {} *******", serial);
        return null;
    }
}
