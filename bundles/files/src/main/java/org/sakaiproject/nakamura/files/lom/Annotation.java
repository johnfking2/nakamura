package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;
import org.sakaiproject.nakamura.files.lomcp.Serialize;

public class Annotation extends Serialize{

  private String entity;
  private Date date;
  private Description description;
  
  protected String entityName = "entity";
  protected String dateName = "date";
  protected String descriptionName = "description";
  
  public Annotation() {
    super();
  }
  
  public Annotation(JSONObject json) {
    super(json, "");
  }
  
  public Annotation(JSONObject json, String prefix) {
    super(json, prefix);
  }
  
  @Override
  protected void init() {
    entity = JSONUtil.getStringValue(json, entityName);
    
    JSONObject dateJSON = JSONUtil.getJSONObject(json, dateName);
    if (dateJSON != null) {
      date = new Date(dateJSON, prefix);
    }
    
    JSONObject desJSON = JSONUtil.getJSONObject(json, descriptionName);
    if (desJSON != null) {
      description = new Description(desJSON, prefix);
    }
  }

  @Override
  protected void setElementName() {
    entityName = prefix + "entity";
    dateName = prefix + "date";
    descriptionName = prefix + "description";
  }
  
  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Description getDescription() {
    return description;
  }

  public void setDescription(Description description) {
    this.description = description;
  }
}
