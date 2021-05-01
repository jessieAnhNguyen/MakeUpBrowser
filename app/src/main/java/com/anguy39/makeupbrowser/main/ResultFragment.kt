package com.anguy39.makeupbrowser.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.FragmentResultBinding
import com.anguy39.makeupbrowser.databinding.ListFragmentBinding

class ResultFragment : Fragment() {
    private var binding: FragmentResultBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val resultFragmentBinding = FragmentResultBinding.inflate(inflater, container, false)
        binding = resultFragmentBinding

        binding?.apply {
            product1Button.setOnClickListener {
                it.findNavController().navigate(R.id.action_resultFragment_to_productFragment)
            }
            product2Button.setOnClickListener {
                it.findNavController().navigate(R.id.action_resultFragment_to_productFragment)
            }
            product3Button.setOnClickListener {
                it.findNavController().navigate(R.id.action_resultFragment_to_productFragment)
            }
            product4Button.setOnClickListener {
                it.findNavController().navigate(R.id.action_resultFragment_to_productFragment)
            }
        }

        return resultFragmentBinding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}