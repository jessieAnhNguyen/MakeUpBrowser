package com.anguy39.makeupbrowser.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.FragmentProductBinding
import com.squareup.picasso.Picasso


class ProductDialog : DialogFragment() {

    private val sharedViewModel: ProductViewModel by activityViewModels()
    private var binding: FragmentProductBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val productFragmentBinding = FragmentProductBinding.inflate(inflater, container, false)
        binding = productFragmentBinding
        return productFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            productNameTextView.text = sharedViewModel.currProduct.name
            brandNameTextView.text = sharedViewModel.currProduct.brand
            priceTextView.text = sharedViewModel.currProduct.price
            typeTextView.text = sharedViewModel.currProduct.product_type
            loadProductImage(sharedViewModel.currProduct.image_link)
            doneProductButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_productFragment_to_resultFragment)
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT
//        params.height = ViewGroup.LayoutParams.MATCH_PARENT
//        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
//    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadProductImage(request: String) {
        val picasso = Picasso.get()
        picasso.load(request)
                .placeholder(R.drawable.blush)
                .into(binding?.productImageView)
    }


}