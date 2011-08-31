package org.sakaiproject.nakamura.cp;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class HasMetadata extends Serialize {
  private Metadata metadata;
  private String identifier;

  public HasMetadata() {
    super();
  }

  @Override
  protected void init() {
    String identifierName = "identifier";
    String metadataName = "metadata";
    String lomName = "lom";
    identifier = JSONUtil.getStringValue(json, identifierName);
    JSONObject metaJSON = JSONUtil.getJSONObject(json, metadataName);
    if (metaJSON !=null) {
      metadata = new Metadata(metaJSON);
    } else {
      if (JSONUtil.getJSONObject(json, lomName) != null) {
        metadata = new Metadata(json);
      }
    }
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
