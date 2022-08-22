package com.example.previosmoduleconstraintview

import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {


    private var check: FormState = FormState(valid = false)

    private val loginListener: LoginListener?
        get() = activity?.let { it as? LoginListener }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val warning = warning()


        if (savedInstanceState != null) {
            check = savedInstanceState.getParcelable(KEY_MESSAGE)
                ?: error("Unexpected state")

            if (check.valid) {
                container.addView(warning)
            }
        }

        checkBoxLicense.setOnClickListener {
            entryButton.isEnabled = checkBoxLicense?.isChecked ?: false
        }

        entryButton.setOnClickListener {

            val emailAddress = editTextEmail.text
            val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()


            if (isEmailValid && editTextPassword.text.isNotEmpty()) {
                if (check.valid)
                    container.removeView(warning)
                check = check.valid()
                logIn()

            } else {
                if (!check.valid)
                    container.addView(warning)
                check = check.invalid()
            }
        }

        ANRButton.setOnClickListener {
            Thread.sleep(30000)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_MESSAGE, check)
    }

    private fun logIn() {
        entryButton.isEnabled = false
        checkBoxLicense.isEnabled = false

        val progressBar = ProgressBar(activity).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                this.startToStart = R.id.container
                endToEnd = R.id.container
                topToBottom = R.id.entryButton
            }
        }

        container.addView(progressBar)

        Handler().postDelayed({
            entryButton.isEnabled = true
            checkBoxLicense.isEnabled = true
            container.removeView(progressBar)
            Toast.makeText(activity, "Логин прошел успешно", Toast.LENGTH_SHORT).show()
            onButtonLoginClick()
        }, 2000)

    }

    private fun warning(): View {
        return TextView(activity).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                startToStart = R.id.checkBoxLicense
                endToEnd = R.id.entryButton
                topToBottom = R.id.entryButton
                bottomToTop = R.id.ANRButton
            }
            setText(R.string.warning)
            setTextColor(resources.getColor(R.color.red))
        }
    }

    private fun onButtonLoginClick() {
        loginListener?.onLoginButtonClicked()
    }

    companion object {
        private const val KEY_MESSAGE = "message"

        /*private const val KEY_TEXT = "key_text"

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }*/
    }
}