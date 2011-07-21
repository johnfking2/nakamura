package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class Relation extends Serialize {

  private Kind kind;
  private Resource resource;
  
  protected String kindName = "kind";
  protected String resourceName = "resource";
  
  public Relation() {
    super();
  }
  
  public Relation(JSONObject json) {
    super(json, "");
  }
  
  public Relation(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void init() {
    JSONObject kindJSON = JSONUtil.getJSONObject(json, kindName);
    if (kindJSON != null) {
      kind = new Kind(kindJSON, prefix);
    }
    
    JSONObject resourceJSON = JSONUtil.getJSONObject(json, resourceName);
    if (resourceJSON != null) {
      resource = new Resource(resourceJSON, prefix);
    }
  }

  @Override
  protected void setElementName() {
    kindName = prefix + "kind";
    resourceName = prefix + "resource";
  }
  
  public Kind getKind() {
    return kind;
  }

  public void setKind(Kind kind) {
    this.kind = kind;
  }

  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }
}
