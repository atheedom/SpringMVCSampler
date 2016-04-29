package com.springmvcsampler.registry.registration.pool;

import org.boon.json.JsonSerializer;
import org.boon.json.JsonSerializerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by atheedom on 27/11/2014.
 */
public class JsonSerializerPool extends AbstractObjectPool<JsonSerializer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSerializerPool.class);
    private LinkedBlockingQueue<JsonSerializer> pool = new LinkedBlockingQueue<>();
    private int poolSize = 200;
    private int pollTimeout = 3000;

    public JsonSerializerPool() {
        LOGGER.debug("[JSON SERIALIZER POOL] Enter constructor");
        initialize(poolSize, pool);
        LOGGER.debug("[JSON SERIALIZER POOL] Exit constructor");
    }

    public JsonSerializerPool(int poolSize) {
        LOGGER.debug("[JSON SERIALIZER POOL] Enter constructor. Creating pool size: " + poolSize);
        initialize(poolSize, pool);
        LOGGER.debug("[JSON SERIALIZER POOL] Exit constructor");
    }

    @Override
    public JsonSerializer borrowObject() throws PoolDepletedException{
        LOGGER.debug("[JSON SERIALIZER POOL] Enter JsonSerializerPool.borrowObject");
        JsonSerializer jsonSerializer;
        try {
            jsonSerializer = pool.poll(this.pollTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("[JSON SERIALIZER POOL] Thread Interrupted: " + e);
            LOGGER.error("[JSON SERIALIZER POOL] Json Serializer pool empty and not replenished within timeout limits.");
            throw new PoolDepletedException("The json serializer pool is empty and was not replenished within timeout limits.");
        }

        LOGGER.debug("[JSON SERIALIZER POOL] Exit JsonSerializerPool.borrowObject");
        return jsonSerializer;
    }

    @Override
    protected JsonSerializer createObject() {
        LOGGER.debug("[JSON SERIALIZER POOL] Enter JsonSerializerPool.createObject");
        JsonSerializer jsonSerializer = new JsonSerializerFactory().useAnnotations().useFieldsFirst().create();
        LOGGER.debug("[JSON SERIALIZER POOL] Exit JsonSerializerPool.createObject");
        return jsonSerializer;
    }

    @Override
    protected void destroyObject(AbstractQueue<JsonSerializer> pool) {
        LOGGER.debug("[JSON SERIALIZER POOL] Enter JsonSerializerPool.destroyObject");
        JsonSerializer jsonSerializer = pool.poll();
        jsonSerializer = null;
        LOGGER.debug("[JSON SERIALIZER POOL] Exit JsonSerializerPool.destroyObject");
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
