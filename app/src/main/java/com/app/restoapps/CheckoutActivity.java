package com.app.restoapps;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.app.restoapps.fragment.DetailOrderFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckoutActivity extends AppCompatActivity implements DetailOrderFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        initializeFragment();

        ButterKnife.bind(this);
    }

    private void initializeFragment(){
        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.fDetail, DetailOrderFragment.newInstance("",""))
            .commit();
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
        }
        return true;
    }

    @OnClick(R.id.btnPaid)
    public void btnPaidClicked(){
        setResult(MainActivity.CHECKOUT_PAGE_PAGE_RESPONSE);
        finish();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
