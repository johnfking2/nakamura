package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Description extends LangStringType{
  public Description() {
    super();
  }
  
  public Description(JSONObject json) {
    super(json);
  }
}
