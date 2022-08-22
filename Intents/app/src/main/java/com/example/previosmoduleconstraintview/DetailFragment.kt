package com.example.previosmoduleconstraintview

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView.text = requireArguments().getString(KEY_TEXT)
    }

    companion object {
        private const val KEY_TEXT = "key_text"




        fun newInstance(text: String): DetailFragment {
            return DetailFragment().withArguments {
                putString(KEY_TEXT, text)
            }
        }
    }
}