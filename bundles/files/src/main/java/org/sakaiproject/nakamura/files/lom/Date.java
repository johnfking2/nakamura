package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Date extends DateTimeType {
  public Date() {
    super();
  }
  
  public Date(JSONObject json) {
    super(json, "");
  }
  
  public Date(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
