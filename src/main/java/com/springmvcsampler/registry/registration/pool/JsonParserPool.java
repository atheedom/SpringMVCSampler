package com.springmvcsampler.registry.registration.pool;

import org.boon.json.JsonParserAndMapper;
import org.boon.json.JsonParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by atheedom on 27/11/2014.
 */
public class JsonParserPool extends AbstractObjectPool<JsonParserAndMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParserPool.class);
    private LinkedBlockingQueue<JsonParserAndMapper> pool = new LinkedBlockingQueue<>();
    private int poolSize = 20;
    private int pollTimeout = 3000;

    public JsonParserPool() {
        LOGGER.debug("[JSON PARSER POOL] Enter constructor");
        initialize(poolSize, pool);
        LOGGER.debug("[JSON PARSER POOL] Exit constructor");
    }

    public JsonParserPool(int poolSize) {
        LOGGER.debug("[JSON PARSER POOL] Enter constructor. Setting pool size: " + poolSize);
        initialize(poolSize, pool);
        LOGGER.debug("[JSON PARSER POOL] Exit constructor");
    }

    @Override
    public JsonParserAndMapper borrowObject() throws PoolDepletedException{
        LOGGER.debug("[JSON PARSER POOL] Enter JsonParserAndMapper.borrowObject");
        JsonParserAndMapper jsonParserAndMapper;
        try {
            jsonParserAndMapper = pool.poll(this.pollTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("[JSON PARSER POOL] Thread Interrupted: " + e);
            LOGGER.error("[JSON PARSER POOL] Json Parser pool empty and not replenished within timeout limits.");
            throw new PoolDepletedException("The json parser pool is empty and was not replenished within timeout limits.");
        }

        LOGGER.debug("[JSON PARSER POOL] Exit JsonParserAndMapper.borrowObject");
        return jsonParserAndMapper;
    }

    @Override
    protected JsonParserAndMapper createObject() {
        LOGGER.debug("[JSON PARSER POOL] Enter JsonParserPool.createObject");
        JsonParserAndMapper jsonParser = new JsonParserFactory().useAnnotations().usePropertiesFirst().create();
        LOGGER.debug("[JSON PARSER POOL] Exit JsonParserPool.createObject");
        return jsonParser;
    }

    @Override
    protected void destroyObject(AbstractQueue<JsonParserAndMapper> pool) {
        LOGGER.debug("[JSON PARSER POOL] Enter JsonParserPool.destroyObject");
        JsonParserAndMapper jsonParserAndMapper = pool.poll();
        jsonParserAndMapper = null;
        LOGGER.debug("[JSON PARSER POOL] Exit JsonParserPool.destroyObject");
    }

    @Override
    public void reinitializePool(AbstractObjectPool.PoolConfiguration poolConfiguration) {
        PoolConfiguration config = (PoolConfiguration) poolConfiguration;
        destroyPool();
        initialize(config.getPoolSize(), pool);
    }


    /**
     * Pool Configuration inner class for the Fixed Queue Pool
     */
    public final class PoolConfiguration extends AbstractObjectPool.PoolConfiguration {
        private int poolSize;
        public PoolConfiguration(int poolSize) {
            this.poolSize = poolSize;
        }
        public int getPoolSize() {
            return poolSize;
        }
    }


}
