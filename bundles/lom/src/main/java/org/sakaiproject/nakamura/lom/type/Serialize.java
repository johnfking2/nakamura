package org.sakaiproject.nakamura.lom.type;

import org.apache.sling.commons.json.JSONObject;

public abstract class Serialize {
  protected JSONObject json;
  
  public Serialize() {
    setJSON(json);
  }
  
  public Serialize(JSONObject json) {
    this.json = json;
    init();
  }
 
  public JSONObject getJSON() {
    return json;
  }
  
  private void setJSON(JSONObject json) {
    this.json = json;
  }
  protected abstract void init();
}
