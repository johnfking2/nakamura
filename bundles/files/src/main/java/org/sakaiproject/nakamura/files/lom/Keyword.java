package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class Keyword extends LangStringType {

  public Keyword() {
    
  }
  
  public Keyword(JSONObject json) {
    super(json, "");
  }
  
  public Keyword(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
