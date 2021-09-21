package com.example.hackathon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class adapter extends ArrayAdapter<data> {

    private Activity act;
    private List<data> list;


    public adapter(@NonNull Activity context, List<data> list) {
        super(context,R.layout.lvlayout,list);
        this.act= (Activity) context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){

        final ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(act).inflate(R.layout.lvlayout, null);
            viewHolder = new ViewHolder();

            viewHolder.place = (TextView) view.findViewById(R.id.cvtvplace);
            viewHolder.temp = (TextView) view.findViewById(R.id.tvtemp);
            viewHolder.oxy = (TextView) view.findViewById(R.id.tvoxy);
            viewHolder.place.setText(list.get(position).getPlace());
            viewHolder.temp.setText(list.get(position).getTemp());
            viewHolder.oxy.setText(list.get(position).getOxygen());

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {

        TextView place;
        TextView temp;
        TextView oxy;
    }


}

