package com.example.previosmoduleconstraintview

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity(R.layout.activity_main), LoginListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isTablet = this.resources.getBoolean(R.bool.isTablet)

       // if (!isTablet)
       //     requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        showLoginFragment()


    }

    private fun showLoginFragment() {

        val isFragmentExists = supportFragmentManager.findFragmentById(R.id.container) != null

        if (!isFragmentExists) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .setTransition(TRANSIT_FRAGMENT_FADE)
                .commit()
        }
    }

    override fun onLoginButtonClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment())
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()
    }


}