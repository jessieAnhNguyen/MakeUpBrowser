package com.anguy39.makeupbrowser.main

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductAPI {
    @GET("api/v1/products.json")
    fun fetchFromBrand(
        @Query("brand") brand: String
    ): Call<List<Product>>

    @GET("api/v1/products.json")
    fun fetchFromType(
        @Query("product_type") product_type: String
    ): Call<List<Product>>
}