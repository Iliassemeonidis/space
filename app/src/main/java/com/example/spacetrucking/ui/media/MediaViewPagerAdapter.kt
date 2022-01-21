package com.example.spacetrucking.ui.media

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spacetrucking.model.media.data.ImageData
import com.example.spacetrucking.model.media.data.ServerResponseMedia
import com.example.spacetrucking.ui.media.MediaContainerFragment.Companion.IMAGE_KEY

class MediaViewPagerAdapter(
    activity: FragmentActivity, private var listFragment: List<ServerResponseMedia>
) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = MediaContainerFragment()

        checkData(position, fragment)

        return fragment
    }

    private fun checkData(
        position: Int,
        fragment: MediaContainerFragment
    ) {
        val uri = listFragment[position]
            //.links?.get(0)?.href

        //TODO  create fun check uri
       // if (!uri.isNullOrBlank()) {
            fragment.arguments = Bundle().apply {
                putSerializable(IMAGE_KEY, uri)
            }
        //}
    }
}