package com.anguy39.makeupbrowser.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.ListFragmentBinding
import com.anguy39.makeupbrowser.databinding.RecyclerItemBinding

class ListFragment : Fragment() {
    private val sharedViewModel: ProductViewModel by activityViewModels()

    private var binding: ListFragmentBinding? = null
    private lateinit var brandAdapter: BrandAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val listFragmentBinding = ListFragmentBinding.inflate(inflater, container, false)
        binding = listFragmentBinding

        brandAdapter = BrandAdapter()

        binding?.apply {
            brandRecycleView.run {
                layoutManager = LinearLayoutManager(context)
                adapter = brandAdapter
            }
        }

        return listFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.brandList.observe(viewLifecycleOwner, {
            brandAdapter.updateBrandsList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    inner class BrandAdapter: RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

        private var brandList: List<String> = emptyList()

        inner class BrandViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
            private lateinit var brand: String

            init {
                binding.brandTextView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                sharedViewModel.fetchProductList(brand)
                sharedViewModel.updateBrand(brand)
                binding.root.findNavController().navigate(R.id.action_listFragment_to_resultFragment)

            }

            fun bind(brand: String) {
                this.brand = brand
                binding.brandTextView.text = brand
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
            val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BrandViewHolder(view)
        }

        override fun getItemCount() = brandList.size

        override fun onBindViewHolder(holder: BrandViewHolder, position: Int) = holder.bind(brandList[position])

        fun updateBrandsList(brandList: List<String>) {
            this.brandList = brandList
            notifyDataSetChanged()
        }
    }


}