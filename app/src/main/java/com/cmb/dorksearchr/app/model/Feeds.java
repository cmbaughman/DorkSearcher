package com.cmb.dorksearchr.app.model;

import android.util.Log;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by cmb on 3/6/14.
 */
public class Feeds {
    private String feedUrl;
    private String feedTitle;

    public Feeds() { }

    public Feeds(JSONObject jsonObject) {
        try {
            this.feedTitle = jsonObject.getString("title");
            this.feedUrl = jsonObject.getString("link");
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Feeds> fromJson(JSONArray jsonArray) {
        ArrayList<Feeds> feeds = new ArrayList<Feeds>();
        try {
            for(int i = 0; i <= jsonArray.length(); i++) {
                feeds.add(new Feeds(jsonArray.getJSONObject(i)));
            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        return feeds;
    }

    public static ArrayList<Feeds> fromXml(String xml) throws XmlPullParserException {
        ArrayList<Feeds> feeds = new ArrayList<Feeds>();
        InputStream inputStream = null;
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(inputStream, null);

        int eventType = xpp.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_DOCUMENT) {

            }
            else if (eventType == XmlPullParser.START_TAG) {

            }
            else if (eventType == XmlPullParser.END_TAG) {

            }
            else if(eventType == XmlPullParser.TEXT) {

            }
        }

        return feeds;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }
}
