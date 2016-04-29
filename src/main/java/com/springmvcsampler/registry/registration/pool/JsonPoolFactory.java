package com.springmvcsampler.registry.registration.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by atheedom on 27/11/2014.
 */
public class JsonPoolFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPoolFactory.class);
    private static JsonParserFacade jsonParserFacade;
    private static JsonSerializerFacade jsonSerializerFacade;

    /**
     * Returns an instance of the json parser facade.
     * <p>
     * The first call constructs the facade and subsequent calls returns
     * a reference to the previously created.
     *
     * @return JsonParserFacade instance
     */
    public static JsonParserFacade getParserFacadeInstance() {

        LOGGER.debug("[JSON POOL FACTORY] Enter JsonPoolFactory.getParserFacadeInstance");
        if (jsonParserFacade == null) {
            synchronized (JsonPoolFactory.class) {
                if (jsonParserFacade == null) {
                    jsonParserFacade = new JsonParserFacade();
                }
            }
        }
        LOGGER.debug("[JSON POOL FACTORY] Exit JsonPoolFactory.getParserFacadeInstance");
        return jsonParserFacade;
    }

    /**
     * Returns an instance of the json parser facade with pool of the given size.
     * If the pool size is different from the pool's current size the pool is distroyed
     * and recreated.
     * <p>
     * The first call constructs the facade and subsequent calls returns
     * a reference to the previously created, as long as the pool size has not changed.
     *
     * @param newPoolSize pool size
     * @return JsonParserFacade instance
     */
    public static JsonParserFacade getParserFacadeInstance(int newPoolSize) {

        LOGGER.debug("[JSON POOL FACTORY] Enter JsonPoolFactory.getParserFacadeInstance with pool size: " + newPoolSize);

        if (jsonParserFacade == null || jsonParserFacade.getJsonParserPool().getPool().size() != newPoolSize) {
            synchronized (JsonPoolFactory.class) {
                if (jsonParserFacade == null || jsonParserFacade.getJsonParserPool().getPool().size() != newPoolSize) {
                    if (jsonParserFacade != null) {
                        jsonParserFacade.changePoolSize(newPoolSize);
                    } else {
                        jsonParserFacade = new JsonParserFacade(newPoolSize);
                    }
                }
            }
        }
        LOGGER.debug("[JSON POOL FACTORY] Exit JsonPoolFactory.getParserFacadeInstance");
        return jsonParserFacade;
    }

    /**
     * Returns an instance of the json serializer facade.
     * <p>
     * The first call constructs the facade and subsequent calls returns
     * a reference to the previously created.
     *
     * @return JsonSerializerFacade instance
     */
    public static JsonSerializerFacade getSerializerFacadeInstance() {

        LOGGER.debug("[JSON POOL FACTORY] Enter JsonPoolFactory.getSerializerFacadeInstance");
        if (jsonSerializerFacade == null) {
            synchronized (JsonPoolFactory.class) {
                if (jsonSerializerFacade == null) {
                    jsonSerializerFacade = new JsonSerializerFacade();
                }
            }
        }
        LOGGER.debug("[JSON POOL FACTORY] Exit JsonPoolFactory.getSerializerFacadeInstance");
        return jsonSerializerFacade;
    }


    /**
     * Returns an instance of the json serializer facade.
     * <p>
     * The first call constructs the facade and subsequent calls returns
     * a reference to the previously created.
     *
     * @return JsonSerializerFacade instance
     */
    public static JsonSerializerFacade getSerializerFacadeInstance(int newPoolSize) {

        LOGGER.debug("[JSON POOL FACTORY] Enter JsonPoolFactory.getSerializerFacadeInstance with pool size: " + newPoolSize);
        if (jsonSerializerFacade == null || jsonSerializerFacade.getJsonSerializerPool().getPool().size() != newPoolSize) {
            synchronized (JsonPoolFactory.class) {
                if (jsonSerializerFacade == null || jsonSerializerFacade.getJsonSerializerPool().getPool().size() != newPoolSize) {
                    if (jsonSerializerFacade != null) {
                        jsonSerializerFacade.changePoolSize(newPoolSize);
                    } else {
                        jsonSerializerFacade = new JsonSerializerFacade(newPoolSize);
                    }
                }
            }
        }
        LOGGER.debug("[JSON POOL FACTORY] Exit JsonPoolFactory.getSerializerFacadeInstance");
        return jsonSerializerFacade;
    }


    /**
     * Destroys the pool and sets the serializer facade to null
     * <p>
     * This method should be called if the pool is to be reinitialised with a new pool
     * of a different size.
     */
    public static void destroySerializerFacadeInstance() {
        jsonSerializerFacade.destroyPool();
        jsonSerializerFacade = null;
    }

    /**
     * Destroys the pool and sets the parser facade to null
     * <p>
     * This method should be called if the pool is to be reinitialised with a new pool
     * of a different size.
     */
    public static void destroyParserFacadeInstance() {
        jsonParserFacade.destroyPool();
        jsonParserFacade = null;
    }
}
