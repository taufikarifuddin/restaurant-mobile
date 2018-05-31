package com.app.restoapps;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.app.restoapps.adapter.ExpandableListAdapter;
import com.app.restoapps.adapter.listener.OnClickBtnQty;
import com.app.restoapps.dto.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnClickBtnQty{

    @BindView(R.id.elvListCategory)
    ExpandableListView elvListCategory;

    private ExpandableListAdapter adapter;

    public static final int CHECKOUT_PAGE_PAGE_REQUEST = 300;
    public static final int CHECKOUT_PAGE_PAGE_RESPONSE = 310;

    public static final int CHANGE_PASSWORD_PAGE_REQUEST = 500;
    public static final int CHANGE_PASSWORD_PAGE_SUCCESS_RESPONSE = 501;
    public static final int DETAIL_ORDER_PAGE_FAILED_RESPONSE = 502;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        adapter = new ExpandableListAdapter(this.getBaseContext(),constructSampleData(),this);

        elvListCategory.setAdapter(adapter);
    }

    private Map<String,List<Item>> constructSampleData(){
        Map<String,List<Item>> data = new TreeMap<>();

        for (int i = 0; i < 5; i++) {
            List<Item> items= new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Item item = new Item();
                item.setId(i+j);
                item.setName("data : "+i+"-"+j);
                item.setPrice(1000);
                items.add(item);
            }
            data.put("data-"+i,items);
        }
        Log.d("debug data ",data.toString());
        return data;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.ilogout :
                YesNoModal.build(this, "Logout", "Are you sure for logout ? ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.iChangePassword :
                startActivityForResult(new Intent(this,ChangePasswordActivity.class),
                        CHANGE_PASSWORD_PAGE_REQUEST);
                break;
        }

        return true;
    }

    @Override
    public void onClick(int parent,int child,int total) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode == CHECKOUT_PAGE_PAGE_RESPONSE
                && requestCode == CHECKOUT_PAGE_PAGE_REQUEST ){

        }
    }

    @OnClick(R.id.btnCheckout)
    public void onClick(){
        Intent intent = new Intent(this,CheckoutActivity.class);
        startActivityForResult(intent,CHECKOUT_PAGE_PAGE_REQUEST);
    }

    @OnClick(R.id.ivHistory)
    public void onHistoryClick(){
        Intent intent = new Intent(this,ListOrderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account,menu);
        return true;
    }

    private static class YesNoModal{

        public static android.support.v7.app.AlertDialog build(Context ctx,
                                                               String title,
                                                               String message,
                                                               DialogInterface.OnClickListener yesOnClick,
                                                               DialogInterface.OnClickListener noOnClick){
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ctx);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("Ya", yesOnClick);
            builder.setNegativeButton("Tidak",noOnClick);
            return builder.create();
        }

    }
}
