/*
 * Copyright (C) Indigo Code Collective - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Indigo Code Collective, 2014
 */

package com.springmvcsampler.registry.registration.pool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractQueue;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by atheedom on 27/08/2014.
 */
public abstract class AbstractObjectPool<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractObjectPool.class);

    private AbstractQueue<T> pool;
    private ScheduledExecutorService executorService;

    public AbstractObjectPool() {
    }


    /**
     * Initialise the pool and populate it with poolSize number of objects
     *
     * @param poolSize the size of object to initialise the pool
     */
    public void initialize(int poolSize, AbstractQueue<T> pool) {
        LOGGER.debug("[OBJECT POOL] Object queue initializing.");
        setPool(pool);
        for (int i = 0; i < poolSize; i++) {
            pool.add(createObject());
        }
        LOGGER.debug("[OBJECT POOL] Object queue initialized.");
    }

    /**
     * Gets the pool
     *
     * @return AbstractQueue pool object
     */
    public AbstractQueue<T> getPool() {
        return pool;
    }


    /**
     * Sets the pool.
     *
     * @param pool the pool to set
     */
    public void setPool(AbstractQueue<T> pool) {
        this.pool = pool;
    }


    /**
     * Gets the next free object from the pool.
     *
     * Different strategies can be implemented to deal with a
     * situation where the pool doesn't contain any objects.
     *
     * Some possible options:
     *
     * 1. a new object will be created and given to the caller of this method.
     *
     * @return T borrowed object
     */
    public abstract T borrowObject() throws PoolDepletedException;


    /**
     * Returns object back to the pool.
     *
     * Possible implementation may include code to clean/reset the
     * object to initial values.
     *
     * @param object object to be returned
     */
    public void returnObject(T object) {
        if (object == null) {
            return;
        }
        this.pool.offer(object);
    }


    /**
     * Creates a new object.
     *
     * @return T new object
     */
    protected abstract T createObject();


    /**
     * Destroys an object.
     *
     * @param pool the pool to destroy
     */
    protected abstract void destroyObject(AbstractQueue<T> pool);


    /**
     * Shutdown this pool's executor service and deletes the queue pool.
     *
     * NOTE: This method should be overridden in the subclass as the executor reference
     * will be null
     */
    public void shutdown() {
        LOGGER.debug("[QUEUE POOL] Pool queue shutting down.");

        // Destroys the entire pool.
        destroyPool();

        if (executorService != null) {
            LOGGER.debug("[OBJECT POOL] Shutting down executor service.");
            executorService.shutdown();
            executorService = null;
        }
        LOGGER.debug("[OBJECT POOL] Pool shut down.");
    }

    /**
     * Destroys the entire pool.
     */
    public void destroyPool() {
        LOGGER.debug("[OBJECT POOL] Destroy the pool.");
        while (!pool.isEmpty()) {
            destroyObject(pool);
        }
        pool = null;
        LOGGER.debug("[OBJECT POOL] Pool destroyed.");
    }

    /**
     * Reinitializes the pool.
     */
    public abstract void reinitializePool(PoolConfiguration poolConfiguration);


    /**
     * Pool Configuration inner class
     */
    class PoolConfiguration {}

}

