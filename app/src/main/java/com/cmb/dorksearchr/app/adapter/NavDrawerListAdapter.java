package com.cmb.dorksearchr.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cmb.dorksearchr.app.R;
import com.cmb.dorksearchr.app.model.NavDrawerItem;

import java.util.ArrayList;

/**
 * Created by cmb on 3/4/14.
 */
public class NavDrawerListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapter(Context ctx, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_list_item, null);
        }
        TextView title = (TextView)convertView.findViewById(R.id.nav_sec_title);
        TextView counter = (TextView)convertView.findViewById(R.id.counter);
        title.setText(navDrawerItems.get(position).getTitle());

        if(navDrawerItems.get(position).isCounterVisible()) {
            counter.setText(navDrawerItems.get(position).getCount());
        }
        else {
            counter.setVisibility(View.GONE);
        }

        return convertView;
    }
}
