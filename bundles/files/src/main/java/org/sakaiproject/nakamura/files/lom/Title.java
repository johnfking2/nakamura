package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Title extends LangStringType {

  public Title() {
    super();
  }
  
  public Title(JSONObject json) {
    super(json, "");
  }
  
  public Title(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
