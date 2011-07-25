package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;

public class TypicalLearningTime extends Duration {
  public TypicalLearningTime() {
    super();
  }
  
  public TypicalLearningTime(JSONObject json) {
    super(json);
  }
}
