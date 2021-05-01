package com.anguy39.makeupbrowser.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.RecyclerItemBinding

class BrandAdapter: RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    private var brandList: List<String> = emptyList()

    inner class BrandViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var brand: String
        private val brandItemTextView: TextView = itemView.findViewById(R.id.brand_textView)

        init {
//            friendItemTextView.setOnClickListener(this)
//            friendtoDeleteTextView.text = ""
        }

        override fun onClick(v: View?) {
//            binding.apply {
//                friendToDelete = friend
//                friendtoDeleteTextView.text = "Friend to be deleted is ${friendToDelete.firstName}"
//                Log.d(TAG, "HERE: " + friendtoDeleteTextView.text.toString())
//                friendtoDeleteTextView.visibility = View.VISIBLE
//            }


        }

        fun bind(brand: String) {
            this.brand = brand
            brandItemTextView.text = brand
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