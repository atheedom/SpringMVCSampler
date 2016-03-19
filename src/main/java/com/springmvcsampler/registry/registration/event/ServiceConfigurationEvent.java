package com.springmvcsampler.registry.registration.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by atheedom on 19/03/2016.
 */
public class ServiceConfigurationEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ServiceConfigurationEvent(Object source) {
        super(source);
    }

}
