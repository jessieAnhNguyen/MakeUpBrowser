package com.anguy39.makeupbrowser.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anguy39.makeupbrowser.MainActivity.Companion.CATEGORY_CHOICE
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.ListFragmentBinding
import com.anguy39.makeupbrowser.databinding.RecyclerItemBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.CropSquareTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation

private const val TAG = "ListFragment"
class ListFragment : Fragment() {
    private val sharedViewModel: ProductViewModel by activityViewModels()

    private var binding: ListFragmentBinding? = null
    private lateinit var brandAdapter: BrandAdapter
    private lateinit var typeAdapter: TypeAdapter

    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val listFragmentBinding = ListFragmentBinding.inflate(inflater, container, false)
        binding = listFragmentBinding
//        prefs.registerOnSharedPreferenceChangeListener(this)

        Log.d(TAG, "current category is ${sharedViewModel.currCategory}")
        brandAdapter = BrandAdapter()
        typeAdapter = TypeAdapter()

        binding?.apply {
            brandRecycleView.run {
                layoutManager = LinearLayoutManager(context)
                if (sharedViewModel.currCategory == "brand") adapter = brandAdapter
                else adapter = typeAdapter
            }
            doneListButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_listFragment_to_welcomeFragment)
            }
        }

        if (sharedViewModel.currCategory == "brand") binding?.listTextView!!.text = getString(R.string.list_of_brand)
        else binding?.listTextView!!.text = getString(R.string.list_of_type)

        return listFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (sharedViewModel.currCategory == "brand") {
            sharedViewModel.brandList.observe(viewLifecycleOwner, {
                brandAdapter.updateBrandsList(it)
            })
        }
        else {
            sharedViewModel.typeList.observe(viewLifecycleOwner, {
                typeAdapter.updateTypesList(it)
            })
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
//        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }
    
    inner class BrandAdapter: RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

        private var brandList: List<String> = emptyList()

        inner class BrandViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
            private lateinit var brand: String

            init {
                binding.brandTextView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                sharedViewModel.fetchFromBrand(brand)
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

    inner class TypeAdapter: RecyclerView.Adapter<TypeAdapter.TypeViewHolder>() {

        private var typeList: List<String> = emptyList()

        inner class TypeViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
            private lateinit var type: String

            init {
                binding.brandTextView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                sharedViewModel.fetchFromType(type)
                sharedViewModel.updateType(type)
                binding.root.findNavController().navigate(R.id.action_listFragment_to_resultFragment)

            }

            fun bind(type: String) {
                this.type = type
                binding.brandTextView.text = type
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
            val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TypeViewHolder(view)
        }

        override fun getItemCount() = typeList.size

        override fun onBindViewHolder(holder: TypeViewHolder, position: Int) = holder.bind(typeList[position])

        fun updateTypesList(typeList: List<String>) {
            this.typeList = typeList
            notifyDataSetChanged()
        }
    }



}