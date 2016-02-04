package com.example.user.mymaterialdesign.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.mymaterialdesign.R;
import com.example.user.mymaterialdesign.weatherdata.Forecast;

import java.util.List;

/**
 * Created by user on 03.02.2016.
 */
public class WeatherArrayAdapter extends BaseAdapter {

  private LayoutInflater lInflater;
  private List<Forecast> forecasts;
  private Context context;

  public WeatherArrayAdapter(Context _context, List<Forecast> _forecasts) {
    this.context = _context;
    this.forecasts = _forecasts;
    lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public void setForecasts(List<Forecast> _forecasts) {
    this.forecasts = _forecasts;
  }

  @Override
  public int getCount() {
    return forecasts.size();
  }

  @Override
  public Object getItem(int position) {
    return forecasts.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    if (view == null) {
      view = lInflater.inflate(R.layout.list_weather, parent, false);
    }

    Forecast forecast = (Forecast) getItem(position);
    TextView date = (TextView) view.findViewById(R.id.date);
    date.setText(forecast.getDate() + " " + forecast.getDay());

    TextView temp = (TextView) view.findViewById(R.id.temperature);
    temp.setText("Temperature high " + celcii(forecast.getHigh()) + "° low " + celcii(forecast
        .getLow())+"°");

    TextView text = (TextView) view.findViewById(R.id.text_weather);
    text.setText(forecast.getText());


    return view;
  }

  private float celcii(int farengeut)
  {
    float c = (farengeut-32)*5/9;
    return c;
  }
}
