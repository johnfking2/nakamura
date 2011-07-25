package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

public class OrComposite extends Serialize {

  private Type type;
  private Name name;
  private String minimumVersion;
  private String maxmumVersion;

  public OrComposite() {
    super();
  }
  
  public OrComposite(JSONObject json) {
    super(json);
  }

  @Override
  protected void init() {
    String typeName = "type";
    String nameName = "name";
    String minimumVersionName = "minimumVersion";
    String maxmumVersionName = "maxmumVersion";
    JSONObject typeJSON = JSONUtil.getJSONObject(json, typeName);
    if (typeJSON != null) 
      type = new Type(json);
    
    JSONObject nameJSON = JSONUtil.getJSONObject(json, nameName);
    if (nameJSON != null)
      name = new Name(json);
    
    minimumVersion = JSONUtil.getStringValue(json, minimumVersionName);
    maxmumVersion = JSONUtil.getStringValue(json, maxmumVersionName);
  }
  
  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public String getMinimumVersion() {
    return minimumVersion;
  }

  public void setMinimumVersion(String minimumVersion) {
    this.minimumVersion = minimumVersion;
  }

  public String getMaxmumVersion() {
    return maxmumVersion;
  }

  public void setMaxmumVersion(String maxmumVersion) {
    this.maxmumVersion = maxmumVersion;
  }
}
