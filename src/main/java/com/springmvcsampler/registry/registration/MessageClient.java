package com.springmvcsampler.registry.registration;

/**
 * Created by atheedom on 19/02/2016.
 */
public interface MessageClient {

    void init();

    boolean start();

    void startHeartBeat() throws Exception;

    void deregister() throws Exception;

    void endHeartBeat() throws Exception;

}
