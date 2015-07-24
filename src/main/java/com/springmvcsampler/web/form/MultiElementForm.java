package com.springmvcsampler.web.form;

import java.util.List;

/**
 * Created by atheedom on 23/07/15.
 */
public class MultiElementForm {

    public enum HouseType{
        FLAT,
        BUNGALOW,
        SEMIDETACHED,
        DETACHED
    }

    private String name;

    private List<String> cities;

    private HouseType houseType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }
}
