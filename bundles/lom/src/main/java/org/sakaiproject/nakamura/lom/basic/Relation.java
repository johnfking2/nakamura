package org.sakaiproject.nakamura.lom.basic;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.elements.Kind;
import org.sakaiproject.nakamura.lom.elements.Resource;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class Relation extends Serialize {

  private Kind kind;
  private Resource resource;

  public Relation() {
    super();
  }
  
  public Relation(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String kindName = "kind";
    String resourceName = "resource";
    JSONObject kindJSON = JSONUtil.getJSONObject(json, kindName);
    if (kindJSON != null) {
      kind = new Kind(kindJSON);
    }
    
    JSONObject resourceJSON = JSONUtil.getJSONObject(json, resourceName);
    if (resourceJSON != null) {
      resource = new Resource(resourceJSON);
    }
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
  
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getKind() != null)
      sb.append(this.getKind().generateXML());
    if (this.getResource() != null)
      sb.append(this.getResource().generateXML());    
    if (sb.toString().equals(""))
      return "";
    return new String("<relation>" + sb.toString() + "</relation>");
  }
}
