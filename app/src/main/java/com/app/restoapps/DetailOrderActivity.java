package com.app.restoapps;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.app.restoapps.fragment.DetailOrderFragment;

public class DetailOrderActivity extends AppCompatActivity
        implements DetailOrderFragment.OnFragmentInteractionListener{

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        initializeFragment();

        id = getIntent().getIntExtra("orderId",0);


    }

    private void initializeFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fDetailOrder, DetailOrderFragment.newInstance("", ""))
                .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
