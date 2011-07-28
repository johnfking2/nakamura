package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class Duration extends Serialize{
  private String duration;
  private Description description;

  public Duration() {
    super();
  }
  
  public Duration(JSONObject json) {
    super(json);
  }
 
  @Override
  protected void init() {
    String durationName = "duration";
    String descriptionName = "description";
    
    duration = JSONUtil.getStringValue(json, durationName);
    
    JSONObject descriptionJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (descriptionJSON != null) {
      description = new Description(descriptionJSON);
    }
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
  
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getDuration() != null)
      sb.append("<duration>" + this.getDuration() + "</duration>");
    if (this.getDescription() != null)
      sb.append(this.getDescription().generateXML());
    if (sb.toString().equals(""))
      return "";
    return new String("<duration>" + sb.toString() + "</duration>");
  }
}
