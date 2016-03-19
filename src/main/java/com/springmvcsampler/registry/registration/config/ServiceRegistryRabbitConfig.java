package com.springmvcsampler.registry.registration.config;

import com.springmvcsampler.registry.registration.ServiceRegistryConfig;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by atheedom on 13/02/2016.
 */
@Component
public class ServiceRegistryRabbitConfig implements ServiceRegistryConfig {

    private static final Logger LOGGER = Logger.getLogger("");

    private String applicationName;
    private String host;
    private String username;
    private String password;
    private String port;
    private String queueName;

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

        ServiceRegistryConfig build() {
            return new ServiceRegistryRabbitConfig(this);
        }
    }

    private ServiceRegistryRabbitConfig() {
    }

    private ServiceRegistryRabbitConfig(final Builder builder) {
        this.applicationName = builder.applicationName;
        this.host = builder.host;
        this.username = builder.username;
        this.password = builder.password;
        this.port = builder.port;
        this.queueName = builder.queueName;

        LOGGER.info(() -> "Client created for " + applicationName);
    }


    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public void setParameters(Map<String, String> parameters) {
        this.applicationName = parameters.get("applicationName");
        this.host = parameters.get("host");
        this.username = parameters.get("username");
        this.password = parameters.get("password");
        this.port = parameters.get("port");
        this.queueName = parameters.get("queueName");
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
        return Integer.parseInt(port);
    }

    public String getQueueName() {
        return queueName;
    }
}
