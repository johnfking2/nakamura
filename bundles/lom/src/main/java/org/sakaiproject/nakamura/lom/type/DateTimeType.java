package org.sakaiproject.nakamura.lom.type;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.elements.Description;

public abstract class DateTimeType extends Serialize {
  
  private String dateTime;
  private Description description;

  public DateTimeType() {
    super();
  }
  
  public DateTimeType(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String dateTimeName = "dateTime";
    String descriptionName = "description";
    dateTime = JSONUtil.getStringValue(json, dateTimeName);
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON != null)
      description = new Description(descriptionJSON);
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
