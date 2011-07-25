package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Keyword extends LangStringType {

  public Keyword() {
    
  }
  
  public Keyword(JSONObject json) {
    super(json);
  }
}
