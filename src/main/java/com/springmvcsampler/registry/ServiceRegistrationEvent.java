package com.springmvcsampler.registry;

import org.springframework.context.ApplicationEvent;

/**
 * Created by atheedom on 21/01/2016.
 */
public class ServiceRegistrationEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ServiceRegistrationEvent(Object source) {
        super(source);
    }

}