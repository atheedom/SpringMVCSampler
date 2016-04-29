package com.springmvcsampler.registry.registration;


/**
 * Created by atheedom on 29/04/2016.
 */
public class HeartBeatPayload extends BaseEntity {

    private String applicationName;

    private Long timeStamp;

    public HeartBeatPayload(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
