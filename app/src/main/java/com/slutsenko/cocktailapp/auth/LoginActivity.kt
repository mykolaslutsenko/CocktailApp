package com.slutsenko.cocktailapp.auth

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseActivity
import com.slutsenko.cocktailapp.databinding.ActivityLoginBinding
import com.slutsenko.cocktailapp.ui.activity.MainActivity
import com.slutsenko.cocktailapp.ui.dialog.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModel: LoginViewModel by viewModels()

    override fun myView(): Int {
        return R.layout.activity_login
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