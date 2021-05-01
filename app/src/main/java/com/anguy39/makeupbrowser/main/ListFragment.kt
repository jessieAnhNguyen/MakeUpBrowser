package com.anguy39.makeupbrowser.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.anguy39.makeupbrowser.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    private var binding: ListFragmentBinding? = null
    private lateinit var brandAdapter: BrandAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val listFragmentBinding = ListFragmentBinding.inflate(inflater, container, false)
        binding = listFragmentBinding

        brandAdapter = BrandAdapter()

        binding?.apply {
            recyclerView.run {
                layoutManager = LinearLayoutManager(context)
                adapter = brandAdapter
            }
        }

        return listFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.brandList.observe(viewLifecycleOwner, {
            brandAdapter.updateBrandsList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }


}