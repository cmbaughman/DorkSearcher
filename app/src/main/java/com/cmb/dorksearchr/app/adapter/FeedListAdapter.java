package com.cmb.dorksearchr.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cmb.dorksearchr.app.R;
import com.cmb.dorksearchr.app.model.Feeds;

import java.util.List;

/**
 * Created by cmb on 3/6/14.
 */
public class FeedListAdapter extends ArrayAdapter<Feeds> {

    private static class ViewHolder {
        TextView title;
        TextView url;
    }

    public FeedListAdapter(Context context, List<Feeds> feedsArrayList) {
        super(context, R.layout.feed_item, feedsArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Feeds feeds = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.feed_item, null);
            viewHolder.title = (TextView)convertView.findViewById(R.id.feedTitle);
            viewHolder.url = (TextView)convertView.findViewById(R.id.feedUrl);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.title.setText(feeds.getFeedTitle());
        viewHolder.url.setText(feeds.getFeedUrl());

        return convertView;
    }
}
