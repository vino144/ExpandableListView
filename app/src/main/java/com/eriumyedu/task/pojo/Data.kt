package com.eriumyedu.task.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Data {
    @SerializedName("country")
    @Expose
    private var country: List<Country?>? = null

    fun getCountry(): List<Country?>? {
        return country
    }

    fun setCountry(country: List<Country?>?) {
        this.country = country
    }
}