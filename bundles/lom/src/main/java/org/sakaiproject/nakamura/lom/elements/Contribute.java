package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.JSONUtil;
import org.sakaiproject.nakamura.lom.type.Serialize;

import java.util.ArrayList;
import java.util.List;

public class Contribute extends Serialize{

  private Role role;
  private Date date; 
  private List<Entity> entity;
  
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
      JSONArray entitysArray = JSONUtil.getJSONArray(json, entityName);
      if (entitysArray != null) {
        for (int i = 0; i < entitysArray.length(); i++) {
          String s = entitysArray.optString(i);
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

  public List<Entity> getEntity() {
    return entity;
  }

  public void addEntity (String vcard) {
    if (entity == null)
      entity = new ArrayList<Entity>();
    entity.add(new Entity(vcard));
  }
  public void setEntity(List<Entity> entity) {
    this.entity = entity;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
  
  @Override
  public String generateXML() {
    StringBuilder sb = new StringBuilder("");
    if (this.getDate() != null)
      sb.append(this.getDate().generateXML());
    if (this.getEntity() != null) {
      for (int i = 0; i < this.getEntity().size(); i++)
        sb.append(entity.get(i).generateXML());
    }
    if (this.getRole() != null)
      sb.append(this.getRole().generateXML());
    if (sb.toString().equals(""))
      return "";
    return new String ("<contribute>" + sb.toString() + "</contribute>");
  }
}
