package com.slutsenko.cocktailapp.aus;


import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.slutsenko.cocktailapp.Base;
import com.slutsenko.cocktailapp.R;
import com.slutsenko.cocktailapp.ui.MainActivity;

public class LoginActivity extends Base {

    private String login = "mykola";
    private String password = "123456";
    EditText log;
    EditText pass;
    Button but;

    @Override
    protected int myView() {
        return R.layout.activity_login;
    }

    @Override
    protected void activityCreated() {
        log = findViewById(R.id.login);
        pass = findViewById(R.id.password);
        but = findViewById(R.id.button);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                invalidate();
            }
        };
    }

    private void invalidate() {
        String l = log.getText().toString();
        String  p = pass.getText().toString();
        but.setEnabled( login == l && password == p);
    }

    public void onClickBut(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
