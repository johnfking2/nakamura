package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;

public class Organization extends HasItem {
  private String structure;
  
  public Organization() {
    super();
    this.type = HasItem.ITEMTYPE.ORGANIZATION;
  }
  
  public Organization(JSONObject json) {
    this.type = HasItem.ITEMTYPE.ORGANIZATION;
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
  
  @Override
  public String generateXML() {
    StringBuilder head = new StringBuilder("<organization");
    StringBuilder sb = new StringBuilder(super.generateXML());
    if (this.getIdentifier() != null)
      head.append(" identifier=\"" + this.getIdentifier() + "\"");
    if (this.getStructure() != null)
      head.append(" structure=\"" + this.getStructure() + "\"");
    if (sb.toString().equals("") && head.toString().equals("<organization"))
      return sb.toString();
    return new String(head + ">" + sb.toString() + "</organization>");
  }
}
