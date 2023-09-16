package com.example.technews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> title_list,source_list,author_list;
    LayoutInflater layoutInflater;


    public CustomAdapter(Context context, ArrayList<String> title_list,ArrayList<String> source_list,ArrayList<String> author_list){
        this.context = context;
        this.title_list = title_list;
        this.source_list = source_list;
        this.author_list = author_list;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return title_list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.list_item,null);
        TextView title = (TextView) view.findViewById(R.id.newstitleid);
        TextView source = (TextView) view.findViewById(R.id.newssourceid);
        TextView author = (TextView) view.findViewById(R.id.newsauthorid);

        title.setText(title_list.get(i));
        source.setText(source_list.get(i));
        author.setText(author_list.get(i));
        return view;
    }
}
