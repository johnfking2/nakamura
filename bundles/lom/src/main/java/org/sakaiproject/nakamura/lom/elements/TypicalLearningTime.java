package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;

public class TypicalLearningTime extends Duration {
  public TypicalLearningTime() {
    super();
  }
  
  public TypicalLearningTime(JSONObject json) {
    super(json);
  }
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getDuration() != null)
      sb.append("<duration>" + this.getDuration() + "</duration>");
    if (this.getDescription() != null)
      sb.append(this.getDescription().generateXML());
    if (sb.toString().equals(""))
      return "";
    return new String("<typicalLearningTime>" + sb.toString() + "</typicalLearningTime>");
  }
}
