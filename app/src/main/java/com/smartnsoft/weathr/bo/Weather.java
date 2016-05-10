package com.smartnsoft.weathr.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ludovic Roland
 * @since 2015.10.19
 */
public final class Weather
    implements Serializable
{

  public static final class Forecast
      implements Serializable
  {

    public enum Type
    {
      SUNNY, CLOUDY, RAINY, SNOWY, STORMY
    }

    public enum WindDirection
    {
      N, NW, W, SW, S, SE, E, NE
    }

    public final String date;

    public final int temperatureMin;

    public final int temperatureMax;

    public final Type type;

    public final int uvIndex;

    public final WindDirection windDirection;

    public final int windSpeed;

    public Forecast(String date, int temperatureMin, int temperatureMax, Type type, int uvIndex,
        WindDirection windDirection, int windSpeed)
    {
      this.date = date;
      this.temperatureMin = temperatureMin;
      this.temperatureMax = temperatureMax;
      this.type = type;
      this.uvIndex = uvIndex;
      this.windDirection = windDirection;
      this.windSpeed = windSpeed;
    }

  }

  public final int code;

  public final String message;

  public final String city;

  public final List<Forecast> forecasts;

  public Weather(int code, String message, String city, List<Forecast> forecasts)
  {
    this.code = code;
    this.message = message;
    this.city = city;
    this.forecasts = forecasts;
  }

}
