package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Entry extends LangStringType {
  public Entry() {
    super();
  }
  
  public Entry(JSONObject json) {
    super(json);
  }
}
