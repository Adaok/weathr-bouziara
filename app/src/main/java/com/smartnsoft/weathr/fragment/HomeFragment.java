package com.smartnsoft.weathr.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smartnsoft.weathr.AboutActivity;
import com.smartnsoft.weathr.R;
import com.smartnsoft.weathr.bo.Weather.Forecast;

/**
 * @author Ludovic Roland
 * @since 2015.10.19
 */
public final class HomeFragment
    extends Fragment
    implements OnRefreshListener, OnSharedPreferenceChangeListener, OnClickListener
{

  public static final class WeatherSimpleViewHolder
      extends ViewHolder
  {

    private final TypedArray forecastImagesResources;

    private final SimpleDateFormat dateFormat;

    private final CardView cardView;

    private final ImageView image;

    private final TextView date;

    private final TextView temperature;

    public WeatherSimpleViewHolder(View itemView)
    {
      super(itemView);

      dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
      forecastImagesResources = itemView.getResources().obtainTypedArray(R.array.forecastImages);
      date = (TextView) itemView.findViewById(R.id.date);
      image = (ImageView) itemView.findViewById(R.id.image);
      temperature = (TextView) itemView.findViewById(R.id.temperature);
      cardView = (CardView) itemView.findViewById(R.id.cardView);
    }

    public void update(final Forecast forecast, final int backgroundColor)
    {
      try
      {
        final long dateInMilliseconds = dateFormat.parse(forecast.date).getTime();
        date.setText(DateUtils.formatDateTime(date.getContext(), dateInMilliseconds, DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NO_YEAR));
      }
      catch (ParseException exception)
      {
        Log.w(HomeFragment.TAG, "Unable to parse the forcast date '" + forecast.date + "'!");
        date.setText(forecast.date);
      }

      cardView.setCardBackgroundColor(backgroundColor);
      image.setImageDrawable(forecastImagesResources.getDrawable(forecast.type.ordinal()));
      temperature.setText(temperature.getResources().getString(R.string.weather_temperature, forecast.temperatureMax));
    }

  }

  public static final String BUSINESS_OBJECT_EXTRA = "businessObjectExtra";

  public static final String BACKGROUND_COLOR_EXTRA = "backgroundColorExtra";

  public static final String CITY_PREFERENCES_KEY = "cityPreferencesKey";

  public static final String FORECASTS_DAYS_PREFRENCES_KEY = "forecastsPreferencesKey";

  private static final String TAG = HomeFragment.class.getSimpleName();

  private SharedPreferences preferences;

  private ProgressBar progressBar;

  private SwipeRefreshLayout swipeRefreshLayout;

  private View errorView;

  private TextView errorMessage;

  private Button errorButton;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    setHasOptionsMenu(true);

    preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    preferences.registerOnSharedPreferenceChangeListener(this);

    errorView = rootView.findViewById(R.id.errorView);
    errorMessage = (TextView) rootView.findViewById(R.id.errorMessage);
    errorButton = (Button) rootView.findViewById(R.id.errorButton);
    errorButton.setOnClickListener(this);

    progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

    swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
    swipeRefreshLayout.setOnRefreshListener(this);
    swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.colors));

    return rootView;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
  {
    inflater.inflate(R.menu.settings, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    if (item.getItemId() == R.id.menu_settings)
    {
      final ParameterDialogFragment parameterDialogFragment = new ParameterDialogFragment();
      parameterDialogFragment.show(getActivity().getFragmentManager(), "parameter");
      return true;
    }
    else if (item.getItemId() == R.id.menu_about)
    {
      startActivity(new Intent(getActivity(), AboutActivity.class));
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();

    preferences.unregisterOnSharedPreferenceChangeListener(this);
  }

  @Override
  public void onRefresh()
  {
    //do the network call
  }

  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
  {
    //do the network call
  }


  @Override
  public void onClick(View view)
  {
    if (view == errorButton)
    {
      onRefresh();
    }
  }

}
