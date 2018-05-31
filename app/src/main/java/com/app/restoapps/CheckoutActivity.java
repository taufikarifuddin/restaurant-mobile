package com.app.restoapps;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
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
        setTitle("");

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
        YesNoModal.build(this, "Confirm Checkout", "Are you sure for checkout ? ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(MainActivity.CHECKOUT_PAGE_PAGE_RESPONSE);
                finish();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private static class YesNoModal{

        public static AlertDialog build(Context ctx,
                                        String title,
                                        String message,
                                        DialogInterface.OnClickListener yesOnClick,
                                        DialogInterface.OnClickListener noOnClick){
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("Ya", yesOnClick);
            builder.setNegativeButton("Tidak",noOnClick);
            return builder.create();
        }

    }
}
