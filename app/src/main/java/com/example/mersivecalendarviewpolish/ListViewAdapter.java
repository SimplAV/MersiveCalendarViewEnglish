package com.example.mersivecalendarviewpolish;


import static com.example.mersivecalendarviewpolish.Constants.FIFTH_COLUMN;
import static com.example.mersivecalendarviewpolish.Constants.FIRST_COLUMN;
import static com.example.mersivecalendarviewpolish.Constants.FOURTH_COLUMN;
import static com.example.mersivecalendarviewpolish.Constants.SECOND_COLUMN;
import static com.example.mersivecalendarviewpolish.Constants.THIRD_COLUMN;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<HashMap<String,String>> list;
    Activity activity;

    public ListViewAdapter(Activity activity, ArrayList<HashMap<String,String>> list)
    {
        super();
        this.activity = activity;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
       // TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
        TextView txtFourth;
        TextView txtFifth;
        TextView txtTimeSeparator;
       // TextView txtEventSeparator;
        ImageView imgViewSeparator;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView=inflater.inflate(R.layout.colmn_row, null);
            holder = new ViewHolder();

           // holder.txtFirst=(TextView) convertView.findViewById(R.id.TextID);
            holder.txtSecond=(TextView) convertView.findViewById(R.id.TextOrganizer);
            holder.txtThird=(TextView) convertView.findViewById(R.id.TextTitle);
            holder.txtFourth=(TextView) convertView.findViewById(R.id.TextStartTime);
            holder.txtFifth=(TextView) convertView.findViewById(R.id.TextEndTime);
            holder.imgViewSeparator=(ImageView) convertView.findViewById(R.id.imageViewSeparator);
            holder.txtTimeSeparator=(TextView) convertView.findViewById(R.id.textViewTimeSeparator);

            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map = list.get(position);
        //holder.txtFirst.setText(map.get(FIRST_COLUMN));

        if((map.get(FIRST_COLUMN).equals("2")))
        {
            //holder.txtSecond.setTextColor(Color.parseColor("#FF0000"));
            //holder.txtThird.setTextColor(Color.parseColor("#FF0000"));
            holder.txtFourth.setTextColor(Color.parseColor("#FF0000"));
            holder.txtFifth.setTextColor(Color.parseColor("#FF0000"));
            holder.txtTimeSeparator.setTextColor(Color.parseColor("#FF0000"));
            //holder.txtEventSeparator.setTextColor(Color.parseColor("#FF0000"));
            //holder.imgViewSeparator.setImageResource(R.drawable.separator_red);
        }else
        {
           // holder.txtSecond.setTextColor(Color.parseColor("#cecece"));
           // holder.txtThird.setTextColor(Color.parseColor("#cecece"));
           // holder.txtFourth.setTextColor(Color.parseColor("#cecece"));
           // holder.txtFifth.setTextColor(Color.parseColor("#cecece"));
           // holder.txtTimeSeparator.setTextColor(Color.parseColor("#cecece"));
            //holder.txtEventSeparator.setTextColor(Color.parseColor("#cecece"));
            //holder.imgViewSeparator.setImageResource(R.drawable.separator_grey);
        }
        holder.txtSecond.setText(map.get(SECOND_COLUMN));
        holder.txtThird.setText(map.get(THIRD_COLUMN));
        holder.txtFourth.setText(map.get(FOURTH_COLUMN));
        holder.txtFifth.setText(map.get(FIFTH_COLUMN));

        return convertView;
    }
}
