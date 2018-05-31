package com.app.restoapps;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.app.restoapps.dto.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOrderActivity extends AppCompatActivity {

    @BindView(R.id.tlListOrder)
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        setTitle("");

        ButterKnife.bind(this);

        initializeData(table,constructSampleData());
    }


    private List<Order> constructSampleData(){
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Order order = new Order();
            order.setId(i);
            order.setDate("07-08-2018");
            order.setPriceTotal("Rp.1000,-");
            orders.add(order);
        }       

        return orders;
    }


    private void initializeData(TableLayout table,List<Order> data){
        for( Order order : data ){
            TableRow row = getDefaultRow(order.getId());
            row.addView(getDefaultTextViewColumn(order.getDate()));
            row.addView(getDefaultTextViewColumn(order.getPriceTotal()));
            table.addView(row);
        }
    }

    private TableRow getDefaultRow(final int id){
        int padding = getResources().getDimensionPixelOffset(R.dimen.default_padding_inner_component);
        TableRow row = new TableRow(this);
        row.setPadding(padding,padding,padding,padding);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetailOrder(id);
            }
        });

        return row;
    }

    private void goToDetailOrder(int id){
        Toast.makeText(ListOrderActivity.this, "id : "+id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,DetailOrderActivity.class);
        intent.putExtra("orderId",id);

        startActivity(intent);
    }

    private TextView getDefaultTextViewColumn(final String text){
        float textSize = getResources().getDimensionPixelOffset(R.dimen.default_text_size) /
                getResources().getDisplayMetrics().density;
        TextView tv = new TextView(this);
        tv.setTextSize(textSize);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv.setText(text);

        return tv;
    }
}
