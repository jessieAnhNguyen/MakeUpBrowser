package com.anguy39.makeupbrowser.main

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductAPI {
    @GET("api/v1/products.json")
    fun fetchProduct(
        @Query("brand") brand: String
    ): Call<List<Product>>
}