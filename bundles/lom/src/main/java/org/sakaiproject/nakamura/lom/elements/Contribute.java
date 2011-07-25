package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Contribute extends Serialize{

  private Role role;
  private List<String> entity;
  private Date date; 
  
  public static enum CONTRIBUTETYPE {LIFECYCLE, METAMETADATA};
  private CONTRIBUTETYPE type;
  
  public Contribute (CONTRIBUTETYPE contributeType) {
    super();
    type = contributeType;
  }
  
  public Contribute (JSONObject json, CONTRIBUTETYPE contributeType) {
    super(json);
    type = contributeType;
  }

  @Override
  protected void init() {
    String roleName = "role";
    String entityName = "entity";
    String dateName = "date";
    JSONObject roleJSON = JSONUtil.getJSONObject(json, roleName);
    if (roleJSON != null)
      role = new Role(roleJSON, type);
    
    String entityString = JSONUtil.getStringValue(json, entityName);
    if (entityString == null) {
      JSONArray entityArray = JSONUtil.getJSONArray(json, entityName);
      if (entityArray != null) {
        for (int i = 0; i < entityArray.length(); i++) {
          String s = entityArray.optString(i);
          if (s != null) {
            addEntity(s);
          }
        }
      }
    } else {
      addEntity(entityString);
    }
    
    JSONObject dateJSON = JSONUtil.getJSONObject(json, dateName);
    if (dateJSON != null)
      date = new Date(dateJSON);
  }
  
  public CONTRIBUTETYPE getType() {
    return type;
  }
  
  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<String> getEntity() {
    return entity;
  }

  public void addEntity (String e) {
    if (entity == null)
      entity = new ArrayList<String>();
    entity.add(e);
  }
  public void setEntity(List<String> entity) {
    this.entity = entity;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }


}
