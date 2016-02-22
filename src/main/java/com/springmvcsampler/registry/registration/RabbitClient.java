package com.springmvcsampler.registry.registration;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by atheedom on 19/02/2016.
 */
public class RabbitClient implements MessageClient {

    private Channel channel;
    private Connection connection;
    private AMQP.Queue.DeclareOk declareOk;
    private ServiceRegistryClient client;

    public RabbitClient(ServiceRegistryClient client) throws IOException {
        this.client = client;
        connect();
    }

    private void connect() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(client.getHost());
        factory.setUsername(client.getUsername());
        factory.setPassword(client.getPassword());
        factory.setPort(client.getPort());
        connection = factory.newConnection();
        channel = connection.createChannel();
        declareOk = channel.queueDeclare(client.getQueueName(), false, false, false, null);
    }

    public void startHeartBeat() {
        client.getScheduledExecutorService().scheduleWithFixedDelay((Runnable) () -> {
            try {
                channel.basicPublish("", client.getQueueName(), null, MessageHelper.heartBeat().getBytes());
            } catch (IOException e) {
                // Logout
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    public void deregister() {

        // Send message to deregister service

        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endHeartBeat() {

    }


//        String message = "Hello World!";
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");
//

}
