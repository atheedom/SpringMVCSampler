package com.springmvcsampler.registry;

/**
 * Created by atheedom on 21/01/2016.
 */
public class RegistrationConfiguration {

    private boolean enabled;

    public RegistrationConfiguration(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean enabled() {
        return enabled;
    }


}
