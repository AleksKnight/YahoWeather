package com.example.user.mymaterialdesign.ui;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.mymaterialdesign.R;
import com.example.user.mymaterialdesign.adapters.WeatherArrayAdapter;
import com.example.user.mymaterialdesign.api.loaders.WeatherLoader;
import com.example.user.mymaterialdesign.weatherdata.Astronomy;
import com.example.user.mymaterialdesign.weatherdata.Atmosphere;
import com.example.user.mymaterialdesign.weatherdata.GetQuery;
import com.example.user.mymaterialdesign.weatherdata.Location;
import com.example.user.mymaterialdesign.weatherdata.Units;
import com.example.user.mymaterialdesign.weatherdata.Wind;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, LoaderManager.LoaderCallbacks, View.OnClickListener {

  private ListView mListView;
  private WeatherArrayAdapter mWeatherArrayAdapter;
  private PopupMenu popupMenu;
  private TextView town;
  private TextView wind;
  private TextView atmosphere;
  private TextView astronomy;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle("");
    setSupportActionBar(toolbar);
    mListView = (ListView) findViewById(R.id.weater_list);
    town = (TextView) findViewById(R.id.town);
    town.setOnClickListener(this);

    wind = (TextView) findViewById(R.id.wind);
    wind.setVisibility(View.GONE);

    atmosphere = (TextView) findViewById(R.id.atmosphere);
    atmosphere.setVisibility(View.GONE);

    astronomy = (TextView) findViewById(R.id.astronomy);
    astronomy.setVisibility(View.GONE);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_town) {
      showPopupMenuTown();
      return true;
    }
    if (id == R.id.action_add_town) {
      final View view = View.inflate(this, R.layout.dialog_town, null);
      AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
      alertDialog.setView(view);
      alertDialog.setCancelable(false);

      // Setting Negative "Cancel" Button
      alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          EditText editTextTown = (EditText) view.findViewById(R.id.editTextTown);
          addToPopop(editTextTown.getText().toString());
          dialog.cancel();
        }
      });
      alertDialog.show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void showPopupMenuTown() {
    if (popupMenu == null) {
      initPopup();
    }
    popupMenu.show();
  }

  private void initPopup() {
    popupMenu = new PopupMenu(this, findViewById(R.id.action_town));
    popupMenu.inflate(R.menu.town_menu);
    popupMenu.setOnMenuItemClickListener(this);
  }

  private void addToPopop(String nameItem) {
    if (popupMenu == null) {
      initPopup();
    }
    popupMenu.getMenu().add(nameItem);
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {
    Bundle bundle = new Bundle();
    bundle.putString("town", item.getTitle().toString());
    getLoaderManager().initLoader(R.id.weatherLoader, bundle, this);
    return false;
  }


  @Override
  public Loader onCreateLoader(int id, Bundle args) {
    switch (id) {
      case R.id.weatherLoader:
        return new WeatherLoader(this, args.getString("town"));
      default:
        return null;
    }
  }

  @Override
  public void onLoadFinished(Loader loader, Object data) {
    int id = loader.getId();
    switch (id) {
      case R.id.weatherLoader:
        if (data != null) {
          GetQuery m = (GetQuery) data;

          Location location = m.getQuery().getResults().getChannel().getLocation();
          town.setText(location.getCountry() + " " + location.getCity() + " " + location.getRegion());

          Units units = m.getQuery().getResults().getChannel().getUnits();

          Wind wind_ = m.getQuery().getResults().getChannel().getWind();
          wind.setText("Wind \n chill " + wind_.getChill() + " direction " + wind_.getDirection() + " " +
              "speed " + wind_.getSpeed() + " " + units.getSpeed());

          Atmosphere atmosphere_ = m.getQuery().getResults().getChannel().getAtmosphere();
          atmosphere.setText("Atmosphere \n humidity " + atmosphere_.getHumidity() + " pressure " +
              "" + atmosphere_
              .getPressure() + " rising " + atmosphere_.getRising() + " visibility " + atmosphere_
              .getVisibility());

          Astronomy astronomy_ = m.getQuery().getResults().getChannel().getAstronomy();
          astronomy.setText("Astronomy \n sunrise " + astronomy_.getSunrise() + " sunset " + astronomy_
              .getSunset());

          if (mWeatherArrayAdapter == null) {
            mWeatherArrayAdapter = new WeatherArrayAdapter(this, m.getQuery().getResults().getChannel().getItem()
                .getForecast());
            mListView.setAdapter(mWeatherArrayAdapter);
          } else {
            mWeatherArrayAdapter.setForecasts(m.getQuery().getResults().getChannel().getItem()
                .getForecast());
            mWeatherArrayAdapter.notifyDataSetChanged();
          }
        }
    }
    getLoaderManager().destroyLoader(id);
  }

  @Override
  public void onLoaderReset(Loader loader) {

  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.town) {
      if (wind.getVisibility() == View.GONE) {
        wind.setVisibility(View.VISIBLE);
        atmosphere.setVisibility(View.VISIBLE);
        astronomy.setVisibility(View.VISIBLE);
      } else {
        wind.setVisibility(View.GONE);
        atmosphere.setVisibility(View.GONE);
        astronomy.setVisibility(View.GONE);
      }
    }

  }
}
