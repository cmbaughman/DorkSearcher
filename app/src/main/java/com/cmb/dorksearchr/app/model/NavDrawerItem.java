package com.cmb.dorksearchr.app.model;

/**
 * Created by cmb on 3/4/14.
 */
public class NavDrawerItem {
    private String title;
    private int icon;
    private String count = "0";

    private boolean isCounterVisible = true;

    public NavDrawerItem() {}

    public NavDrawerItem(String title, String count) {
        this.setTitle(title);
        this.setCount(count);
    }

    public NavDrawerItem(String title, String count, boolean isCounterVisible) {
        this.setTitle(title);
        this.setCount(count);
        this.setCounterVisible(isCounterVisible);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public boolean isCounterVisible() {
        return isCounterVisible;
    }

    public void setCounterVisible(boolean isCounterVisible) {
        this.isCounterVisible = isCounterVisible;
    }
}
