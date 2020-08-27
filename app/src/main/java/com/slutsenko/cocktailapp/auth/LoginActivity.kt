package com.slutsenko.cocktailapp.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityLoginBinding
import com.slutsenko.cocktailapp.presentation.ui.activity.MainActivity
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.ui.dialog.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.reflect.KClass

const val EXTRA_KEY_LOGIN = "EXTRA_KEY_LOGIN"
const val EXTRA_KEY_PASSWORD = "EXTRA_KEY_PASSWORD"

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {
    var isScreenLogin = true

    override val viewModelClass: KClass<LoginViewModel>
        get() = LoginViewModel::class

    override fun myView(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.loginInputLiveData.value = savedInstanceState?.getString(EXTRA_KEY_LOGIN)
//        viewModel.passwordInputLiveData.value = savedInstanceState?.getString(EXTRA_KEY_PASSWORD)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        outState.putString(EXTRA_KEY_LOGIN, viewModel.loginInputLiveData.value)
//        outState.putString(EXTRA_KEY_PASSWORD, viewModel.passwordInputLiveData.value)
    }

    override fun activityCreated() {

        viewModel.isLoginDataCorrectLiveData.observe(this, Observer {
            if (isScreenLogin) {
                setupLoginButton(it)
            }
        })
        viewModel.isRegisterDataCorrectLiveData.observe(this, Observer {
            if (!isScreenLogin) {
                setupLoginButton(it)
            }
        })

        viewModel.isCorrectLoginWithServerLiveData.observe(this, Observer
        {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                ErrorDialogFragment.newInstance {
                    titleText = getString(R.string.error_login)
                    leftButtonText = getString(R.string.ok)
                    descriptionText = getString(R.string.error_login_desc)
                }.show(supportFragmentManager, ErrorDialogFragment::class.java.simpleName)
            }
        })

        viewModel.isCorrectRegisterWithServerLiveData.observe(this, Observer
        {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                ErrorDialogFragment.newInstance {
                    titleText = getString(R.string.error_login)
                    leftButtonText = getString(R.string.ok)
                    descriptionText = getString(R.string.error_login_desc)
                }.show(supportFragmentManager, ErrorDialogFragment::class.java.simpleName)
            }
        })
    }

    private fun setupLoginButton(it: Boolean) {
        if (it) {
            btn_login.isEnabled = it
            btn_login.setTextColor(Color.WHITE)
        } else {
            btn_login.isEnabled = !it
            btn_login.setTextColor(Color.GRAY)
        }
    }

    override fun configureDataBinding(binding: ActivityLoginBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }

    fun onSwitchScreenClick(v: View?) {
        when (isScreenLogin) {
            true -> showRegisterScreen()
            false -> showLoginScreen()
        }
    }

    private fun showLoginScreen() {
        til_firstName.visibility = View.GONE
        til_lastName.visibility = View.GONE
        til_repeatPassword.visibility = View.GONE
        txt_haveAccount.text = getString(R.string.no_account)
        btn_singIn_singUp.text = getString(R.string.btn_singUp)
        btn_login.text = getString(R.string.btn_login)
        isScreenLogin = true
    }

    private fun showRegisterScreen() {
        til_firstName.visibility = View.VISIBLE
        til_lastName.visibility = View.VISIBLE
        til_repeatPassword.visibility = View.VISIBLE
        txt_haveAccount.text = getString(R.string.have_account)
        btn_singIn_singUp.text = getString(R.string.btn_login)
        btn_login.text = getString(R.string.btn_singUp)
        isScreenLogin = false
    }

    fun onClickLogin(v: View?) {
        when (isScreenLogin) {
            true -> viewModel.login()
            false -> viewModel.register()
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