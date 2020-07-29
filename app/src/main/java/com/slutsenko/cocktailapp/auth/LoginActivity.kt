package com.slutsenko.cocktailapp.auth

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.base.BaseActivity
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseActivity
import com.slutsenko.cocktailapp.databinding.ActivityLoginBinding
import com.slutsenko.cocktailapp.ui.activity.MainActivity
import com.slutsenko.cocktailapp.ui.dialog.*
import kotlinx.android.synthetic.main.activity_login.*

const val EXTRA_KEY_LOGIN = "EXTRA_KEY_LOGIN"
const val EXTRA_KEY_PASSWORD = "EXTRA_KEY_PASSWORD"

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModel: LoginViewModel by viewModels()

    lateinit var login: String
    lateinit var password: String
    private val myLogin = "mykola"
    private val myPassword = "a23456"
    override fun myView(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loginInputLiveData.value = savedInstanceState?.getString(EXTRA_KEY_LOGIN)

        viewModel.passwordInputLiveData.value = savedInstanceState?.getString(EXTRA_KEY_PASSWORD)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_KEY_LOGIN, viewModel.loginInputLiveData.value)
        outState.putString(EXTRA_KEY_PASSWORD, viewModel.passwordInputLiveData.value)
    }

    override fun activityCreated() {
        viewModel.isLoginDataCorrectLiveData.observe(this, Observer {
            if (it) {
                btn_login.isEnabled = it
                btn_login.setTextColor(Color.WHITE)
            } else {
                btn_login.isEnabled = !it
                btn_login.setTextColor(Color.GRAY)
            }
        })
        viewModel.isLoginDataValidLiveData.observe(this, Observer { })
    }

    override fun configureDataBinding(binding: ActivityLoginBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }

    fun onClickLogin(v: View?) {
        if (viewModel.isLoginDataValidLiveData.value == true) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            ErrorDialogFragment.newInstance {
                titleText = "Error"
                leftButtonText = "Ok"
                descriptionText = "Incorrect login or password"
            }.show(supportFragmentManager, ErrorDialogFragment::class.java.simpleName)
        }
    }

    override fun onDialogFragmentClick(
            dialog: DialogFragment,
            buttonType: DialogButton,
            type: DialogType<DialogButton>,
            data: Any?
    ) {
        super.onDialogFragmentClick(dialog, buttonType, type, data)
        when (type) {
            ErrorDialogType -> {
                when (buttonType) {
                    ActionSingleDialogButton -> {
                        dialog.dismiss()
                    }
                }
            }
        }
    }
}