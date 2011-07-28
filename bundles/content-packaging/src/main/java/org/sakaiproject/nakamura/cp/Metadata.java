package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.basic.LOMRoot;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class Metadata extends Serialize {

  private String schema;
  private String schemaVersion;
  private LOMRoot lom;

  public Metadata() {
    super();
  }
  
  public Metadata(JSONObject json){
    super(json);
  }
  
  @Override
  protected void init() {
    String schemaName = "schema";
    String schemaVersionName = "schemaVersion";
    String lomName = "lom";
    schema = JSONUtil.getStringValue(json, schemaName);
    schemaVersion = JSONUtil.getStringValue(json, schemaVersionName);
    JSONObject lomJSON = JSONUtil.getJSONObject(json, lomName);
    if (lomJSON != null) {
      lom = new LOMRoot(json);
    }
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
  
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getSchema() != null) {
      sb.append("<schema>" + this.getSchema() + "</schema>");
    }
    if (this.getSchemaVersion() != null) {
      sb.append("<schemaversion>" + this.getSchemaVersion() + "</schemaversion>");
    }
    if (this.getLom() != null) {
      sb.append(this.getLom().generateXML());
    }
    if(sb.toString().equals(""))
      return "";
    return new String("<metadata>" + sb.toString() + "</metadata>");
  }
}
