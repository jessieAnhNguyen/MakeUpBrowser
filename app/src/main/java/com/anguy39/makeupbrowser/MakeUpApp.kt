package com.anguy39.makeupbrowser

import android.app.Application

class MakeUpApp: Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        private lateinit var instance: MakeUpApp
        const val DEFAULT_API_URL = "http://makeup-api.herokuapp.com/"
    }
}