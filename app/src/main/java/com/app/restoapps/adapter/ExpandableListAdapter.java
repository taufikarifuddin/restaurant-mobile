package com.app.restoapps.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.restoapps.R;
import com.app.restoapps.adapter.listener.OnClickBtnQty;
import com.app.restoapps.dto.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * TODO: Add a class header comment!
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,List<Item>> mData;
    private List<String> mHeader;
    OnClickBtnQty mQtyClick;

    public ExpandableListAdapter(Context ctx,Map<String,List<Item>> data,
                                 OnClickBtnQty qty){
        mLayoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mData = data;
        mQtyClick = qty;
        init();
    }

    private void init(){
        mHeader = this.getHeaderFromKeys(mData.keySet());
    }

    private List<String> getHeaderFromKeys(Set<String> keys){
        List<String> listOfKey = new ArrayList<>();

        if( keys.isEmpty() ) return listOfKey;

        for(String key : keys){
            listOfKey.add(key);
        }
        Log.d("list of header : ",listOfKey.toString());

        return listOfKey;
    }

    @Override
    public int getGroupCount() {
        return mHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(mHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mData.get(mHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return ((Item)getChild(groupPosition,childPosition)).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if( convertView == null ){
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_header,null);

            LinearLayout header = (LinearLayout) convertView.findViewById(R.id.llHeaderExpandable);

            if( isBorderEnable(groupPosition) ) {
                header.setBackgroundResource(R.drawable.expandable_round_top);
            }else{
                header.setBackgroundResource(R.drawable.expandable_normal_list  );
            }

            ((TextView) convertView.findViewById(R.id.tvExpandableHeader)).setText(mHeader.get(groupPosition));
        }

        return convertView;
    }

    private boolean isBorderEnable(int position){
        return position == 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if( convertView == null ){
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_list,null);
        }


        final Item item = ((Item)getChild(groupPosition,childPosition));

        Button increaseBtn = (Button) convertView.findViewById(R.id.btnIncrease);
        Button decreaseBtn = (Button) convertView.findViewById(R.id.btnDecrease);

        final FormView fm = new FormView();
        fm.setEdit(((EditText) convertView.findViewById(R.id.etQty)));
        fm.setTextView(((TextView) convertView.findViewById(R.id.tvPrice)));

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrice(item,fm,QtyButtonTrigger.INCREASE);
            }
        });

        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrice(item,fm,QtyButtonTrigger.DECREASE);
            }
        });

        ((TextView) convertView.findViewById(R.id.tvExpandableListItemName)).setText(item.getName());



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private void updatePrice(Item item,FormView fm,QtyButtonTrigger trigger){

        if( fm != null) {

            int currentQty = item.getQty();
            if( trigger.equals(QtyButtonTrigger.INCREASE) ){
                item.setQty( currentQty + 1 );
            }else{
                if( isLimitValue(item.getQty()) ) {
                    item.setQty(currentQty - 1);
                }
            }

            mQtyClick.onClick(0,0,calculateTotal(item));

            updatePriceView(fm,item);
        }
    }

    private int calculateTotal(Item item){
        return item.getPrice() * item.getQty();
    }

    private boolean isLimitValue(int number){
        return number > 0;
    }

    private void updatePriceView(FormView fm,Item item){
        fm.getTextView().setText("Rp."+String.valueOf(calculateTotal(item))+",-");
        fm.getEdit().setText(String.valueOf(item.getQty()));
    }


    public enum QtyButtonTrigger{
        DECREASE,INCREASE
    }

    private class FormView{

        TextView textView;
        EditText edit;

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public EditText getEdit() {
            return edit;
        }

        public void setEdit(EditText edit) {
            this.edit = edit;
//            this.edit.setKeyListener(null);
        }
    }


}
