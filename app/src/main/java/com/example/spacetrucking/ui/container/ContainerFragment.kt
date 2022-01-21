package com.example.spacetrucking.ui.container

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spacetrucking.R
import com.example.spacetrucking.ui.SpaceTab
import com.example.spacetrucking.ui.main.MainFragment
import com.example.spacetrucking.ui.mars.MarsFragment
import com.example.spacetrucking.ui.media.MediaFragment
import com.example.spacetrucking.ui.transfer.TechTransferFragment
import kotlinx.android.synthetic.main.main_fragment_end.*

class ContainerFragment : Fragment(R.layout.fragment_main_cantainer) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: SharedViewModel =
            ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.subscribeToLiveData().observe(viewLifecycleOwner) { renderData(it) }

        openMainFragment()
        initBottomNavigation()
    }

    private fun openMainFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commitAllowingStateLoss()
    }

    private fun renderData(spaceTab: SpaceTab) {
        when (spaceTab) {
            SpaceTab.INFO -> bottom_navigation.selectedItemId = R.id.item_info_main
            SpaceTab.MEDIA -> bottom_navigation.selectedItemId = R.id.item_media
            SpaceTab.TRANSFER -> bottom_navigation.selectedItemId = R.id.item_transfer
            SpaceTab.MARS -> bottom_navigation.selectedItemId = R.id.item_mars
        }
    }

    private fun initBottomNavigation() {
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
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}