package com.anguy39.makeupbrowser.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.FragmentWelcomeBinding
import com.anguy39.makeupbrowser.main.ProductViewModel

class WelcomeFragment : Fragment() {
    private var binding: FragmentWelcomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val welcomeFragmentBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding = welcomeFragmentBinding
        return welcomeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            welcomeTextView.text = getString(R.string.welcome)
            goButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_welcomeFragment_to_listFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}