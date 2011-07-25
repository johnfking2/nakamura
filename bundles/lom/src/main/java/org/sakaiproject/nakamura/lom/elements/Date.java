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
}
