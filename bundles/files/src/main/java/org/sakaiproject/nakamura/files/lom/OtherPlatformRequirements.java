package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class OtherPlatformRequirements extends LangStringType {

  public OtherPlatformRequirements() {
    super();
  }
  
  public OtherPlatformRequirements(JSONObject json) {
    super(json, "");
  }
  
  public OtherPlatformRequirements(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
