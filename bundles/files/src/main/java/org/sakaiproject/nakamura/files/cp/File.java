package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class File extends Serialize {
  private String href;
  private Metadata metadata;
  protected String lomPrefix = ""; 
  
  protected String hrefName = "href";
  protected String metadataName = "metadata";
  protected String lomName = "lom";
  
  public File() {
    super();
  }
  
  public File(JSONObject json) {
    this.json = json;
    this.prefix = "";
    this.lomPrefix = "";
    this.setElementName();
    this.init();
  }
  
  public File(JSONObject json, String prefix, String lomPrefix) {
    this.json = json;
    this.prefix = prefix;
    this.lomPrefix = lomPrefix;
    this.setElementName();
    this.init();
  }
  
  public void init() {
    href = JSONUtil.getStringValue(json, hrefName);
    
    JSONObject metaJSON = JSONUtil.getJSONObject(json, metadataName);
    if (metaJSON != null) {
      metadata = new Metadata(metaJSON, prefix, lomPrefix);
    } else {
      if (JSONUtil.getJSONObject(json, lomName) != null) {
        metadata = new Metadata(json, prefix, lomPrefix);
      }
    }
  }
  
  public void setElementName() {
    hrefName = prefix + "href";
  }
  
  public String getHref() {
    return href;
  }
  
  public void setHref(String href) {
    this.href = href;
  }
  
  public Metadata getMetadata() {
    return metadata;
  }
  
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }
}
