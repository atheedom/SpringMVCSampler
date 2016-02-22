package com.springmvcsampler.registry.client;

import java.util.logging.Logger;

/**
 * Created by atheedom on 13/02/2016.
 */
//@Component
public class ServiceRegistryClient {

    private static final Logger LOGGER = Logger.getLogger("");

    private final String applicationName;
    private final String host;
    private final String username;
    private final String password;
    private final String port;
    private final String queueName;

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

    private ServiceRegistryClient(final Builder builder) {
        this.applicationName = builder.applicationName;
        this.host = builder.host;
        this.username = builder.username;
        this.password = builder.password;
        this.port = builder.port;
        this.queueName = builder.queueName;

        LOGGER.info(() -> "Client created for " + applicationName);
    }

}
