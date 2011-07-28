package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Version extends LangStringType {
  public Version() {
    super();
  }
  
  public Version(JSONObject json) {
    super(json);
  }
  
  @Override
  public String generateXML() {
    if (this.getLangString().generateXML().equals(""))
      return "";
    return new String("<version>" + this.getLangString().generateXML() + "</version>");
  }
}
