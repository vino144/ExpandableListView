package com.eriumyedu.task.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal


class City {
    @SerializedName("city_name")
    @Expose
    private var cityName: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("channel_locations_id")
    @Expose
    private var channelLocationsId: String? = null

    @SerializedName("latitude")
    @Expose
    private var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    private var longitude: String? = null

    @SerializedName("level")
    @Expose
    private var level: String? = null

    fun getCityName(): String? {
        return cityName
    }

    fun setCityName(cityName: String?) {
        this.cityName = cityName
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getChannelLocationsId(): String? {
        return channelLocationsId
    }

    fun setChannelLocationsId(channelLocationsId: String?) {
        this.channelLocationsId = channelLocationsId
    }

    fun getLatitude(): String? {
        return latitude
    }

    fun setLatitude(latitude: String?) {
        this.latitude = latitude
    }

    fun getLongitude(): String? {
        return longitude
    }

    fun setLongitude(longitude: String?) {
        this.longitude = longitude
    }

    fun getLevel(): String? {
        return level
    }

    fun setLevel(level: String?) {
        this.level = level
    }

}