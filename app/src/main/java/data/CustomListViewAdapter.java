package data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import model.Event;
import musictag.hytham1.com.musictag.ActivityDetails;
import musictag.hytham1.com.musictag.AppController;
import musictag.hytham1.com.musictag.R;

/**
 * Created by Hytham on 2/15/2017.
 */

public class CustomListViewAdapter extends ArrayAdapter<Event> {
    private LayoutInflater inflater;
    private ArrayList<Event> data;
    private Activity mContext;
    private int layoutResourceId;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListViewAdapter(Activity context, int resource, ArrayList<Event> objs) {
        super(context, resource, objs);
        data = objs;
        mContext = context;
        layoutResourceId = resource;
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getPosition(Event item) {
        return super.getPosition(item);
    }

    @Nullable
    @Override
    public Event getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = null;

        if (row == null){
            inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(layoutResourceId , parent , false);

            //Now get Resources from View Holder

            viewHolder = new ViewHolder();
            viewHolder.bandImage = (NetworkImageView) row.findViewById(R.id.bandImage);
            viewHolder.venue = (TextView)row.findViewById(R.id.venueText);
            viewHolder.headLiner = (TextView) row.findViewById(R.id.headLinerText);
            viewHolder.when = (TextView)row.findViewById(R.id.whenText);
            viewHolder.where = (TextView) row.findViewById(R.id.whereText);
           row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

          viewHolder.event = data.get(position);

        // we can display the data

        viewHolder.headLiner.setText("HeadLiner: " + viewHolder.event.getHeadliner());
        viewHolder.venue.setText("Venue: " + viewHolder.event.getVenueName());
        viewHolder.when.setText("When " + viewHolder.event.getStartDate() );
        viewHolder.where.setText("Where: " + viewHolder.event.getStreet() + " ," + viewHolder.event.getCity() + ", "
                + viewHolder.event.getCountry());
        viewHolder.bandImage.setImageUrl(viewHolder.event.getUrl() , imageLoader);
        viewHolder.website = viewHolder.event.getWebSite();

        final ViewHolder finalViewHolder = viewHolder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext , ActivityDetails.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("eventObj" , finalViewHolder.event);
                i.putExtras(mBundle);
                mContext.startActivity(i);
            }
        });


        return row;
    }
    public class ViewHolder{
        Event event;
        TextView headLiner;
        TextView venue;
        TextView where;
        TextView when;
        String website;
        NetworkImageView bandImage;


    }
}
