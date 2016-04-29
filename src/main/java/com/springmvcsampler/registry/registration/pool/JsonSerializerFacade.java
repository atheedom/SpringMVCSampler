package com.springmvcsampler.registry.registration.pool;

import org.boon.json.JsonSerializer;
import org.boon.primitive.CharBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by atheedom on 27/11/2014.
 */
public class JsonSerializerFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSerializerFacade.class);
    private JsonSerializerPool jsonSerializerPool;


    /**
     * This constructor should only be called from the Factory class
     */
    public JsonSerializerFacade() {
        LOGGER.debug("[JSON SERIALIZER FACADE] Constructing the JsonSerializerPool");
        jsonSerializerPool = new JsonSerializerPool();
    }

    /**
     * This constructor should only be called from the Factory class
     */
    public JsonSerializerFacade(int poolSize) {
        LOGGER.debug("[JSON SERIALIZER FACADE] Constructing the JsonSerializerPool");
        jsonSerializerPool = new JsonSerializerPool(poolSize);
    }

    /**
     * Serializes the given object by borrowing an object from the serializer pool.
     *
     * @param obj the object to serialise
     * @return a CharBuf object (like StringBuilder) containing the serialized object
     */
    public synchronized CharBuf serialize(Object obj) {

        LOGGER.debug("[JSON SERIALIZER FACADE] Enter JsonSerializerFacade.serialize");

        JsonSerializer jsonSerializer = null;
        CharBuf charBuf = null;
        try {
            jsonSerializer = jsonSerializerPool.borrowObject();
            charBuf = jsonSerializer.serialize(obj);
        } catch (PoolDepletedException e) {
            LOGGER.error("[JSON SERIALIZER FACADE] Pool depletion exception - " + e.getMessage());
            LOGGER.error("[JSON SERIALIZER FACADE]", e);
            charBuf = new CharBuf("POOL DEPLETED EXCEPTION THROWN".getBytes());
        } catch (Exception e) {
            LOGGER.error("[JSON SERIALIZER FACADE] Exception in serializtion - " + e.getMessage());
            LOGGER.error("[JSON SERIALIZER FACADE]", e);
            throw e;
        } finally {
            if (jsonSerializer != null) {
                jsonSerializerPool.returnObject(jsonSerializer);
            }
        }

        LOGGER.debug("[JSON SERIALIZER FACADE] Exit JsonSerializerFacade.serialize");

        return charBuf;
    }


    /**
     * Serializes the given object by borrowing an object from the serializer pool.
     *
     * @param obj the object to serialise
     * @return a String of the JSON representation of the given object
     */
    public synchronized String serializeToJson(Object obj) {
        LOGGER.debug("[JSON SERIALIZER FACADE] Enter JsonSerializerFacade.serializeToJson");
        String out = null;
        CharBuf buf = this.serialize(obj);
        if (buf != null) {
            out = buf.toString();
        }
        LOGGER.debug("[JSON SERIALIZER FACADE] Exit JsonSerializerFacade.serializeToJson");
        return out;
    }

    /**
     * Returns the current JSON Serializer pool
     *
     * @return jsonSerializerPool
     */
    public JsonSerializerPool getJsonSerializerPool() {
        return jsonSerializerPool;
    }


    /**
     * Destroys the parser pool.
     * <p>
     * NOTE: This method will not shutdown the executor.
     */
    public void destroyPool() {
        jsonSerializerPool.destroyPool();
        jsonSerializerPool = null;
    }

    /**
     * Destroys and shuts down the pool.
     */
    public void shutdown() {
        jsonSerializerPool.shutdown();
    }


    /**
     * Change the size of a pool.
     * <p>
     * Note that if the pool size is to be reduced and this method is called at run time it is possible that the final
     * pool size achieved is more than the poolSize required. This is because some objects
     * in the pool have been polled and are currently in use. If the remaining objects in the pool number less
     * than the reduction required only those in the pool will be removed.
     *
     * @param poolSize new pool size
     */
    public void changePoolSize(int poolSize) {
        if (jsonSerializerPool != null) {
            int size = jsonSerializerPool.getPool().size();
            if (size < poolSize) {
                int sizeToBeAdded = poolSize - size;
                LOGGER.debug("[JSON PARSER FACADE] Adding " + sizeToBeAdded + " JSON to pool.");
                for (int i = 0; i < sizeToBeAdded; i++) {
                    jsonSerializerPool.getPool().add(jsonSerializerPool.createObject());
                }
            } else if (size > poolSize) {
                int sizeToBeRemoved = size - poolSize;
                LOGGER.debug("[JSON PARSER FACADE] Removing " + sizeToBeRemoved + " JSON from pool.");
                for (int i = 0; i < sizeToBeRemoved; i++) {
                    jsonSerializerPool.destroyObject(jsonSerializerPool.getPool());
                }
            }
        }
    }
}
