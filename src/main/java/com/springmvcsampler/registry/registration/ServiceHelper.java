package com.springmvcsampler.registry.registration;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by atheedom on 21/01/2016.
 */
@Component
@Scope("prototype")
public class ServiceHelper {

    private String serviceName;

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String serviceName() {
        return serviceName;
    }

}
