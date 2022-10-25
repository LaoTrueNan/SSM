package com.byd.gzq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Leonard
 * @date 2022/9/26 10:09
 */
@Component
public class MQUtils {
    private final ConnectionFactory cf = new ConnectionFactory();

    private final Logger log = LogManager.getLogger(MQUtils.class);
    public MQUtils() {
        log.info("MQ has been loaded...");
    }

    @Bean
    public Channel getChannel(){
        cf.setHost("localhost");
        try {
            Channel c = cf.newConnection().createChannel();
            c.queueDeclare("ssm",false,false,false,null);
            return c;
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
