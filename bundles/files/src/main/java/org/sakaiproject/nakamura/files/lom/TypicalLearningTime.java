package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

public class TypicalLearningTime extends Duration {
  public TypicalLearningTime() {
    super();
  }
  
  public TypicalLearningTime(JSONObject json) {
    super(json, "");
  }
  
  public TypicalLearningTime(JSONObject json, String prefix) {
    super(json, prefix);
  }
}
