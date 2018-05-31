package com.app.restoapps;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.restoapps.fragment.DetailOrderFragment;

public class DetailOrderActivity extends AppCompatActivity {

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
}
