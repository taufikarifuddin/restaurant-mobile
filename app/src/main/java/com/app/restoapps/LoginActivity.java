package com.app.restoapps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    public static int REGISTER_RESULT_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLLogin)
    public void onLoginClick(){
        gotoMainPage();
    }

    @OnClick(R.id.btnLRegister)
    public void onRegisterClick(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,REGISTER_RESULT_CODE);
    }

    private void gotoMainPage(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == REGISTER_RESULT_CODE && resultCode == REGISTER_RESULT_CODE ) {
            boolean result = data.getBooleanExtra("response", false);
            if (result) {
                gotoMainPage();
            } else {
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
