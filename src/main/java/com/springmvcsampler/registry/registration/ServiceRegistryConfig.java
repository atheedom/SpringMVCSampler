package com.springmvcsampler.registry.registration;

import java.util.Map;

/**
 * Created by atheedom on 19/03/2016.
 */
public interface ServiceRegistryConfig {

    String getApplicationName();

    void setParameters(Map<String,String> parameters);
}
