package com.cmb.dorksearchr.app.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmb on 3/4/14.
 */
public class GoogleDorks {
    private String feedRes = "files_containing_usernames.txt";

    public GoogleDorks() {}

    public GoogleDorks(String feedRes) {
        this.feedRes = feedRes;
    }

    public String getFeedRes() {
        return this.feedRes;
    }

    public void setFeedRes(String val) {
        this.feedRes = val;
    }

    public List<String> getFeed(Context ctx) {
        Toast.makeText(ctx, this.getFeedRes(), Toast.LENGTH_SHORT).show();
        List<String> dlist = new ArrayList<String>();
        AssetManager amgr= ctx.getAssets();
        InputStream inpt= null;
        String line;

        try {
            inpt = amgr.open(this.getFeedRes());
            BufferedReader buf = new BufferedReader(new InputStreamReader(inpt));
            if (inpt != null) {
                while((line = buf.readLine()) != null) {
                    if(line.length() > 3) {
                        dlist.add(line);
                    }
                }
                inpt.close();
            }
        }
        catch(IOException iex){
            Toast.makeText(ctx, iex.getMessage()
                    , Toast.LENGTH_LONG).show();
        }

        return dlist;
    }

}
