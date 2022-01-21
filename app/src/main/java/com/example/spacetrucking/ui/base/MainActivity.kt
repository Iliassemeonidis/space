package com.example.spacetrucking.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacetrucking.R
import com.example.spacetrucking.ui.container.ContainerFragment

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openMainFragment(savedInstanceState)
    }

    private fun openMainFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContainerFragment())
                .commitAllowingStateLoss()
        }
    }
}