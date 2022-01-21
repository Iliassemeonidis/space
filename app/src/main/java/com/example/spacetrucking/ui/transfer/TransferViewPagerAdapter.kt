package com.example.spacetrucking.ui.transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spacetrucking.model.transfer.data.TransferData
import com.example.spacetrucking.ui.transfer.TransferContainerFragment.Companion.TRANSFER_KEY

class TransferViewPagerAdapter(
    activity: FragmentActivity, private var listFragment: List<TransferData>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TransferContainerFragment()

        val uri = listFragment[position]

        fragment.arguments = Bundle().apply {
            putSerializable(TRANSFER_KEY, uri)
        }
        return fragment
    }
}