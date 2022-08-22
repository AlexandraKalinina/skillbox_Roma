package com.example.previosmoduleconstraintview

import android.content.pm.ActivityInfo
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainFragment : Fragment(R.layout.fragment_main), TextviewListener {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val isTablet = activity?.resources?.getBoolean(R.bool.isTablet)

        if (isTablet == false)
            showListFragment()
    }

    override fun onTextViewClick(text: String) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerSecond, DetailFragment.newInstance(text))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(TAG)
            .commit()
    }

    private fun showListFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerSecond, ListFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    companion object {
        const val TAG = "FragmentTag"
    }
}