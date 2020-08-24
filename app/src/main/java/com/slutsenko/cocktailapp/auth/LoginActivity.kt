package com.slutsenko.cocktailapp.auth

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityLoginBinding
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.ui.dialog.ActionSingleDialogButton
import com.slutsenko.cocktailapp.presentation.ui.dialog.DialogButton
import com.slutsenko.cocktailapp.presentation.ui.dialog.DialogType
import com.slutsenko.cocktailapp.presentation.ui.dialog.ErrorDialogType
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.reflect.KClass

const val EXTRA_KEY_LOGIN = "EXTRA_KEY_LOGIN"
const val EXTRA_KEY_PASSWORD = "EXTRA_KEY_PASSWORD"

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModelClass: KClass<LoginViewModel>
        get() = LoginViewModel::class
    //override val viewModel: LoginViewModel by viewModels()

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
            if (it) {
                btn_login.isEnabled = it
                btn_login.setTextColor(Color.WHITE)
            } else {
                btn_login.isEnabled = !it
                btn_login.setTextColor(Color.GRAY)
            }
        })

        viewModel.isLoggedLiveData.observe(this, Observer { })
    }

    override fun configureDataBinding(binding: ActivityLoginBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }

    var login = true
    fun onSingInClick(v: View?) {
        if (login) {
            til_name.visibility = View.VISIBLE
            til_lastName.visibility = View.VISIBLE
            til_repeatPassword.visibility = View.VISIBLE
            txt_haveAccount.text = getString(R.string.have_account)
            btn_singIn_singUp.text = getString(R.string.btn_login)
            btn_login.text = getString(R.string.btn_singUp)
            login = false
        } else {
            til_name.visibility = View.GONE
            til_lastName.visibility = View.GONE
            til_repeatPassword.visibility = View.GONE
            txt_haveAccount.text = getString(R.string.no_account)
            login = true
            btn_singIn_singUp.text = getString(R.string.btn_singUp)
            btn_login.text = getString(R.string.btn_login)
        }
    }

    fun onClickLogin(v: View?) {
        //viewModel.invalidate()
        viewModel.register()
        //startActivity(Intent(this, MainActivity::class.java))
        //finish()
//        } else {
//            ErrorDialogFragment.newInstance {
//                titleText = getString(R.string.error_login)
//                leftButtonText = getString(R.string.ok)
//                descriptionText = getString(R.string.error_login_desc)
//            }.show(supportFragmentManager, ErrorDialogFragment::class.java.simpleName)
//        }
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