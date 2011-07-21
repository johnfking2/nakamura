package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class Identifier extends Serialize {

  private String catalog;
  private String entry;

  protected String catalogName = "catalog";
  protected String entryName = "entry";
  
  public Identifier() {
    super();
  }
  
  public Identifier(JSONObject json) {
    super(json, "");
  }
  
  public Identifier(JSONObject json, String prefix) {
    super(json, prefix);
  }

  @Override
  protected void setElementName() {
    catalogName = prefix + "catalog";
    entryName = prefix + "entry";
  }

  @Override
  protected void init() {
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
