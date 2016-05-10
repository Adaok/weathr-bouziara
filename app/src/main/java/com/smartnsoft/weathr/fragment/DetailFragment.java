package com.smartnsoft.weathr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartnsoft.weathr.R;
import com.smartnsoft.weathr.bo.Weather.Forecast;
import com.smartnsoft.weathr.fragment.HomeFragment.WeatherSimpleViewHolder;

/**
 * @author Ludovic Roland
 * @since 2015.11.18
 */
public final class DetailFragment
    extends Fragment
{

  private Bundle weatherArguments;

  public void setWeatherArguments(Bundle weatherArguments)
  {
    this.weatherArguments = weatherArguments;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    final View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

    final Forecast forecast = (Forecast) weatherArguments.getSerializable(HomeFragment.BUSINESS_OBJECT_EXTRA);
    final int backgroundColor = weatherArguments.getInt(HomeFragment.BACKGROUND_COLOR_EXTRA, getResources().getColor(R.color.pink));

    final WeatherSimpleViewHolder weatherViewHolder = new WeatherSimpleViewHolder(rootView);
    weatherViewHolder.update(forecast, backgroundColor);

    return rootView;
  }

}
