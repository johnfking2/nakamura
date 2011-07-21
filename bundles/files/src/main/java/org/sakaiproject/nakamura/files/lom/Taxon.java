package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class Taxon extends Serialize {

  private String id;
  private Entry entry;

  protected String idName = "id";
  protected String entryName = "entry";
  
  public Taxon() {
    super();
  }
  
  public Taxon(JSONObject json) {
    super(json, "");
  }
  
  public Taxon(JSONObject json, String prefix) {
    super(json, prefix);
  }

  @Override
  protected void init() {
    id = json.optString(idName);
    
    JSONObject entryJSON = JSONUtil.getJSONObject(json, entryName);
    if (entryJSON != null)
      entry = new Entry(entryJSON, prefix);
  }
  
  @Override
  protected void setElementName() {
    idName = prefix + "id";
    entryName = prefix + "entry";
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
}
