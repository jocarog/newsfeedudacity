package com.example.android.newsfeedudacity;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends ArrayAdapter<news> {

    public NewsAdapter(Context context, ArrayList<news> news) {
        super ( context, 0, news );
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @Nullable ViewGroup parent) {

        final ViewHolder holder;

        try {
            if (convertView == null) {
                convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_item, parent, false );
                holder = new ViewHolder ( convertView );
                convertView.setTag ( holder );
            } else {
                holder = (ViewHolder) convertView.getTag ();
            }
            if (position < getCount ()) {
                news currentNews = getItem ( position );

                holder.sectionTextView.setText ( currentNews.getSection () );
                holder.titleTextView.setText ( currentNews.getTitle () );
                holder.dateTextView.setText ( formatDate ( currentNews.getDate () ) );
                holder.authorTextView.setText ( currentNews.getAuthor () );
            }
        } catch (NullPointerException npe) {
            Log.e ( "NewsAdapter", "getSection() throws npe", npe );
        }
        return convertView;
    }

    // Create ViewHolder to increase loading efficiency
    static class ViewHolder {
        private TextView sectionTextView;
        private TextView titleTextView;
        private TextView dateTextView;
        private TextView authorTextView;

        ViewHolder(View view) {
            sectionTextView = view.findViewById ( R.id.section );
            titleTextView = view.findViewById ( R.id.title );
            dateTextView = view.findViewById ( R.id.date );
            authorTextView = view.findViewById ( R.id.author );
        }
    }

    /* date string, example format: Jan 09, 2018 10:05:08 AM */
    private String formatDate(String date) {
        Date dateObject = new Date ();
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd'T'kk:mm:ss'Z'", Locale.US );
            dateObject = simpleDateFormat.parse ( date );
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        SimpleDateFormat newDateFormat = new SimpleDateFormat ( "MMM dd, yyyy hh:mm:ss a", Locale.US );
        String dateFormatted = newDateFormat.format ( dateObject );
        return dateFormatted;
    }

    public static class NewsLoader extends AsyncTaskLoader<List<news>> {

        // declare variables
        private String url;

        // default constructor for loader object
        public NewsLoader(Context context, String url) {
            super ( context );
            this.url = url;
        }

        // force load on start
        @Override
        protected void onStartLoading() {
            forceLoad ();
        }

        // conducted on background thread
        @Override
        public List<news> loadInBackground() {
            if (url == null) {
                return null;
            }

            // perform the network request, parse the JSON response, and extract a list of news articles
            List<news> news = QueryUtils.fetchNewsData ( url );
            return news;
        }
    }
}