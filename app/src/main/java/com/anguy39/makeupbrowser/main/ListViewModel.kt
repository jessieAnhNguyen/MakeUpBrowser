package com.anguy39.makeupbrowser.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel(app: Application) : AndroidViewModel(app) {
    private var _brandList = MutableLiveData<List<String>>(listOf("Dior", "Maybelline", "Clinique", "Colourpop", "Covergirl", "Dr. Hauschka",
        "Glossier", "L'oreal", "lotus cosmetics usa", "Milani", "nyx", "Revlon"))
    var brandList: LiveData<List<String>> = _brandList
}