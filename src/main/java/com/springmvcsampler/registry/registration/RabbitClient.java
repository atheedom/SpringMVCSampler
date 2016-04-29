package com.springmvcsampler.registry.registration;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.springmvcsampler.registry.registration.config.ServiceRegistryRabbitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by atheedom on 19/02/2016.
 */
@Component
public class RabbitClient implements MessageClient {

    private Channel channel;
    private Connection connection;
    private AMQP.Queue.DeclareOk declareOk;
    private ServiceRegistryRabbitConfig config;
    private boolean started = false;

    private HeartBeatPayload heartBeatPayload;

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private ServiceRegistryConfig serviceRegistryConfig;

    public void init() {
        this.config = (ServiceRegistryRabbitConfig) serviceRegistryConfig;
        this.heartBeatPayload = new HeartBeatPayload(config.getApplicationName());
    }


    @Override
    public boolean start() {

        try {
            connect();
        } catch (Exception e) {
            return false;
        }
        started = true;
        return true;
    }

    private void connect() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(config.getHost());
        factory.setUsername(config.getUsername());
        factory.setPassword(config.getPassword());
        factory.setPort(config.getPort());
        connection = factory.newConnection();
        channel = connection.createChannel();
        declareOk = channel.queueDeclare(config.getQueueName(), false, false, false, null);
        AMQP.Queue.BindOk bind = channel.queueBind(config.getQueueName(), "bus", "#");
    }


    public void startHeartBeat() throws Exception {
        checkStarted();
        scheduledExecutorService.scheduleWithFixedDelay((Runnable) () -> {
            try {
                System.out.println("Sending heartbeat message");
                heartBeatPayload.setTimeStamp(Instant.now().toEpochMilli());
                channel.basicPublish("bus", config.getQueueName(), null, MessageHelper.heartBeat(heartBeatPayload).getBytes());
            } catch (Exception e) {
                System.out.println("Error sending heartbeat message " + e);
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    private void checkStarted() throws Exception {
        if (!started) {
            throw new Exception("Service not started");
        }
    }

    public void deregister() throws Exception {
        checkStarted();

        // Send message to deregister service

        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endHeartBeat() throws Exception {
        checkStarted();

    }


//        String message = "Hello World!";
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");
//

}
