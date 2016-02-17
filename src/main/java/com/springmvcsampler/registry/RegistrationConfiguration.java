package com.springmvcsampler.registry;

/**
 * Created by atheedom on 21/01/2016.
 */
public class RegistrationConfiguration {

    private String serviceName;

    public RegistrationConfiguration(String serviceName) {
        this.serviceName = serviceName;
    }

    public String serviceName() {
        return serviceName;
    }


}
