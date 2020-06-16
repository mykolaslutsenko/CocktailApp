package com.slutsenko.cocktailapp.auth

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.slutsenko.cocktailapp.Base
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Base() {
    lateinit var login: String
    lateinit var password: String
    private val myLogin = "mkola"
    private val myPassword = "a23456"
    override fun myView(): Int {
        return R.layout.activity_login
    }

    override fun activityCreated() {
        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                invalidate()
            }
        }
        et_login.addTextChangedListener(textWatcher)
        et_password.addTextChangedListener(textWatcher)
    }

    private fun invalidate() {
        login = et_login.text.toString()
        password = et_password.text.toString()
        btn_login.isEnabled = login == myLogin && password == myPassword
    }

    fun isValidLogin(login: String?): Boolean {
        return login?.length!! >= 6
    }


    fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }

    fun onClickBtn(v: View?) {
        if (isValidPassword(password) && isValidLogin(login)) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onStart() {
        invalidate()
        super.onStart()
    }
}