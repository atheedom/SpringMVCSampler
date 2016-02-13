package com.springmvcsampler.registry;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by atheedom on 21/01/2016.
 */
@Async
@Component
public class ServiceRegistrationAnnotationListener implements ApplicationListener<ServiceRegistrationEvent> {

//    @EventListener
//    public void hasServiceRegistrationAnnotation(ServiceRegistrationEvent serviceRegistrationEventEvent){
//
//
//    }

    private void handleEvent(ServiceRegistrationEvent serviceRegistrationEventEvent) {

    }


    @Override
    public void onApplicationEvent(ServiceRegistrationEvent event) {
        handleEvent(event);
        System.out.println("Annotation found: " + ((RegistrationConfiguration)event.getSource()).enabled());

    }
}
