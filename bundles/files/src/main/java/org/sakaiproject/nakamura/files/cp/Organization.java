package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;

public class Organization extends HasItem {
  private String structure;
  
  protected String structureName = "structure";
  
  public Organization() {
    super();
  }
  
  public Organization(JSONObject json) {
    this.json = json;
    super.setElementName();
    this.setElementName();
    super.init();
    this.init();
  }
  
  public Organization(JSONObject json, String prefix, String lomPrefix) {
    this.json = json;
    this.prefix = prefix;
    this.lomPrefix = lomPrefix;
    this.setElementName();
    super.setElementName();
    super.init();
    this.init();  
  }
  
  public void init() {
    structure = JSONUtil.getStringValue(json, structureName);
  }
  
  public void setElementName() {
    structureName = prefix + "structure";
  }
  
  public String getStructure() {
    return structure;
  }
  
  public void setStructure(String structure) {
    this.structure = structure;
  }
}
