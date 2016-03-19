package com.springmvcsampler.registry.registration;

import com.springmvcsampler.registry.registration.event.ServiceConfigurationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by atheedom on 13/02/2016.
 */
@Component
public class ServiceRegistryClient implements ApplicationListener<ServiceConfigurationEvent> {

    private static final Logger LOGGER = Logger.getLogger("");

    @Autowired
    private MessageClient messageClient;

    @Override
    public void onApplicationEvent(ServiceConfigurationEvent event) {
        if(event != null) {
            try {
                go();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void go() throws Exception {

        System.out.println("");
        messageClient.init();
        messageClient.start();
        messageClient.startHeartBeat();
//        messageClient.endHeartBeat();
//        messageClient.deregister();

        // provoke scheduled task to send heartbeat


    }



}
