package com.example.android.newsfeedudacity;

public class news {
    private String mSection;
    private String mDate;
    private String mTitle;
    private String mAuthor;
    private String mUrl;
    //constructor

    public news(String mSection, String mDate, String mTitle, String mAuthor, String mUrl) {
        this.mSection = mSection;
        this.mDate = mDate;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mUrl = mUrl;
    }

    //retrieve news section
    public String getSection() {
        return mSection;
    }

    //date retrieval

    public String getDate() {
        return mDate;
    }

    //title retrieval

    public String getTitle() {
        return mTitle;
    }

    //author's name retrieval

    public String getAuthor() {
        return mAuthor;
    }

    //URL
    public String getUrl() {
        return mUrl;
    }
}
