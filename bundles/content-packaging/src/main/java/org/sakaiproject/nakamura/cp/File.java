package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class File extends Serialize {
  private String href;
  private Metadata metadata;
  
  public File() {
    super();
  }
  
  public File(JSONObject json) {
    this.json = json;
    this.init();
  }
  
  public void init() {
    String hrefName = "href";
    String metadataName = "metadata";
    String lomName = "lom";
    href = JSONUtil.getStringValue(json, hrefName);
    
    JSONObject metaJSON = JSONUtil.getJSONObject(json, metadataName);
    if (metaJSON != null) {
      metadata = new Metadata(metaJSON);
    } else {
      if (JSONUtil.getJSONObject(json, lomName) != null) {
        metadata = new Metadata(json);
      }
    }
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
