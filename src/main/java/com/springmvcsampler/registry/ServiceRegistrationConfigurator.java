package com.springmvcsampler.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by atheedom on 21/01/2016.
 */
@Async
@Component
public class ServiceRegistrationConfigurator implements ApplicationListener<ServiceRegistrationEvent> {

    private static final Logger LOGGER = Logger.getLogger("");

    private Map<String, Object> serviceRegistrationConfig = Collections.EMPTY_MAP;


    @Autowired
    private ServiceRegistryClient serviceRegistryClient;

    @Override
    public void onApplicationEvent(ServiceRegistrationEvent event) {
        handleEvent(event);
    }

    private void handleEvent(ServiceRegistrationEvent event) {


        final String applicationName = ((RegistrationConfiguration) event.getSource()).serviceName();

        LOGGER.config(() -> "producing " + applicationName);

        String host = readProperty("host", serviceRegistrationConfig);
        String username = readProperty("username", serviceRegistrationConfig);
        String password = readProperty("password", serviceRegistrationConfig);
        String port = readProperty("port", serviceRegistrationConfig);
        String queueName = readProperty("queueName", serviceRegistrationConfig);

        LOGGER.config(() -> "host ip: " + host);

        serviceRegistryClient.Builder(applicationName);


//        return new SnoopServiceClient.Builder(applicationName)
//                .serviceUrl(serviceUrl)
//                .build();

    }

    private String readProperty(final String key, Map<String, Object> snoopConfig) {

        String property = Optional.ofNullable(System.getProperty(key))
                .orElseGet(() -> {
                    String envProp = Optional.ofNullable(System.getenv(key))
                            .orElseGet(() -> {
                                String confProp = Optional.ofNullable(snoopConfig.get(key))
                                        .orElseThrow(() -> new RuntimeException(key + " must be configured either in application.yml or as env or system property"))
                                        .toString();
                                return confProp;
                            });
                    return envProp;
                });

        return property;
    }

    @PostConstruct
    private void init() {

        try {
            Yaml yaml = new Yaml();
            Map<String, Object> props = (Map<String, Object>) yaml.load(this.getClass().getResourceAsStream("/service-registration.yml"));

            serviceRegistrationConfig = (Map<String, Object>) props.get("registration");

        } catch (YAMLException e) {
            LOGGER.config(() -> "No configuration file. Using env properties.");
        }
    }

//    @EventListener
//    public void hasServiceRegistrationAnnotation(ServiceRegistrationEvent serviceRegistrationEventEvent){
//
//
//    }
}
