package com.example.spacetrucking.ui.media

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.spacetrucking.R
import com.example.spacetrucking.model.media.data.ImageData
import com.example.spacetrucking.model.media.data.ServerResponseMedia
import kotlinx.android.synthetic.main.fragment_media_container.*

class MediaContainerFragment : Fragment(R.layout.fragment_media_container) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(IMAGE_KEY) }?.apply {
            val dataArg = getSerializable(IMAGE_KEY) as ServerResponseMedia

            val uri = dataArg.links
            val dis = dataArg.data

            if (!uri.isNullOrEmpty()) {
                image_media.load(uri[0].href.toString()) {
                    viewLifecycleOwner
                    error(R.drawable.ic_load_error_vector)
                }
            }
            if (!dis.isNullOrEmpty()){
                println(dis[0].description.toString())
                text_description.text = dis[0].description.toString()
            }

        }
    }

    companion object {
        const val IMAGE_KEY = "IMAGE_KEY"
    }
}