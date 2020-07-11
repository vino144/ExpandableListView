package com.eriumyedu.task.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Country {
    @SerializedName("country_name")
    @Expose
    private var countryName: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("level")
    @Expose
    private var level: String? = null

    @SerializedName("state")
    @Expose
    private var state: List<State?>? = null

    fun getCountryName(): String? {
        return countryName
    }

    fun setCountryName(countryName: String?) {
        this.countryName = countryName
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

    fun getState(): List<State?>? {
        return state
    }

    fun setState(state: List<State?>?) {
        this.state = state
    }
}