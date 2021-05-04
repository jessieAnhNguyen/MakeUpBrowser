package com.anguy39.makeupbrowser.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.anguy39.makeupbrowser.R
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.anguy39.makeupbrowser.MainActivity
import com.anguy39.makeupbrowser.MainActivity.Companion.CATEGORY_CHOICE
import com.anguy39.makeupbrowser.MainActivity.Companion.CONFIRM_RATING
import com.anguy39.makeupbrowser.MainActivity.Companion.USERNAME
import com.anguy39.makeupbrowser.main.ProductViewModel

private const val TAG = "SettingsFragment"
class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }
    private val sharedViewModel: ProductViewModel by activityViewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setHasOptionsMenu(true)
        setPreferencesFromResource(R.xml.settings_preference, rootKey)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Log.d(TAG, "change here!")
        when (key) {
            CATEGORY_CHOICE -> {
                setCategory()
            }
            USERNAME -> {
                sharedViewModel.updateUser(prefs.getString(USERNAME, "User").toString())
            }
        }
    }

    private fun setCategory() {

        when (prefs.getString(CATEGORY_CHOICE, "0")?.toInt()) {
            0 -> sharedViewModel.updateCategory("brand")
            1 -> sharedViewModel.updateCategory("type")
            else -> sharedViewModel.updateCategory("brand")
        }
        Log.d(TAG, "current category is ${sharedViewModel.currCategory}")

    }


    override fun onDestroyView() {
        super.onDestroyView()
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }
}
