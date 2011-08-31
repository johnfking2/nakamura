package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;

public class Item extends HasItem {
  private String identifierRef;
  private boolean isvisible;
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
    isvisible = json.optBoolean(isvisibleName);
    parameters = JSONUtil.getStringValue(json, parametersName);
  }
  
  public String getIdentifierRef() {
    return identifierRef;
  }

  public void setIdentifierRef(String identifierRef) {
    this.identifierRef = identifierRef;
  }

  public boolean isIsvisible() {
    return isvisible;
  }

  public void setIsvisible(boolean isvisible) {
    this.isvisible = isvisible;
  }

  public String getParameters() {
    return parameters;
  }

  public void setParameters(String parameters) {
    this.parameters = parameters;
  }
}
