package com.springmvcsampler.registry.registration.pool;

/**
 * Created by atheedom on 29/06/15.
 */
public class PoolDepletedException extends Exception   {

    private static final String MESSAGE = "The pool has been depleted";


    public PoolDepletedException() {
        super(MESSAGE);
    }

    public PoolDepletedException(String message) {
        super(message);
    }



}
