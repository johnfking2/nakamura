package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class Dependency extends Serialize {

  private String identifierref;
  
  protected String identifierrefName = "identifierref";
  
  public Dependency() {
    super();
  }
  
  public Dependency(JSONObject json) {
    super(json, "");
  }
  
  public Dependency(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void init() {
    identifierref = JSONUtil.getStringValue(json, identifierrefName);    
  }
  
  @Override
  protected void setElementName() {
    identifierref = prefix + "identifierref";    
  }
  
  public String getIdentiferRef() {
    return identifierref;
  }
  
  public void setIdentifierRef(String id) {
    this.identifierref = id;
  }
}
