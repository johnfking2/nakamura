package org.sakaiproject.nakamura.files.lomcp;

import org.apache.sling.commons.json.JSONObject;

public abstract class Serialize {
  protected JSONObject json;
  
  protected String prefix = "";
  
  public Serialize() {
    setJSON(json);
  }
  
/*  public Serialize(JSONObject json) {
    this.json = json;
    init();
  }
 */ 
  public Serialize(JSONObject json, String prefix) {
    this.prefix = prefix;
    setJSON(json);
    setElementName();
    init();
  }
  
  public String getPrefix() {
    return prefix;
  }
  
  public JSONObject getJSON() {
    return json;
  }
  
  private void setJSON(JSONObject json) {
    this.json = json;
  }
//  protected abstract void initElementNames();
  protected abstract void setElementName();
  protected abstract void init();
}
