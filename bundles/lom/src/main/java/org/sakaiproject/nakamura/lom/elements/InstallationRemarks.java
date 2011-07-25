package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class InstallationRemarks extends LangStringType {
  public InstallationRemarks() {
    super();
  }
  
  public InstallationRemarks(JSONObject json) {
    super(json);
  }
}
