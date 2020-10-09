package com.verardo.bootcamp.weather;

public interface GeocodingServicesInterface {
    public Location convertAddressToLocation(String address) throws Exception;
}
