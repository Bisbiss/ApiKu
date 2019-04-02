package com.vcode.apiku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<TimItem> {

    private List<TimItem> TimItemList;

    private Context context;

    public ListViewAdapter(List<TimItem> playerItemList, Context context) {
        super(context, R.layout.list_item, playerItemList);
        this.TimItemList = playerItemList;
        this.context = context;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textViewKey = listViewItem.findViewById(R.id.textViewKey);
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewCode = listViewItem.findViewById(R.id.textViewCode);

        TimItem playerItem = TimItemList.get(position);

        textViewKey.setText(playerItem.getKey());
        textViewName.setText(playerItem.getName());
        textViewCode.setText(playerItem.getCode());


        return listViewItem;
    }
}