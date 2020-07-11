package com.eriumyedu.task

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface RetroService {
  /*  @GET("/api/getLocations")
    fun getMovies(@Query("api_key") key: String): Call<PopularMovies>
*/
    @POST("/api/getLocations")
    fun getLocatoinsdata(@Body body:HashMap<String, String>): Call<ResponseBody?>?

 /* @POST("/api/getLocations")
  fun getLocatoinsdata(@Body body: HashMap<String, String>): ResponseBody?*/
}