package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class Taxon extends Serialize {

  private String id;
  private Entry entry;

  public Taxon() {
    super();
  }
  
  public Taxon(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String idName = "id";
    String entryName = "entry";
    
    id = json.optString(idName);
    
    JSONObject entryJSON = JSONUtil.getJSONObject(json, entryName);
    if (entryJSON != null)
      entry = new Entry(entryJSON);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Entry getEntry() {
    return entry;
  }

  public void setEntry(Entry entry) {
    this.entry = entry;
  }
  
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getId() != null) {
      sb.append("<id>" + this.getId() + "</id>");
    }
    if (this.getEntry() != null) {
      sb.append(this.getEntry().generateXML());
    }
    if (sb.toString().equals(""))
      return "";
    return new String("<taxon>" + sb.toString() + "</taxon>");
  }
}
