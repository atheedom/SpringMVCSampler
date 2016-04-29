package com.springmvcsampler.registry.registration.pool;

import org.boon.json.JsonParserAndMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by atheedom on 27/11/2014.
 */
public class JsonParserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParserFacade.class);
    private JsonParserPool jsonParserPool;

    public JsonParserFacade() {
        LOGGER.debug("[JSON PARSER FACADE] Constructing the JsonParserFacade");
        jsonParserPool = new JsonParserPool();
    }

    public JsonParserFacade(int poolSize) {
        LOGGER.debug("[JSON PARSER FACADE] Constructing the JsonParserFacade with size: " + poolSize);
        jsonParserPool = new JsonParserPool(poolSize);
    }

    public synchronized final <T> T parse(Class<T> type, String value) {

        LOGGER.debug("[JSON PARSER FACADE] Enter JsonParserFacade.parse");

        JsonParserAndMapper jsonParserAndMapper = null;
        T obj = null;
        try {
            jsonParserAndMapper = jsonParserPool.borrowObject();
            obj = jsonParserAndMapper.parse(type, value);
        } catch (PoolDepletedException e) {
            LOGGER.error("[JSON PARSER FACADE] Pool depletion exception - " + e.getMessage());
            LOGGER.error("[JSON PARSER FACADE] " + e);
        } catch (Exception e) {
            LOGGER.error("[JSON PARSER FACADE] Parser Pool Exception in Parsing - " + e.getMessage());
            LOGGER.error("[JSON PARSER FACADE] " + e);
            throw e;
        } finally {
            if (jsonParserAndMapper != null) {
                jsonParserPool.returnObject(jsonParserAndMapper);
            }
        }

        LOGGER.debug("[JSON PARSER FACADE] Exit JsonParserFacade.parse");

        return obj;
    }


    /**
     * Returns the current JSON parser pool
     *
     * @return
     */
    public JsonParserPool getJsonParserPool() {
        return jsonParserPool;
    }


    /**
     * Destroys the parser pool.
     * <p>
     * NOTE: This method will not shutdown the executor.
     */
    public void destroyPool() {
        jsonParserPool.destroyPool();
        jsonParserPool = null;
    }


    /**
     * Change the size of a pool.
     * <p>
     * Note that if the pool size is to be reduced and this method is called at run time it is possible that the final
     * pool size achieved is more than the poolSize required. This is because some objects
     * in the pool have been polled and are currently in use. If the remaining objects in the pool number less
     * than the reduction required only those in the pool will be removed.
     *
     * @param newPoolSize new pool size
     */
    public void changePoolSize(int newPoolSize) {

        LOGGER.debug("[JSON POOL FACTORY] Enter JsonParserFacade.changePoolSize with pool size: " + newPoolSize);

        if (jsonParserPool != null) {
            int size = jsonParserPool.getPool().size();
            if (size < newPoolSize) {
                int sizeToBeAdded = newPoolSize - size;
                LOGGER.debug("[JSON PARSER FACADE] Adding " + sizeToBeAdded + " JSON to pool.");
                for (int i = 0; i < sizeToBeAdded; i++) {
                    jsonParserPool.getPool().add(jsonParserPool.createObject());
                }
            } else if (size > newPoolSize) {
                int sizeToBeRemoved = size - newPoolSize;
                LOGGER.debug("[JSON PARSER FACADE] Removing " + sizeToBeRemoved + " JSON from pool.");
                for (int i = 0; i < sizeToBeRemoved; i++) {
                    jsonParserPool.destroyObject(jsonParserPool.getPool());
                }
            }
        }
    }
}
