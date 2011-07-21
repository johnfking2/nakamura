package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;

public class Item extends HasItem {
  private String identifierRef;
  private boolean isvisible;
  private String parameters;
  
  public String identifierRefName = "identifierRef";
  public String isvisibleName = "isvisible";
  public String parametersName = "parameters";
  
  public Item() {
    super();
  }
  public Item(JSONObject json) {
    this.json = json;
    super.setElementName();
    this.setElementName();
    super.init();
    this.init();    
  }
  public Item(JSONObject json, String prefix, String lomPrefix) {
    this.json = json;
    this.prefix = prefix;
    this.lomPrefix = lomPrefix;
    super.setElementName();
    this.setElementName();
    super.init();
    this.init();
  }

  @Override
  protected void setElementName() {
    identifierRefName = prefix + "identifierRef";
    isvisibleName = prefix + "isvisible";
    parametersName = prefix + "parameters";
  }

  @Override
  protected void init() {
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
