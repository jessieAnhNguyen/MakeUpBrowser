package com.anguy39.makeupbrowser.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anguy39.makeupbrowser.MakeUpApp.Companion.DEFAULT_API_URL
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "ProductViewModel"
class ProductViewModel(app: Application) : AndroidViewModel(app) {
    private var _brandList = MutableLiveData<List<String>>(listOf("Dior", "Maybelline", "Clinique", "Colourpop", "Covergirl", "Dr. Hauschka",
        "Glossier", "L'oreal", "lotus cosmetics usa", "Milani", "nyx", "Revlon"))
    var brandList: LiveData<List<String>> = _brandList
    var currBrand: String = ""

    private var _productList = MutableLiveData<List<Product>>()
    var productList: LiveData<List<Product>> = _productList

    fun fetchProductList(brand: String) {
        Log.d(TAG, "start fetching ...")
        val productListRequest: Call<List<Product>> = productApi.fetchProduct(brand)
        Log.d(TAG, "Finish fetching")
        productListRequest.enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                Log.d(TAG, "go into onResponse")
                _productList.value = emptyList()
                response.body()?.let {
                    _productList.value = it
                    for (i in _productList.value!!.indices) {
                        _productList.value!!.get(i).name.replace("\\s".toRegex(), "")
                        Log.d(TAG, "name: ${_productList.value!!.get(i).name}")
                    }
//                    Log.d(TAG, "product list is " + (_productList.value?.get(0)!!.name))
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d(TAG, "Failed to get response.")
                Log.d(TAG, t.toString())
            }
        })
    }

    companion object {
        val productApi: ProductAPI by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(DEFAULT_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return@lazy retrofit.create(ProductAPI::class.java)
        }
    }
}