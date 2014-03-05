package com.cmb.dorksearchr.app.common.screens;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.TextView;

import com.cmb.dorksearchr.app.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by cmb on 3/4/14.
 */
public class AboutDialog extends Dialog {
    private static Context mContext = null;

    public AboutDialog(Context ctx) {
        super(ctx);
        mContext = ctx;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.about_dialog);
        TextView tv = (TextView)findViewById(R.id.legal_text);
        tv.setText(readRawTextFile(R.raw.legal));
        tv = (TextView)findViewById(R.id.info_text);
        tv.setText(Html.fromHtml(readRawTextFile(R.raw.info)));
        tv.setLinkTextColor(Color.WHITE);
        Linkify.addLinks(tv, Linkify.ALL);

    }

    public static String readRawTextFile(int id) {
        InputStream inputStream = mContext.getResources().openRawResource(id);

        InputStreamReader in = new InputStreamReader(inputStream);
        BufferedReader buf = new BufferedReader(in);
        String line;


        StringBuilder text = new StringBuilder();
        try {
            while (( line = buf.readLine()) != null) text.append(line);
        } catch (IOException e) {
            return null;
        }

        return text.toString();
    }
}
