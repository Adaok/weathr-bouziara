package com.smartnsoft.weathr.ws;

import java.io.IOException;

import android.util.Log;

import com.smartnsoft.weathr.bo.Weather;

/**
 * @author Ludovic Roland
 * @since 2015.10.30
 */
public final class WeathRServices
{

  private static volatile WeathRServices instance;

  public static WeathRServices getInstance()
  {
    if (instance == null)
    {
      synchronized (WeathRServices.class)
      {
        if (instance == null)
        {
          instance = new WeathRServices();
        }
      }
    }
    return instance;
  }

  private WeathRServices()
  {
  }

  public Weather getWeather(String city, int forecastCount)
      throws IOException
  {
    Log.d(WeathRServices.class.getSimpleName(), "Retrieving the weather for the city '" + city + "' for the '" + forecastCount + "' days");
    //TODO
    return null;
  }

}
