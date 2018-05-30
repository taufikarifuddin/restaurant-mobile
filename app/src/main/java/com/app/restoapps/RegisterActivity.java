package com.app.restoapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRRegister)
    public void onClickButtonRegister(){
        setResult(LoginActivity.REGISTER_RESULT_CODE,constructResponse());
        finish();
    }

    private Intent constructResponse(){
        Intent intent = new Intent();
        intent.putExtra("response",true);
        return intent;
    }

}
