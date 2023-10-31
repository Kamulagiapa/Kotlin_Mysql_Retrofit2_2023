package com.example.muhammad_idris.Api

import com.example.muhammad_idris.Model.DataItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    @GET("mahasiswa")
    fun getmahasiswa():
            retrofit2.Call<com.example.muhammad_idris.Model.Response>

}