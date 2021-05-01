package com.anguy39.makeupbrowser.info

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.anguy39.makeupbrowser.BuildConfig
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val infoFragmentBinding = FragmentInfoBinding.inflate(inflater, container, false)
        binding = infoFragmentBinding
        return infoFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            appNameTextView.text = resources.getString(R.string.app_name)
            appIDTextView.text = BuildConfig.APPLICATION_ID
            versionNumberTextView.text = BuildConfig.VERSION_NAME
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }


}