package com.cmb.dorksearchr.app;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.cmb.dorksearchr.app.adapter.FeedListAdapter;
import com.cmb.dorksearchr.app.model.Feeds;
import com.cmb.dorksearchr.app.model.LatestExploits;

import java.util.List;

/**
 * Created by cmb on 3/6/14.
 */
public class LatestFeedsFragment extends Fragment {

    protected ListView listView;
    protected List<Feeds> list;
    public final String TAG = "LatestFeedsFragment";

    protected FeedListAdapter adapter;
    private ShareActionProvider shareActionProvider;
    protected LatestExploits latestExploits;

    public LatestFeedsFragment(int choice) {
        latestExploits = new LatestExploits(choice);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.latestfeeds_fragment, container, false);
        listView = (ListView)rootView.findViewById(R.id.feedsListBox);

        list = latestExploits.getJsonList();
        adapter = new FeedListAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Uri uuri = null;
                if(list.get(pos).getFeedUrl().length() <= 0) {
                    uuri = Uri.parse(list.get(pos).getFeedUrl());
                    startActivity(new Intent(Intent.ACTION_VIEW, uuri));
                    Toast.makeText(getActivity(), uuri.toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "There is no URL associated with this article", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }

}
