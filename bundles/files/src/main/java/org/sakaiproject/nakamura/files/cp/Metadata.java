package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lom.LOMRoot;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class Metadata extends Serialize {

  private String schema;
  private String schemaVersion;
  private LOMRoot lom;
  private String lomPrefix = "";
  
  protected String schemaName = "schema";
  protected String schemaVersionName = "schemaVersion";
  protected String lomName = "lom";
  
  public Metadata() {
    super();
  }
  
  public Metadata(JSONObject json){
    super(json, "");
  }
  
  public Metadata(JSONObject json, String prefix, String lomPrefix) {
    this.json = json;
    this.prefix = prefix;
    this.lomPrefix = lomPrefix;
    this.setElementName();
    this.init();
  }
  
  @Override
  protected void init() {
    schema = JSONUtil.getStringValue(json, schemaName);
    schemaVersion = JSONUtil.getStringValue(json, schemaVersionName);
    
    JSONObject lomJSON = JSONUtil.getJSONObject(json, lomName);
    if (lomJSON != null) {
      lom = new LOMRoot(lomJSON, lomPrefix);
    }
  }
  
  @Override
  protected void setElementName() {
    schemaName = prefix + "schema";
    schemaVersionName = prefix + "schemaVersion";
    lomName = lomPrefix + "lom";
  }
  
  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getSchemaVersion() {
    return schemaVersion;
  }

  public void setSchemaVersion(String schemaVersion) {
    this.schemaVersion = schemaVersion;
  }

  public LOMRoot getLom() {
    return lom;
  }

  public void setLom(LOMRoot lom) {
    this.lom = lom;
  }
}
