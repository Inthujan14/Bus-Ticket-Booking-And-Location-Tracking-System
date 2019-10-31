package com.example.inthujan.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItems;

    CustomAdapter(Context context,List<RowItem> rowItems){
        this.context=context;
        this.rowItems=rowItems;
    }
    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    private class ViewHolder{
        ImageView busImage;
        TextView busName;
        TextView busRoute;
        TextView busTime;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        LayoutInflater mInflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.list_item,null);
            holder=new ViewHolder();

            holder.busName=(TextView)convertView.findViewById(R.id.busName);
            holder.busImage=(ImageView) convertView.findViewById(R.id.busImage);
            holder.busRoute=(TextView)convertView.findViewById(R.id.busRoute);
            holder.busTime=(TextView)convertView.findViewById(R.id.busTime);

            RowItem row_pos=rowItems.get(position);

            holder.busImage.setImageResource(row_pos.getPicId());
            holder.busName.setText(row_pos.getBusName());
            holder.busRoute.setText(row_pos.getRoute());
            holder.busTime.setText(row_pos.getTime());
        }
        return convertView;
    }
}
