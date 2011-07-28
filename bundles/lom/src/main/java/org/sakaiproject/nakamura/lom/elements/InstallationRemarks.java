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
  
  @Override
  public String generateXML() {
    if (this.getLangString().generateXML().equals(""))
      return "";
    return new String("<installationRemarks>" + this.getLangString().generateXML() + "</installationRemarks>");
  }
}
