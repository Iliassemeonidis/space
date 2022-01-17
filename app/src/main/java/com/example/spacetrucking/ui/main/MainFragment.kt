package com.example.spacetrucking.ui.main

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import coil.request.LoadRequestBuilder
import com.example.spacetrucking.R
import com.example.spacetrucking.databinding.MainFragmentStartBinding
import com.example.spacetrucking.model.main.data.PODServerResponseData
import com.example.spacetrucking.model.main.data.PictureOfTheDayData
import com.example.spacetrucking.ui.mars.MarsFragment
import com.example.spacetrucking.ui.media.MediaFragment
import com.example.spacetrucking.ui.transfer.TechTransferFragment
import kotlinx.android.synthetic.main.explanation_text_deskription.*
import kotlinx.android.synthetic.main.main_fragment_end.*


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    private var isExpanded = false
    private var mainFragmentStartBinding: MainFragmentStartBinding? = null
    private val binding get() = mainFragmentStartBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentStartBinding = MainFragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }

        increaseImage()
        initInoutLayoutWiki()

        initBottomNavigation()
    }

    private fun initBottomNavigation() {

        arguments?.takeIf { it.containsKey(FLAG) }?.apply {
     bottom_navigation[0].isFocusable = getBoolean(FLAG)


            val view: View = bottom_navigation.findViewById(R.id.item_info_main)
            view.performClick()
         //getBoolean(FLAG)

        }



        bottom_navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_mars -> {
                    openNewFragment(MarsFragment())
                    true
                }
                R.id.item_media -> {
                    openNewFragment(MediaFragment())
                    true
                }
                R.id.item_info_main -> {
                    openNewFragment(MainFragment())
                    true
                }

                R.id.item_transfer -> {
                    openNewFragment(TechTransferFragment())
                    true
                }

                else -> {
                    Toast.makeText(requireContext(), "Item", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

    //TODO Подумать как решить проблему с добавление уже имеющихся фрагментов
    private fun openNewFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }


    private fun increaseImage() {
        picture_of_the_day_view.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                main,
                TransitionSet().addTransition(ChangeBounds()).addTransition(ChangeImageTransform())
            )
            val params: ViewGroup.LayoutParams = picture_of_the_day_view.layoutParams
            params.height =
                if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            picture_of_the_day_view.layoutParams = params
            picture_of_the_day_view.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }
    }


    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url

                when {
                    url.isNullOrEmpty() -> {
                        Toast.makeText(requireContext(), "Empty data", Toast.LENGTH_SHORT).show()
                        picture_of_the_day_view.load(R.drawable.ic_load_error_vector)
                    }
                    else -> {
                        chekResponseUrl(url)
                        getTextFromResponsesData(serverResponseData)
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
                //TODO Отобразите загрузку //showLoading()
            }
            is PictureOfTheDayData.Error -> {
                Toast.makeText(requireContext(), data.error.message, Toast.LENGTH_SHORT).show()
                picture_of_the_day_view.load(R.drawable.ic_load_error_vector)
            }
        }
    }

    private fun getTextFromResponsesData(serverResponseData: PODServerResponseData) {
        serverResponseData.explanation?.let {
            text_view_description.text = it
        }
        serverResponseData.title.let {
            text_view_title.typeface =
                Typeface.createFromAsset(requireActivity().assets, "fonts/AbyssalHorrors1.ttf")

            text_view_title.text = it
        }
    }

    private fun chekResponseUrl(url: String) {
        val youtube = "https://www.youtube.com/"
        if (url.contains(youtube)) {

            convertVideoInImage(url)
        } else {
            picture_of_the_day_view.load(url) {
                getPlaceHolder()
            }
        }
    }

    private fun convertVideoInImage(url: String) {
        val stringPrefix = "https://img.youtube.com/vi/"
        val stringPostfix = "/0.jpg"

        val video = stringPrefix + url.substringAfterLast("/")
            .substringBefore("?") + stringPostfix
        picture_of_the_day_view.load(video) {
            getPlaceHolder()
        }
    }

    private fun LoadRequestBuilder.getPlaceHolder() {
        viewLifecycleOwner
        error(R.drawable.ic_load_error_vector)
        placeholder(R.drawable.ic_no_photo_vector)
    }


    private fun initInoutLayoutWiki() {
        input_layout_wiki.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
    }


    companion object {

        const val FLAG = "FLAG"

    }


}