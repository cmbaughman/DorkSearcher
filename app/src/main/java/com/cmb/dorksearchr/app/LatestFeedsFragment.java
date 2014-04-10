package com.cmb.dorksearchr.app;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.cmb.dorksearchr.app.adapter.FeedListAdapter;
import com.cmb.dorksearchr.app.common.LoadDataListener;
import com.cmb.dorksearchr.app.common.rssreader.lib.RssFeed;
import com.cmb.dorksearchr.app.common.rssreader.lib.RssItem;
import com.cmb.dorksearchr.app.common.rssreader.lib.RssReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmb on 3/6/14.
 */
public class LatestFeedsFragment extends Fragment implements LoadDataListener {

    protected static final String EXPLOITDB_FEED = "http://www.exploit-db.com/rss.xml";
    protected static final String PACKETSTORM_FEED = "http://rss.packetstormsecurity.com/news/";
    protected static final String PACKETSTORM_FILES_FEED = "http://rss.packetstormsecurity.com/files";

    protected String url;
    protected RssAdapter adapter;
    protected ListView listView;
    protected ArrayList<RssItem> list;

    public final String TAG = "LatestFeedsFragment";

    private ShareActionProvider shareActionProvider;

    public LatestFeedsFragment(int choice) {
        switch (choice) {
            case 1:
                url = EXPLOITDB_FEED;
                break;
            case 2:
                url = PACKETSTORM_FEED;
                break;
            case 3:
                url = PACKETSTORM_FILES_FEED;
                break;
            default:
                url = EXPLOITDB_FEED;
                break;
        }

        //RssFeed rssFeed = RssReader.read(url);
        //list  = rssFeed.getRssItems();
        new RetrieveFeedTask().execute(url.toString());
     }

    @Override
    public void onLoadComplete(ArrayList<RssItem> rssItemList) {
        Log.d(TAG, "In onLoadCompleted");
        adapter = new RssAdapter(getActivity(), R.layout.rss_list_item, rssItemList);

        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.latest_feeds_fragment, container, false);
        listView = (ListView)rootView.findViewById(R.id.feedsListBox);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Uri uuri = null;
                if(list.get(pos).getLink().length() <= 0) {
                    uuri = Uri.parse(list.get(pos).getLink());
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
    private class RetrieveFeedTask extends AsyncTask<String, Void, RssFeed> {
        private Exception exception;
        protected RssFeed rssFeed;

        @Override
        protected void onPreExecute() {}

        @Override
        protected RssFeed doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                rssFeed = RssReader.read(url);
                Log.d(TAG, "In thread getting RSS FEED");
                return rssFeed;
            }
            catch(Exception e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(RssFeed feed) {
            if (this.exception != null) {
                Log.e(TAG, this.exception.getMessage());
            }
            else
            {
                onLoadComplete(rssFeed.getRssItems());
                Log.d(TAG, "In postExecute NOW with RSS");
            }
        }

    }

    private class RssAdapter extends ArrayAdapter<RssItem> {
        private ArrayList<RssItem> rssItems;

        public RssAdapter(Context ctx, int viewResourceId, ArrayList<RssItem> rssItems) {
            super(ctx, viewResourceId, rssItems);
            this.rssItems = new ArrayList<RssItem>();
            this.rssItems.addAll(rssItems);
        }

        private class ViewHolder {
            TextView titleText;
            TextView descText;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.rss_list_item, null);
                holder.titleText = (TextView)convertView.findViewById(R.id.feedTitle1);
                holder.descText = (TextView)convertView.findViewById(R.id.feedDesc);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder)convertView.getTag();
            }
            RssItem item = rssItems.get(position);
            holder.titleText.setText(item.getTitle());
            holder.descText.setText(item.getDescription());
            return convertView;
        }
    }
}
