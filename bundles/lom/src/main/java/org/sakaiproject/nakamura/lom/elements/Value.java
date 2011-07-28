package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Value extends LangStringType {

  public Value() {
    super();
  }
  
  public Value(JSONObject json) {
    super(json);
  }
  
  @Override
  public String generateXML() {
    if (this.getLangString().generateXML().equals(""))
      return "";
    return new String("<value>" + this.getLangString().generateXML() + "</value>");
  }
}
