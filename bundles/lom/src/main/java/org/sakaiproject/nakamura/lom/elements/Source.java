package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.LangStringType;

public class Source extends LangStringType {
  public Source() {
    super();
  }
  
  public Source(JSONObject json) {
    super(json);
  }
}
