package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class InstallationRemarks extends LangStringType {
  public InstallationRemarks() {
    super();
  }
  
  public InstallationRemarks(JSONObject json) {
    super(json, "");
  }
  
  public InstallationRemarks(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
