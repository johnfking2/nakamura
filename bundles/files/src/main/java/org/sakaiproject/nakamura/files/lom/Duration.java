package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class Duration extends Serialize{
  private String duration;
  private Description description;
  
  protected String durationName = "duration";
  protected String descriptionName = "description";
  
  public Duration() {
    super();
  }
  
  public Duration(JSONObject json) {
    super(json, "");
  }
  
  public Duration(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  
  @Override
  protected void init() {
    duration = JSONUtil.getStringValue(json, durationName);
    
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON != null) {
      description = new Description(descriptionJSON, prefix);
    }
  }
  
  @Override
  protected void setElementName() {
    durationName = prefix + "duration";
    descriptionName = prefix + "description";
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public Description getDescription() {
    return description;
  }

  public void setDescription(Description description) {
    this.description = description;
  }
}
