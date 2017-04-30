package com.example.android.quakereport.Adapters.earthquake;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.Models.EarthquakeInfo;
import com.example.android.quakereport.R;

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
        TextView cityNameTextView = (TextView)
                listItemView
                        .findViewById(
                                R.id.earthquakeListItem_textView_cityName);
        TextView dateTextView = (TextView)
                listItemView
                        .findViewById(
                                R.id.earthquakeListItem_textView_date);
        TextView timeTextView = (TextView)
                listItemView.findViewById(R.id.earthquakeListItem_textView_time);

        magnitudeTextView.setText(Double.toString(info.getMagnitude()));
        cityNameTextView.setText(info.getCity());
        dateTextView.setText(gregorianCalendarToSimpleDate(info.getDate(), "LLL dd, yyyy"));
        timeTextView.setText(gregorianCalendarToSimpleDate(info.getDate(), "h:mm a"));



        return listItemView;
    }


    private String gregorianCalendarToSimpleDate(Calendar date, String format)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setCalendar(date);
        String formattedDate = simpleDateFormat.format(date.getTime());
        return formattedDate;
    }


}
