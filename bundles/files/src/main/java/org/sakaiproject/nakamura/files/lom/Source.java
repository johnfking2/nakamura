package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Source extends LangStringType {
  public Source() {
    super();
  }
  
  public Source(JSONObject json) {
    super(json, "");
  }
  
  public Source(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
