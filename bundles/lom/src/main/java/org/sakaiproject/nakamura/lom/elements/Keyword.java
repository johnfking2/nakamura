package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Keyword extends LangStringType {

  public Keyword() {
    
  }
  
  public Keyword(JSONObject json) {
    super(json);
  }
  
  @Override
  public String generateXML() {
    if (this.getLangString().generateXML().equals(""))
      return "";
    return new String("<keyword>" + this.getLangString().generateXML() + "</keyword>");
  }
}
