package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Version extends LangStringType {
  public Version() {
    super();
  }
  
  public Version(JSONObject json) {
    super(json, "");
  }
  
  public Version(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
