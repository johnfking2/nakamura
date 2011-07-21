package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Coverage extends LangStringType {

  public Coverage() {
    super();
  }
  
  public Coverage(JSONObject json) {
    super(json);
  }
  
  public Coverage(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
