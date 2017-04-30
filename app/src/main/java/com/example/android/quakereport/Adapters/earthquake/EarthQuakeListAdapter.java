package com.example.android.quakereport.Adapters.earthquake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.Models.EarthquakeInfo;
import com.example.android.quakereport.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class EarthQuakeListAdapter extends ArrayAdapter<EarthquakeInfo>
{
    public EarthQuakeListAdapter(@NonNull Context context, @NonNull List<EarthquakeInfo> earthquakeInfoList)
    {
        super(context, 0, earthquakeInfoList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater
                                    .from(getContext())
                                    .inflate(R.layout.earthquake_list_item, parent, false);

        }

        EarthquakeInfo info = getItem(position);

        TextView magnitudeTextView = (TextView)
                listItemView
                        .findViewById(
                                R.id.earthquakeListItem_textView_magnitude);
        TextView locationOffsetTextView = (TextView)
                listItemView
                        .findViewById(
                                R.id.earthquakeListItem_textView_locationOffset);
        TextView primaryLocationTextView = (TextView)
                listItemView
                        .findViewById(
                                R.id.earthquakeListItem_textView_primaryLocation);
        TextView dateTextView = (TextView)
                listItemView
                        .findViewById(
                                R.id.earthquakeListItem_textView_date);
        TextView timeTextView = (TextView)
                listItemView.findViewById(R.id.earthquakeListItem_textView_time);

        double magnitudeValue = info.getMagnitude();
        magnitudeTextView.setText(Double.toString(magnitudeValue));
        setMagnitudeTextViewBackgroundColor(magnitudeTextView, magnitudeValue);
        setLocationTextViews(primaryLocationTextView, locationOffsetTextView, info.getCity());
        dateTextView.setText(gregorianCalendarToSimpleDate(info.getDate(), "LLL dd, yyyy"));
        timeTextView.setText(gregorianCalendarToSimpleDate(info.getDate(), "h:mm a"));



        return listItemView;
    }

    private void setLocationTextViews(TextView primaryLocationTextView, TextView locationOffsetTextView, String city)
    {
        String[] locations = city.split("of");

        if(locations.length == 2)
        {
            locationOffsetTextView.setText(locations[0].trim() + " of");
            primaryLocationTextView.setText(locations[1].trim());
        }
        else
        {
            locationOffsetTextView.setText("Near the".trim());
            primaryLocationTextView.setText(city.trim());
        }
    }


    private String gregorianCalendarToSimpleDate(Calendar date, String format)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setCalendar(date);
        String formattedDate = simpleDateFormat.format(date.getTime());
        return formattedDate;
    }

    private void setMagnitudeTextViewBackgroundColor(TextView view, double magnitude)
    {
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) view.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(magnitude);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
    }

    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeIntVal = (int) magnitude;
        switch (magnitudeIntVal)
        {
            case 0:
            case 1:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
    }


}
