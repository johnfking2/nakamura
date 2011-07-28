package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class OtherPlatformRequirements extends LangStringType {

  public OtherPlatformRequirements() {
    super();
  }
  
  public OtherPlatformRequirements(JSONObject json) {
    super(json);
  }
  
  @Override
  public String generateXML() {
    if (this.getLangString().generateXML().equals(""))
      return "";
    return new String("<otherPlatformRequirements>" + this.getLangString().generateXML() + "</otherPlatformRequirements>");
  }
}
