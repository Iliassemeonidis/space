package com.example.spacetrucking.ui.mars

import android.content.Context
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.spacetrucking.R
import com.example.spacetrucking.model.mars.state.PictureOfTheMars
import com.example.spacetrucking.model.mars.data.PhotosData
import com.example.spacetrucking.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_fragment_end.*
import kotlinx.android.synthetic.main.mars_fragment_start.*

class MarsFragment : Fragment(R.layout.mars_fragment_start) {

    private val viewModel: MarsViewModel by lazy {
        ViewModelProviders.of(this).get(MarsViewModel::class.java)
    }

    private var show = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) { renderState(it) }

        backgroundImage.setOnClickListener { if (show) hideComponents() else showComponents() }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = MainFragment()

        fragment.arguments = bundleOf().apply {
            putBoolean(MainFragment.FLAG, true)
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commitAllowingStateLoss()

                }

            }
        )
    }

    private fun renderState(state: PictureOfTheMars) {
        when (state) {
            is PictureOfTheMars.Success -> {
                val data = state.serverResponseMarsData.photos
                loading.visibility = View.INVISIBLE
                takeComponentsFromData(data)
            }
            is PictureOfTheMars.Loading -> {
               loading.visibility = View.VISIBLE
            }
            is PictureOfTheMars.Error -> {
                Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show()
                backgroundImage.load(R.drawable.ic_load_error_vector)
            }
        }
    }

    private fun takeComponentsFromData(data: List<PhotosData>?) {
        when (data.isNullOrEmpty()) {
            true -> {
                Toast.makeText(requireContext(), "Empty data", Toast.LENGTH_SHORT).show()
                backgroundImage.load(R.drawable.ic_load_error_vector)
            }

            else -> {
                val uri ="https"+data[0].imgSrc?.substringAfter("p")
                backgroundImage.load(uri) {
                    viewLifecycleOwner
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                }
                date.text = data[0].earthDate

                title.text = data[0].camera?.fullName ?: "None name"

            }
        }

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