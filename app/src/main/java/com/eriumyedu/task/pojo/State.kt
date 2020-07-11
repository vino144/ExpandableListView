package com.eriumyedu.task.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class State {
    @SerializedName("state_name")
    @Expose
    private var stateName: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("level")
    @Expose
    private var level: String? = null

    @SerializedName("city")
    @Expose
    private var city: List<City?>? = null

    fun getStateName(): String? {
        return stateName
    }

    fun setStateName(stateName: String?) {
        this.stateName = stateName
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getLevel(): String? {
        return level
    }

    fun setLevel(level: String?) {
        this.level = level
    }

    fun getCity(): List<City?>? {
        return city
    }

    fun setCity(city: List<City?>?) {
        this.city = city
    }


}