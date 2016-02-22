package com.springmvcsampler.registry.registration;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

/**
 * Created by atheedom on 13/02/2016.
 */
@Component
public class ServiceRegistryClient {

    private static final Logger LOGGER = Logger.getLogger("");

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private  String applicationName;
    private  String host;
    private  String username;
    private  String password;
    private  int port;
    private  String queueName;


    static final class Builder {

        private final String applicationName;
        private String host;
        private String username;
        private String password;
        private String port;
        private String queueName;


        Builder(final String applicationName) {
            this.applicationName = applicationName;
        }

        Builder host(final String host) {
            this.host = host;
            return this;
        }

        Builder username(final String username) {
            this.username = username;
            return this;
        }

        Builder password(final String password) {
            this.password = password;
            return this;
        }

        Builder port(final String port) {
            this.port = port;
            return this;
        }

        Builder queueName(final String queueName) {
            this.queueName = queueName;
            return this;
        }

        ServiceRegistryClient build() {
            return new ServiceRegistryClient(this);
        }
    }

    public ServiceRegistryClient() {

    }

    private ServiceRegistryClient(final Builder builder) {
        this.applicationName = builder.applicationName;
        this.host = builder.host;
        this.username = builder.username;
        this.password = builder.password;
        this.port = Integer.parseInt(builder.port);
        this.queueName = builder.queueName;

        LOGGER.info(() -> "Client created for " + applicationName);
    }




    public void go() {

        // send message to client on the client configs given
        MessageClient rabbitClient = null;
        try {
            rabbitClient = new RabbitClient(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rabbitClient.startHeartBeat();
        rabbitClient.endHeartBeat();
        rabbitClient.deregister();

        // provoke scheduled task to send heartbeat


    }


    public String getApplicationName() {
        return applicationName;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getQueueName() {
        return queueName;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}
