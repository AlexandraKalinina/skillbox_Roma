package com.example.previosmoduleconstraintview

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {

    private val fragment: Fragment? = parentFragment
    private val parentActivity = activity

    private val parent: TextviewListener?
    get() = parentFragment?.let { it as? TextviewListener }
       ?: parentActivity?.let { it as? TextviewListener }

       /* private val textViewListener: TextviewListener?
            get() = parent?.let { it as? TextviewListener }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.let {
            it as ViewGroup
        }.children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    Toast.makeText(activity, "${button.text}", Toast.LENGTH_SHORT).show()
                    onTextviewClick(button.text.toString())
                }
            }
    }

    private fun onTextviewClick(text: String) {
        parent?.onTextViewClick(text)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("List", "List detached")
    }
    companion object {
        private const val KEY_TEXT = "key_text"


    }
}