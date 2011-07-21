package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class TypicalAgeRange extends LangStringType {

  public TypicalAgeRange() {
    super();
  }
  
  public TypicalAgeRange(JSONObject json) {
    super(json, "");
  }
  
  public TypicalAgeRange(JSONObject json, String prefix){
    super(json, prefix);
  }
}
