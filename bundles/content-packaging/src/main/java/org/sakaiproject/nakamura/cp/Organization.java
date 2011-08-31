package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;

public class Organization extends HasItem {
  private String structure;
  
  public Organization() {
    super();
  }
  
  public Organization(JSONObject json) {
    this.json = json;
    super.init();
    this.init();
  }

  public void init() {
    String structureName = "structure";
    structure = JSONUtil.getStringValue(json, structureName);
  }

  public String getStructure() {
    return structure;
  }
  
  public void setStructure(String structure) {
    this.structure = structure;
  }
}
