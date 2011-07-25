package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Coverage extends LangStringType {

  public Coverage() {
    super();
  }
  
  public Coverage(JSONObject json) {
    super(json);
  }

}
