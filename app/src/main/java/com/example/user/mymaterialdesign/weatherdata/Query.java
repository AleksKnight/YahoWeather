package com.example.user.mymaterialdesign.weatherdata;

/**
 * Created by user on 02.02.2016.
 */
public class Query {
  private Integer count;
  private String created;
  private String lang;
  private Diagnostics diagnostics;
  private Results results;

  /**
   * @return The count
   */
  public Integer getCount() {
    return count;
  }

  /**
   * @param count The count
   */
  public void setCount(Integer count) {
    this.count = count;
  }

  /**
   * @return The created
   */
  public String getCreated() {
    return created;
  }

  /**
   * @param created The created
   */
  public void setCreated(String created) {
    this.created = created;
  }

  /**
   * @return The lang
   */
  public String getLang() {
    return lang;
  }

  /**
   * @param lang The lang
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * @return The diagnostics
   */
  public Diagnostics getDiagnostics() {
    return diagnostics;
  }

  /**
   * @param diagnostics The diagnostics
   */
  public void setDiagnostics(Diagnostics diagnostics) {
    this.diagnostics = diagnostics;
  }

  /**
   * @return The results
   */
  public Results getResults() {
    return results;
  }

  /**
   * @param results The results
   */
  public void setResults(Results results) {
    this.results = results;
  }

}
