package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public abstract class DateTimeType extends Serialize {
  
  private String dateTime;
  private Description description;
  
  protected String dateTimeName = "dateTime";
  protected String descriptionName = "description";
  
  public DateTimeType() {
    super();
  }
  
  public DateTimeType(JSONObject json) {
    super(json, "");
  }
  
  public DateTimeType(JSONObject json, String prefix) {
    super(json, prefix);
  }

  @Override
  protected void setElementName() {
    dateTimeName = prefix + "dateTime";
    descriptionName = prefix + "description";
  }

  @Override
  protected void init() {
    dateTime = JSONUtil.getStringValue(json, dateTimeName);
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON != null)
      description = new Description(descriptionJSON, prefix);
  }
  
  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public Description getDescription() {
    return description;
  }

  public void setDescription(Description description) {
    this.description = description;
  }
}
