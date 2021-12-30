package com.example.spacetrucking.ui.fragmentmars

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.spacetrucking.R
import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData
import com.example.spacetrucking.model.repository.marspicture.PictureOfTheMars
import kotlinx.android.synthetic.main.explanation_text_deskription.*
import kotlinx.android.synthetic.main.main_fragment_end.*
import kotlinx.android.synthetic.main.mars_fragment_start.*

class MarsFragment : Fragment(R.layout.mars_fragment_start) {

    private val viewModel: MarsViewModel by lazy {
        ViewModelProviders.of(this).get(MarsViewModel::class.java)
    }

    private var show = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner){renderData(it)}

        backgroundImage.setOnClickListener { if (show) hideComponents() else showComponents() }

    }


    private fun renderData(data: List<PictureOfTheMars>) {
        when (data[0]) {
            is PictureOfTheMars.Success -> {
                val photos = data

                when {
                    photos.isNullOrEmpty() -> {
                        Toast.makeText(requireContext(), "Empty data", Toast.LENGTH_SHORT).show()
                        picture_of_the_day_view.load(R.drawable.ic_load_error_vector)
                    }
                    else -> {
                        //getTextFromResponsesData(serverResponseData)
                    }
                }
            }
            is PictureOfTheMars.Loading -> {
                //TODO Отобразите загрузку //showLoading()
            }
            is PictureOfTheMars.Error -> {
//                Toast.makeText(requireContext(), data.error.message, Toast.LENGTH_SHORT).show()
                picture_of_the_day_view.load(R.drawable.ic_load_error_vector)
            }
        }
    }

    private fun getTextFromResponsesData(serverResponseData: PODServerResponseMarsData) {

    }

    private fun showComponents() {
        show = true

        val constraintSet = ConstraintSet()
        constraintSet.clone(requireActivity(), R.layout.mars_fragment_end)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint_container, transition)
        constraintSet.applyTo(constraint_container)
    }

    private fun hideComponents() {
        show = false

        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.mars_fragment_start)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint_container, transition)
        constraintSet.applyTo(constraint_container)
    }

}