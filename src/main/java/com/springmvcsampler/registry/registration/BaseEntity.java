package com.springmvcsampler.registry.registration;

/**
 * Created by atheedom on 29/04/2016.
 */
abstract class BaseEntity {

    // private static final JsonSerializerFacade JSON_SERIALIZER = JsonPoolFactory.getSerializerFacadeInstance();

    /**
     * Generates a JSON representation of this entity.
     *
     * @return a JSON representation of this entity.
     */
    public String toJson() {
        return this.toString();
//        return JSON_SERIALIZER.serialize(this).toString();
    }

}
