package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Description extends LangStringType{
  public Description() {
    super();
  }
  
  public Description(JSONObject json) {
    super(json, "");
  }
  
  public Description(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
