package org.sakaiproject.nakamura.lom.type;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.xml.XML;

public abstract class Serialize {
  protected JSONObject json;
  
  public Serialize() {
    this.json = new JSONObject();
  }
  
  public Serialize(JSONObject json) {
    this.json = json;
    init();
  }
  
  public Serialize(String xmlContent) throws JSONException {
    this(XML.toJSONObject(xmlContent));
  }
 
  protected abstract void init();
  protected abstract String generateXML();
}
