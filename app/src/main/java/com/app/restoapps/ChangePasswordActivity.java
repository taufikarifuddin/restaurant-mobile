package com.app.restoapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("");

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCPSubmit)
    public void onClick(){
        setResult(MainActivity.CHANGE_PASSWORD_PAGE_SUCCESS_RESPONSE);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                break;
        }
        return true;
    }
}
