package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.DateTimeType;

public class Date extends DateTimeType {
  public Date() {
    super();
  }
  
  public Date(JSONObject json) {
    super(json);
  }
  
  @Override
  public String generateXML() {
    if (super.generateXML().equals(""))
      return "";
    return new String("<date>" + super.generateXML() + "</date>");
  }
}
