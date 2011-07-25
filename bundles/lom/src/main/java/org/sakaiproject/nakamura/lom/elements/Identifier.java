package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class Identifier extends Serialize {

  private String catalog;
  private String entry;

  public Identifier() {
    super();
  }
  
  public Identifier(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String catalogName = "catalog";
    String entryName = "entry";
    String catalogJSON = json.optString(catalogName);
    String entryJSON = json.optString(entryName);
    catalog = catalogJSON;
    entry = entryJSON;
  }
  
  public String getCatalog() {
    return catalog;
  }

  public void setCatalog(String catalog) {
    this.catalog = catalog;
  }

  public String getEntry() {
    return entry;
  }

  public void setEntry(String entry) {
    this.entry = entry;
  }

}
