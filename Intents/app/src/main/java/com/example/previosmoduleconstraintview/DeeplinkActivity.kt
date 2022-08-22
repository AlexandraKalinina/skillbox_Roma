package com.example.previosmoduleconstraintview

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_deeplink.*

class DeeplinkActivity : AppCompatActivity(R.layout.activity_deeplink) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntentData()
    }

    private fun handleIntentData() {
        intent.data?.let { name ->
            deeplinkTextview.text = name.toString()
        }
    }

}