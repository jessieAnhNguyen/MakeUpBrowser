package com.anguy39.makeupbrowser.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.RecyclerItemBinding

private const val TAG = "BrandAdapter"
class BrandAdapter: RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    private var brandList: List<String> = emptyList()

    inner class BrandViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var brand: String

        init {
            binding.brandTextView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            binding.root.findNavController().navigate(R.id.action_listFragment_to_resultFragment)
//                friendToDelete = friend
//                friendtoDeleteTextView.text = "Friend to be deleted is ${friendToDelete.firstName}"
//                Log.d(TAG, "HERE: " + friendtoDeleteTextView.text.toString())
//                friendtoDeleteTextView.visibility = View.VISIBLE
//            }


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