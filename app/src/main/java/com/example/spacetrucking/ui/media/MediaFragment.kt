package com.example.spacetrucking.ui.media

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.spacetrucking.R
import com.example.spacetrucking.model.media.state.MediaState
import kotlinx.android.synthetic.main.fragment_media.*


class MediaFragment : Fragment(R.layout.fragment_media) {

    private val mediaViewModel: MediaViewModel by lazy {
        ViewModelProviders.of(this).get(MediaViewModel::class.java)
    }
    private lateinit var adapter: MediaViewPagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaViewModel.subscribeToStateChange().observe(viewLifecycleOwner) { renderState(it) }
        mediaViewModel.getData()


    }

    private fun renderState(state: MediaState) {
        when (state) {
            is MediaState.Success -> {
                loading_media.visibility = View.INVISIBLE

                val uri = state.podServerResponseMediaData.collection.items
                adapter = MediaViewPagerAdapter(requireActivity(), uri)
                viewPagerImageSlider.adapter = adapter
            }
            is MediaState.Loading -> {
                loading_media.visibility = View.VISIBLE
            }
            is MediaState.Error -> {
                println(state.error.message)
                Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show()
                // image_media.load(R.drawable.ic_load_error_vector)
            }

        }
    }

}