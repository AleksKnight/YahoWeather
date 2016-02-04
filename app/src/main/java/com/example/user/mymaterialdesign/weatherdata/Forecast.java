package com.example.user.mymaterialdesign.weatherdata;

/**
 * Created by user on 02.02.2016.
 */
public class Forecast {
  private String code;
  private String date;
  private String day;
  private int high;
  private int low;
  private String text;

  /**
   * @return The code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code The code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return The date
   */
  public String getDate() {
    return date;
  }

  /**
   * @param date The date
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * @return The day
   */
  public String getDay() {
    return day;
  }

  /**
   * @param day The day
   */
  public void setDay(String day) {
    this.day = day;
  }

  /**
   * @return The high
   */
  public int getHigh() {
    return high;
  }

  /**
   * @param high The high
   */
  public void setHigh(int high) {
    this.high = high;
  }

  /**
   * @return The low
   */
  public int getLow() {
    return low;
  }

  /**
   * @param low The low
   */
  public void setLow(int low) {
    this.low = low;
  }

  /**
   * @return The text
   */
  public String getText() {
    return text;
  }

  /**
   * @param text The text
   */
  public void setText(String text) {
    this.text = text;
  }

}
