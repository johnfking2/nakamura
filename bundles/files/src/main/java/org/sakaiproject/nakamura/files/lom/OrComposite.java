package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class OrComposite extends Serialize {

  private Type type;
  private Name name;
  private String minimumVersion;
  private String maxmumVersion;
  
  protected String typeName = "type";
  protected String nameName = "name";
  protected String minimumVersionName = "minimumVersion";
  protected String maxmumVersionName = "maxmumVersion";
  
  public OrComposite() {
    super();
  }
  
  public OrComposite(JSONObject json) {
    super(json, "");
  }
  
  public OrComposite(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void setElementName() {
    typeName = prefix + "type";
    nameName = prefix + "name";
    minimumVersionName = prefix + "minimumVersion";
    maxmumVersionName = prefix + "maxmumVersion";
  }

  @Override
  protected void init() {
    JSONObject typeJSON = JSONUtil.getJSONObject(json, typeName);
    if (typeJSON != null) 
      type = new Type(json, prefix);
    
    JSONObject nameJSON = JSONUtil.getJSONObject(json, nameName);
    if (nameJSON != null)
      name = new Name(json, prefix);
    
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
