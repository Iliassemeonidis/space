package com.example.spacetrucking.ui.transfer

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.spacetrucking.R
import com.example.spacetrucking.model.media.data.ImageData
import com.example.spacetrucking.model.media.data.ServerResponseMedia
import com.example.spacetrucking.model.transfer.data.TransferData
import kotlinx.android.synthetic.main.fragment_media_container.*
import kotlinx.android.synthetic.main.fragment_media_container.image_media
import kotlinx.android.synthetic.main.fragment_media_container.text_description
import kotlinx.android.synthetic.main.fragment_transfer_container.*
import org.jsoup.Jsoup

class TransferContainerFragment : Fragment(R.layout.fragment_transfer_container) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(TRANSFER_KEY) }?.apply {
            val dataArg = getSerializable(TRANSFER_KEY) as TransferData

            val uri = dataArg.image
            val dis = Jsoup.parse(dataArg.description).text()
            val title = Jsoup.parse(dataArg.title).text()

            if (uri.isNotEmpty()) {
                image_transfer.load(uri) {
                    viewLifecycleOwner
                    error(R.drawable.ic_load_error_vector)
                }
            }
            if (!dis.isNullOrEmpty()) {
                println(dis)
                text_description.text = dis
            }
            if (title.isNotEmpty()) {
                text_title_transfer.text = title
            }
        }
    }

    companion object {
        const val TRANSFER_KEY = "TRANSFER_KEY"
    }
}