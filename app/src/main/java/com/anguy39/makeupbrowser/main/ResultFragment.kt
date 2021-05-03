package com.anguy39.makeupbrowser.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
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

private const val TAG = "ResultFragment"
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

        binding?.doneResultButton!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_resultFragment_to_listFragment)
        }

        binding?.apply {
            productRecyclerView.run {
                layoutManager = LinearLayoutManager(context)
                adapter = productAdapter
            }
            doneResultButton.setOnClickListener{
                it.findNavController().navigate(R.id.action_resultFragment_to_listFragment)
            }
            resultNameTextView.text = getString(R.string.result_name, sharedViewModel.username)

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
            if (sharedViewModel.currCategory == "brand") resultTitleTextView.text = sharedViewModel.currBrand
            else {
                resultTitleTextView.text = sharedViewModel.currType
            }
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
                binding.itemCardView.setCardBackgroundColor(Color.parseColor("#B1C294"))
            }

            override fun onClick(v: View?) {
                sharedViewModel.updateProduct(product)
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