package com.anguy39.makeupbrowser.rating

import android.os.Bundle
import android.view.*
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anguy39.makeupbrowser.BuildConfig
import com.anguy39.makeupbrowser.R
import com.anguy39.makeupbrowser.databinding.FragmentInfoBinding
import com.anguy39.makeupbrowser.databinding.FragmentRatingBinding

class RatingFragment : Fragment() {
    private var binding: FragmentRatingBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val ratingFragmentBinding = FragmentRatingBinding.inflate(inflater, container, false)
        binding = ratingFragmentBinding
        return ratingFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            submitRatingButton.setOnClickListener {
                updateRatingAlert(ratingBar.rating)
            }
        }
    }

    fun updateRatingAlert(rating: Float) {
        val ratingRounded = String.format("%.1f", rating)
        val msg = resources.getString(R.string.rating_alert, ratingRounded)
        val builder = android.app.AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.attention)
            setMessage(msg)
            setPositiveButton(R.string.yes) { _, _ ->
                findNavController().navigate(R.id.action_ratingFragment_to_welcomeFragment)
                confirmRatingAlert()
            }
            setNegativeButton(R.string.cancel) { _, _ ->
            }
            show()
        }
    }

    fun confirmRatingAlert() {
        val msg = resources.getString(R.string.confirm_rating)
        val builder = android.app.AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.confirm)
            setMessage(msg)
            setPositiveButton(R.string.ok) { _, _ ->

            }
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}