package com.cmb.dorksearchr.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.Toast;

import com.cmb.dorksearchr.app.common.screens.AboutDialog;
import com.cmb.dorksearchr.app.model.GoogleDorks;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by cmb on 3/4/14.
 */
public class HomeFragment extends Fragment {
    Spinner catSpin;
    ListView listV;
    final GoogleDorks dorks = new GoogleDorks();
    public final String TAG = "MainActivity";

    ArrayAdapter<String> adapter;
    List<String> list= null;
    private ShareActionProvider mShare;
    protected String dorkSelected = "Nothing selected to share";


    public HomeFragment() {};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        listV = (ListView)rootView.findViewById(R.id.listBox);
        catSpin = (Spinner)rootView.findViewById(R.id.catSpin);

        list = dorks.getFeed(rootView.getContext());
        adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, list);
        listV.setAdapter(adapter);
        listV.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        catSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long idt) {
                switch(pos) {
                    case 0:
                        dorks.setFeedRes("files_containing_usernames.txt");
                        break;
                    case 1:
                        dorks.setFeedRes("files_containing_juicy_info.txt");
                        break;
                    case 2:
                        dorks.setFeedRes("files_containing_passwords.txt");
                        break;
                    case 3:
                        dorks.setFeedRes("various_connected_devices.txt");
                        break;
                    case 4:
                        dorks.setFeedRes("footholds.txt");
                        break;
                    case 5:
                        dorks.setFeedRes("online_shopping_info.txt");
                        break;
                    default:
                        dorks.setFeedRes("files_containing_usernames.txt");
                        break;

                }
                adapter.clear();
                list = dorks.getFeed(getBaseContext());
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
                listV.refreshDrawableState();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //required to implement
            }
        });

        @SuppressLint("NewApi")
        class ItemHighlighterListener implements AdapterView.OnItemLongClickListener {
            private View oldSelection = null;

            public void clearSelection() {
                if(oldSelection != null) {
                    oldSelection.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }
            }

            @SuppressWarnings("deprecation")
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int pos, long id) {
                clearSelection();
                oldSelection = view;
                view.setBackgroundDrawable(view.getContext().getResources().getDrawable(R.drawable.listitem_selector));
                return false;
            }
        }

        listV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           int pos, long arg3) {
                dorkSelected = list.get(pos).toString();

                if (BuildConfig.DEBUG) { Log.d(TAG, LOG + " onItemLongClick Called"); }
                Intent inten = new Intent(Intent.ACTION_SEND);
                inten.setType("text/plain");
                inten.putExtra(Intent.EXTRA_TEXT, dorkSelected);
                setShareIntent(inten);
                view.setSelected(true);
                return true;
            }
        });

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long idt) {
                Uri uuri = null;
                if(list.get(pos).toString().startsWith("http")) {
                    uuri = Uri.parse(list.get(pos).toString());
                    startActivity(new Intent(Intent.ACTION_VIEW, uuri));
                }
                else {
                    try {
                        uuri = Uri.parse("http://www.google.com/#q=" + URLEncoder.encode(list.get(pos), "UTF-8"));
                    }
                    catch(UnsupportedEncodingException uex) {
                        Log.e(TAG, "MainActivity", uex);
                    }
                    startActivity(new Intent(Intent.ACTION_VIEW, uuri));
                }
                Toast.makeText(getBaseContext(), uuri.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_help:
                // location found
                AboutDialog sh = new AboutDialog(this);
                sh.setTitle("About Google Dorks by CMB");
                sh.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShare != null) {
            mShare.setShareIntent(shareIntent);
        }
    }

    private Intent getShareItem() {
        Intent inten = new Intent(Intent.ACTION_SEND);
        inten.setType("text/plain");
        inten.putExtra(Intent.EXTRA_TEXT, dorkSelected);
        mShare.setShareIntent(inten);
        return inten;
    }
}
