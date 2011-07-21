package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class HasMetadata extends Serialize {
  private Metadata metadata;
  private String identifier;
  protected String lomPrefix = ""; 
  
  protected String identifierName = "identifier";
  protected String metadataName = "metadata";
  protected String lomName = "lom";
  
  
  public HasMetadata() {
    super();
  }

  @Override
  protected void init() {
    identifier = JSONUtil.getStringValue(json, identifierName);
    JSONObject metaJSON = JSONUtil.getJSONObject(json, metadataName);
    if (metaJSON !=null) {
      metadata = new Metadata(metaJSON, prefix, lomPrefix);
    } else {
      if (JSONUtil.getJSONObject(json, lomName) != null) {
        metadata = new Metadata(json, prefix, lomPrefix);
      }
    }
  }

  @Override
  protected void setElementName() {
    identifier = prefix + "identifier";
    metadataName = prefix + "metadata";
    lomName = lomPrefix + "lom";
  }  
  
  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }
  
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }
}
