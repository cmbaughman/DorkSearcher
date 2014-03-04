package com.cmb.dorksearchr.app;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Spinner;

import com.cmb.dorksearchr.app.model.GoogleDorks;

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

        return rootView;
    }
}
