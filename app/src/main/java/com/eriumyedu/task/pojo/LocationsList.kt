package com.eriumyedu.task.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LocationsList {
    @SerializedName("code")
    @Expose
    private var code: Int? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

}