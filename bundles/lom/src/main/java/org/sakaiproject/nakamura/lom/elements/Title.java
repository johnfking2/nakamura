package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Title extends LangStringType {

  public Title() {
    super();
  }
  
  public Title(JSONObject json) {
    super(json);
  }
}
