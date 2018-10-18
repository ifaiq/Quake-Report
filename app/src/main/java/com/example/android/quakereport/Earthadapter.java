package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.R.id.one;

/**
 * Created by Faiq on 26-Aug-17.
 */

public class Earthadapter extends ArrayAdapter<Earth> {

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int Magnitudecolor(double magnitude){
        int magcolor;
        int magfloor = (int)Math.floor(magnitude);
        switch (magfloor){
            case 0:
            case 1:
                magcolor= R.color.magnitude1;
                break;
            case 2:
                magcolor= R.color.magnitude2;
                break;
            case 3:
                magcolor= R.color.magnitude3;
                break;
            case 4:
                magcolor= R.color.magnitude4;
                break;
            case 5:
                magcolor= R.color.magnitude5;
                break;
            case 6:
                magcolor= R.color.magnitude6;
                break;
            case 7:
                magcolor= R.color.magnitude7;
                break;
            case 8:
                magcolor= R.color.magnitude8;
                break;
            case 9:
                magcolor= R.color.magnitude9;
                break;
            default:
                magcolor= R.color.magnitude10plus;
                break;


        }
        return ContextCompat.getColor(getContext(),magcolor);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    String primaryLocation;
    String locationOffset;
    private static final String LOCATION_SEPARATOR = " of ";


    public Earthadapter(Activity context, List<Earth> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview, parent, false);
        }




        // Get the {@link AndroidFlavor} object located at this position in the list
        Earth currentAndroidFlavor = getItem(position);
        String originalLocation = currentAndroidFlavor.getLoc();

        TextView magTextView = (TextView) listItemView.findViewById(one);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        String formattedMagnitude = formatMagnitude(currentAndroidFlavor.getMag());
        // Display the magnitude of the current earthquake in that TextView
        magTextView.setText(formattedMagnitude);

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView locTextView = (TextView) listItemView.findViewById(R.id.two);
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.twob);
        locationOffsetView.setText(primaryLocation);
        locTextView.setText(locationOffset);

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.three);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        Date dateObject = new Date(currentAndroidFlavor.getDate());
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        GradientDrawable magnitudeCircle = (GradientDrawable)magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = Magnitudecolor(currentAndroidFlavor.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        return listItemView;
    }
}
