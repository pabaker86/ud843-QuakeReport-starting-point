package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by paul on 3/31/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {

        super(context, 0, earthquake);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);


        //Create a new Decimal formatter to only display one decimal after the number
        //and input the value from the earthquakes magnitude double into formatter -> String
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitudeOutput = formatter.format(currentEarthquake.getMagnitude());

        // Find the TextView in the list_item.xml layout with the ID magnitude
        TextView magTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // set the text on the magnitude TextView = the value from the magnitude formatter.
        magTextView.setText(magnitudeOutput);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        /** Split the City string into two different locations of strings to display
        *under two different text views.  1 being the Offset location if the word of appears
        and the second being the location offset either by value or "Near the" */
        String CityValue ;
        CityValue = (currentEarthquake.getCity());

        String CityOffset;

        int stringLength = CityValue.length();  // get the length of the string
        //Check the string to see if it contains a valid Offset value = "of"
        if (CityValue.contains("of")) {
            int breakpoint = CityValue.indexOf("of");  // find the breakpoint of where found
            // set the offset value to sub string up to the breakpoint
             CityOffset= (CityValue.substring(0, breakpoint+2));
            //Replace CityValue with the value of the Breakpoint + 2 to length end for the rest of the string.
             CityValue = CityValue.substring((breakpoint + 2), stringLength);
        } else {
            //set the offset value to "Near the" if only the City is listed
             CityOffset = "Near the";
        }

        // Find the TextView in the list_item.xml layout with the ID cityOffset
        TextView cityOffsetTextView = (TextView) listItemView.findViewById(R.id.cityOffset);
        //Set the offset city value
        cityOffsetTextView.setText(CityOffset);

        // Find the TextView in the list_item.xml layout with the ID city
        TextView cityTextView = (TextView) listItemView.findViewById(R.id.city);
        //Set the City Value
        cityTextView.setText(CityValue);


        // date object creator setting to the unix time format from the earthquakeAdapter getter method.
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984") From the Method helper
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM") from the Method helper
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * getMagnitudeColor(double magnitude) that returns the correct color value
     * based on the current earthquake’s magnitude value.
     */
    private int getMagnitudeColor(double magnitudeValue) {
        int magnitudeColorID;  // Integer value of Each earthquakes magnitude
        int magnitudeFloorValue = (int) Math.floor(magnitudeValue); // convert double to floor int
        switch(magnitudeFloorValue) {
            case 0:
                case 1:
                magnitudeColorID = R.color.magnitude1;
                break;
            case 2:
            magnitudeColorID = R.color.magnitude2;
            break;
            case 3:
            magnitudeColorID = R.color.magnitude3;
            break;
            case 4:
            magnitudeColorID = R.color.magnitude4;
            break;
            case 5:
            magnitudeColorID = R.color.magnitude5;
            break;
            case 6:
            magnitudeColorID = R.color.magnitude6;
            break;
            case 7:
            magnitudeColorID = R.color.magnitude7;
            break;
            case 8:
            magnitudeColorID = R.color.magnitude8;
            break;
            case 9:
            magnitudeColorID = R.color.magnitude9;
            break;
            default:
                magnitudeColorID = R.color.magnitude10plus;
                break;

       }
                return ContextCompat.getColor(getContext(), magnitudeColorID);
   }

}
