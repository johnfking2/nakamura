package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Value extends LangStringType {

  public Value() {
    super();
  }
  
  public Value(JSONObject json) {
    super(json, "");
  }
  
  public Value(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
