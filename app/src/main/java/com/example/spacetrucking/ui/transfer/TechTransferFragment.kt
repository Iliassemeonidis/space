package com.example.spacetrucking.ui.transfer

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.spacetrucking.R
import com.example.spacetrucking.model.transfer.data.TransferData
import com.example.spacetrucking.model.transfer.state.TransferState
import com.example.spacetrucking.ui.SpaceTab
import com.example.spacetrucking.ui.container.SharedViewModel
import kotlinx.android.synthetic.main.fragment_tech_transfer.*

class TechTransferFragment : Fragment(R.layout.fragment_tech_transfer) {

    private val viewModel: TechTransferViewModel by lazy {
        ViewModelProviders.of(this).get(TechTransferViewModel::class.java)
    }
    private lateinit var adapter: TransferViewPagerAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.subscribeToStateChange().observe(viewLifecycleOwner) { renderToObserve(it) }
        viewModel.getData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // TODO повторяющийся код
                    requireActivity().supportFragmentManager.popBackStack()
                    sharedViewModel.setSpaceTab(SpaceTab.INFO)
                }
            }
        )
    }

    private fun renderToObserve(state: TransferState) {
        when (state) {
            is TransferState.Success -> {
                // Хард код для примера
                val title = state.serverResponseMarsData.results[0][2]
                val image = state.serverResponseMarsData.results[0][10]
                val description = state.serverResponseMarsData.results[0][3]
                loading_transfer.visibility = View.INVISIBLE
                checkData(image, description,title)
            }
            is TransferState.Loading -> { loading_transfer.visibility = View.VISIBLE }
            is TransferState.Error -> { Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show() }
        }
    }

    private fun checkData(image: String, description: String,title:String) {
        if (image.isNotBlank() && description.isNotBlank()) {
            addValueInViewPager(image, description,title)
        } else {
            initViewPager(listOf())
        }
    }

    private fun addValueInViewPager(image: String, description: String,title:String) {
        val transfer = TransferData(title, description,image)
        loading_transfer.visibility = View.INVISIBLE
        initViewPager(listOf(transfer))
    }

    private fun initViewPager(transfer: List<TransferData>) {
        adapter = TransferViewPagerAdapter(requireActivity(), transfer)
        view_pager.adapter = adapter
    }
}