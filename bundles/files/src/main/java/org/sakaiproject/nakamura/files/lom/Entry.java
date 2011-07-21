package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Entry extends LangStringType {
  public Entry() {
    super();
  }
  
  public Entry(JSONObject json) {
    super(json, "");
  }
  
  public Entry(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
