package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;

public class Item extends HasItem {
  private String identifierRef;
  private String isvisible;
  private String parameters;

  public Item() {
    super();
  }
  public Item(JSONObject json) {
    this.json = json;
    super.init();
    this.init();    
  }

  @Override
  protected void init() {
    String identifierRefName = "identifierRef";
    String isvisibleName = "isvisible";
    String parametersName = "parameters";
    identifierRef = JSONUtil.getStringValue(json, identifierRefName);
    isvisible = JSONUtil.getStringValue(json, isvisibleName);
    parameters = JSONUtil.getStringValue(json, parametersName);
  }
  
  public String getIdentifierRef() {
    return identifierRef;
  }

  public void setIdentifierRef(String identifierRef) {
    this.identifierRef = identifierRef;
  }

  public String getIsvisible() {
    return isvisible;
  }

  public void setIsvisible(String isvisible) {
    this.isvisible = isvisible;
  }

  public String getParameters() {
    return parameters;
  }

  public void setParameters(String parameters) {
    this.parameters = parameters;
  }
  
  @Override
  public String generateXML() {
    StringBuilder head = new StringBuilder("<item");
    StringBuilder sb = new StringBuilder(super.generateXML());
    if (this.getIdentifier() != null) {
      head.append(" identifier=\"" + this.getIdentifier() + "\"");
    }
    if (this.getIdentifierRef() != null) {
      head.append(" identifierref=\"" + this.getIdentifierRef() + "\"");
    }
    if (this.getIsvisible() != null) {
      head.append(" isvisible=\"" + this.getIsvisible() + "\"");
    }
    if (this.getParameters() != null) {
      head.append(" parameters=\"" + this.getParameters() + "\"");
    }
    if (sb.toString().equals("") && head.toString().equals("<item"))
      return sb.toString();
    return new String(head + ">" + sb.toString() + "</item>");
  }
}
