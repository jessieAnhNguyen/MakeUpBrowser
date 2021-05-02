package com.anguy39.makeupbrowser.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.FragmentResultBinding
import com.anguy39.makeupbrowser.databinding.ListFragmentBinding
import com.anguy39.makeupbrowser.databinding.RecyclerItemBinding

class ResultFragment : Fragment() {
    private val sharedViewModel: ProductViewModel by activityViewModels()
    private var binding: FragmentResultBinding? = null
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val resultFragmentBinding = FragmentResultBinding.inflate(inflater, container, false)
        binding = resultFragmentBinding

        productAdapter = ProductAdapter()

        binding?.apply {
            productRecyclerView.run {
                layoutManager = LinearLayoutManager(context)
                adapter = productAdapter
            }
        }
        return resultFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.productList.observe(viewLifecycleOwner, {
            productAdapter.updateProductsList(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            resultTitleTextView.text = sharedViewModel.currBrand
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    inner class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        private var productList: List<Product> = emptyList()

        inner class ProductViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
            private lateinit var product: Product

            init {
                binding.brandTextView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
//                sharedViewModel.fetchProductList(brand)
//                sharedViewModel.currBrand = brand
                binding.root.findNavController().navigate(R.id.action_resultFragment_to_productFragment)

            }

            fun bind(product: Product) {
                this.product = product
                binding.brandTextView.text = product.name
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProductViewHolder(view)
        }

        override fun getItemCount() = productList.size

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(productList[position])

        fun updateProductsList(productList: List<Product>) {
            this.productList = productList
            notifyDataSetChanged()
        }
    }

}