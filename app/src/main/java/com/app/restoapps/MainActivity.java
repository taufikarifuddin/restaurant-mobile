package com.app.restoapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.app.restoapps.adapter.ExpandableListAdapter;
import com.app.restoapps.adapter.listener.OnClickBtnQty;
import com.app.restoapps.dto.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnClickBtnQty{

    @BindView(R.id.elvListCategory)
    ExpandableListView elvListCategory;

    private ExpandableListAdapter adapter;

    public static int CHECKOUT_PAGE_PAGE_REQUEST = 300;
    public static int CHECKOUT_PAGE_PAGE_RESPONSE = 310;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        adapter = new ExpandableListAdapter(this.getBaseContext(),constructSampleData(),this);

        elvListCategory.setAdapter(adapter);
    }

    private HashMap<String,List<Item>> constructSampleData(){
        HashMap<String,List<Item>> data = new HashMap<>();

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
        return data;
    }

    @Override
    public void onClick(int parent,int child,int total) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode == CHECKOUT_PAGE_PAGE_RESPONSE
                && requestCode == CHECKOUT_PAGE_PAGE_REQUEST ){
            adapter.refresh();

        }
    }

    @OnClick(R.id.btnCheckout)
    public void onClick(){
        Intent intent = new Intent(this,CheckoutActivity.class);
        startActivityForResult(intent,CHECKOUT_PAGE_PAGE_REQUEST);
    }
}
